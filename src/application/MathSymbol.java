package application;

public class MathSymbol {

	private char symbol; // The math symbol
	private int priority; // The symbol's priority
	
	/* The number of a symbol's occurrence. For example, the first symbol occurrence will be 1 ,
	 * and the second symbol occurrence will be 2. */
	private int occurrence;
	
	// Constructor
	public MathSymbol(char s, int p, int o) {
		symbol = s;
		priority = p;
		occurrence = o;
	}
	
	public char getSymbol() { return symbol;}
	public void setSymbol(char ch) { symbol = ch;}
	public int getPriority() { return priority;}
	public void setPriority(int p) { priority = p;}
	public int getOccurrence() { return occurrence;}
	public void setOccurrence(int o) { occurrence = o;}
	
}