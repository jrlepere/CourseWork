
public class Precondition {

	private int n;
	
	public Precondition(int n) {
		this.n = n;
	}
	
	/**
	 * Sets n to a new value.
	 * @param n some number n
	 * @precondition n > 0
	 */
	public void setN(int n) {
		this.n = n;
	}
	
	public int getN() {
		return n;
	}
	
}
