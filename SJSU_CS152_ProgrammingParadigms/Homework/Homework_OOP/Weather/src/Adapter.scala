
/**
 * An Adapter class for calculating the weather.
 */
class Adapter extends IThermometer {
  val adaptee = new CenTherm
  def getMeanTemperature(cities: List[String]): Double = {
    var totalTemp:Double = 0.0
    for (city <- cities) totalTemp += adaptee.computeTemp(city)
    totalTemp/cities.length
  }
}