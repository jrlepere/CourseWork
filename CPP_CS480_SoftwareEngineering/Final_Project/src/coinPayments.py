from flask import Flask, request, render_template
import urllib.request, urllib.parse, urllib.error
import hmac
import hashlib
import json
import csv
import os
from collections import namedtuple
from flask_mail import Mail, Message

app = Flask(__name__)
mail = Mail(app)

app.config['MAIL_SERVER'] = 'smtp.gmail.com'
app.config['MAIL_PORT'] = 465
app.config['MAIL_USERNAME'] = 'pants.shopping@gmail.com'
app.config['MAIL_PASSWORD'] = 'coinpayments'
app.config['MAIL_USE_TLS'] = False
app.config['MAIL_USE_SSL'] = True
transactions = {}
API_KEY = 'omitted for security'
API_SECRET = 'omitted for security'
API_URL = 'https://www.coinpayments.net/api.php'
mail = Mail(app)


def _json_hook(d):
    return namedtuple('X', list(d.keys()), rename=True)(*list(d.values()))


def pObject(data):
    return json.loads(data, object_hook=_json_hook).result


def mail1():
    msg = Message("greetings from python code", sender='pants.shopping@gmail.com', recipients=['sevanp@gmail.com'])
    msg.body = "flask mail test message from python code"
    mail.send(msg)
    print("sent")


def get_rates(c):
    param_rates = {'cmd': 'rates',
                   'short': 1,
                   'key': API_KEY,
                   'version': 1}

    encoded = urllib.parse.urlencode(param_rates).encode('utf-8')
    sig = hmac.new(API_SECRET.encode('utf-8'), encoded, digestmod=hashlib.sha512).hexdigest()
    headers = {'hmac': sig, 'Content-Type': 'application/x-www-form-urlencoded'}
    req = urllib.request.Request(API_URL, data=encoded, headers=headers)

    response = urllib.request.urlopen(req)
    response_body = response.read()  # bytes

    r = pObject(response_body)
    rates = r._asdict()
    #print(rates)
    if c == 'BTC':
        return rates['USD'].rate_btc # rates['USD'][1]
    else:
        usd = float(rates['USD'].rate_btc)
        crypto = 1 / float(rates[c].rate_btc)
        return usd * crypto


@app.route('/t')
def t():
    print(transactions)
    return "HI"


@app.route("/")
def index():
    pants_images = []
    partial_path = "static\item_images/700x400\\"
    item_csv = csv.DictReader(open("static/items.csv"))
    for row in item_csv:
        row['path'] = partial_path + row.get('path')  # getting the full path of the image.
        pants_images.append(row)  # adding each items dictionary to the list
    return render_template("index.html", images=pants_images)  # passing list of dicts to bootstrap.


@app.route("/item")
def item():
    item_image = os.path.join("/", request.args.get('img'))
    item_price = request.args.get('price')
    item_name = request.args.get('name')
    item_text = request.args.get('text')
    return render_template("items.html", image=item_image, price=item_price, name=item_name, text=item_text)


@app.route("/button")
def button():
    address = request.args.get('email', '')
    quantity = request.args.get('quantity', '')
    temp_price = request.args.get('price')
    name = request.args.get('name')
    text = request.args.get('text')
    currency = request.args.get('bel')   # bel = Bitcoin,Ether,Lite
    price = temp_price[0:-4]   # keep the number only
    new_rate = get_rates(currency)  # rate of dollar in crypto
    cost = round(int(quantity) * float(new_rate) * float(price), 6) # total cost of purchase rounded to 6 decimal digits
    # print(price)
    # print(quantity)
    # print(cost)
    # print(currency)

    param2 = {'cmd': 'create_transaction',
              'key': API_KEY,
              'version': 1,
              'amount': cost,
              'currency1': currency,
              'currency2': currency,
              'buyer_email': address}

    encoded = urllib.parse.urlencode(param2).encode('utf-8')

    sig = hmac.new(API_SECRET.encode('utf-8'), encoded, digestmod=hashlib.sha512).hexdigest()

    headers = {'hmac': sig, 'Content-Type': 'application/x-www-form-urlencoded'}
    req = urllib.request.Request(API_URL, data=encoded, headers=headers)

    response = urllib.request.urlopen(req)
    response_body = response.read()  # bytes
    res_nametuple = pObject(response_body)  # namedtuple returned from json function
    res_dict = res_nametuple._asdict()  # name tuple to ordered dictionary
    qr_code = res_dict["qrcode_url"]  # returns the value of element in the dict where the key is "qrcode_url"
    amount = res_dict["amount"]
    w_address = res_dict["address"]
    transID = res_dict["txn_id"]
    buyer = {'email': address,'text':None}
    transactions[transID] = buyer

    total_usd = float(price) * int(quantity) # total cost in USD
    return render_template('checkout.html', qrcode_url=qr_code, amount=amount, currency=currency, address=w_address,
                           txn_id=transID, name=name, quantity=quantity, text=text, price=total_usd)


if __name__ == "__main__":
    app.run(threaded=True)
