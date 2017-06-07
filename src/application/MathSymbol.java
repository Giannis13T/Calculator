package application;

public class MathSymbol {

	private char symbol; // The math symbol
	private int priority; // The symbol's priority
	
	/* The number of a symbol's occurrence. The variable considers symbols with equal priority as the same.
	 * For example, the first '+' occurrence would be 1 , and then a '-' occurrence would be 2. */
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
