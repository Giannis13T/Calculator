package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {
	
	@FXML private TextField details;
	@FXML private Button zero;
	@FXML private Button point;
	@FXML private Button clear;
	@FXML private Button one;
	@FXML private Button two;
	@FXML private Button three;
	@FXML private Button four;
	@FXML private Button five;
	@FXML private Button six;
	@FXML private Button seven;
	@FXML private Button eight;
	@FXML private Button nine;
	@FXML private Button del;
	@FXML private Button paropen;
	@FXML private Button parclose;
	@FXML private Button equals;
	@FXML private Button add;
	@FXML private Button sub;
	@FXML private Button mul;
	@FXML private Button div;
	String text = null;
	boolean textNotNull = false;
	String result = null;
	
	
	@FXML
	private void buttonsClicked(ActionEvent e) {
		
		if (e.getSource()==equals) { // calculate the result
			String result = calculate(text);
			text = result;
			details.setText(text);
		} else if (e.getSource()==clear) { // clear the text field
			details.setText(null);
			text = null;
			textNotNull = false;
		} else if (e.getSource()==del) { // delete the last character from the text field
			try {
				text = text.substring(0, text.length()-1);
				details.setText(text);
				textNotNull = true;
			} catch (Exception ex) {
				textNotNull = false;
			}
		} else {
			String btn = ((Button) e.getSource()).getText();
			text = details.getText();
			char last = ' ';
			try {
				last = text.charAt(text.length()-1);
			} catch (Exception ex) {
				last = ' ';
			}
			if (e.getSource()==paropen) { // check if '(' can be appended to the string
				if (last=='+'||last=='-'||last=='*'||last=='/'||last=='('||!textNotNull) {
					if (text!=null) text = text+btn;
					else text = btn;
					details.setText(text);
					textNotNull = true;
				}
			} else if (e.getSource()==parclose) { // check if ')' can be appended to the string
				if (Character.isDigit(last)||last==')') {
					if (text!=null) text = text+btn;
					else text = btn;
					details.setText(text);
					textNotNull = true;
				}
			} else if (e.getSource()==add||e.getSource()==sub||e.getSource()==mul||e.getSource()==div) {
				// check if math symbols can be appended to the string
				if (Character.isDigit(last)||last==')'||((last=='('||!textNotNull)&&e.getSource()==sub)) {
					if (text!=null) text = text+btn;
					else text = btn;
					details.setText(text);
					textNotNull = true;
				}
			} else if (e.getSource()==point) { // check if ',' can be appended to the string
				if (Character.isDigit(last)) {
					if (text!=null) text = text+btn;
					else text = btn;
					details.setText(text);
					textNotNull = true;
				}
			} else { // check if numbers can be appended to the string
				try {
					if (text.charAt(text.length()-1)!=')') {
						if (text!=null) text = text+btn;
						else text = btn;
						details.setText(text);
						textNotNull = true;
					}
				} catch (Exception ex) {
					if (text!=null) text = text+btn;
					else text = btn;
					details.setText(text);
					textNotNull = true;
				}
			}
		}
		
	}
	
	
	// Calculates the result of the mathematical expression given as a string
	private String calculate(String text) {
		
		boolean parenthesesFound = search(text,'(')&&search(text,')');
		if (parenthesesFound) {
			text = calcParentheses(text); // calculate the expressions into parentheses
			text = getFinalText(text); // calculate the whole expression
			result = getResult(text);
		}
		// calculate the whole expression value if there are no parentheses
		else {
			text = getFinalText(text);
			result = getResult(text);
		}
		return result;
	}
	
	// Searches if a specific character in a string exists
	private boolean search(String s, char ch) {
		
		boolean found = false;
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)==ch) {
				found = true;
				break;
			}
		}
		return found;
		
	}
	
	// calculates the result inside the parentheses
	private String calcParentheses(String text) {
		int paropenind = -1;
		int parcloseind = -1;
		boolean done = false;
		String res = null;
		while (!done) {
			for (int i=0; i<text.length(); i++) {
				if (text.charAt(i)=='(') {
					paropenind = i;
				} else if (text.charAt(i)==')') {
					parcloseind = i;
					String temp = text.substring(paropenind+1, parcloseind);
					temp = getFinalText(temp);
					res = getResult(temp);
					text = text.replace(text.substring(paropenind, parcloseind+1), res);
					if (!search(text,')')) done = true;
					break;
				}
			}
		}
		return text;
	}
	
	// changes the text in a form getResult wants it to be
	private String getFinalText(String text) {
		for (int i=0; i<text.length(); i++) {
			// deal with consecutive '-' occurrences
			if (text.charAt(i)=='-'&&text.charAt(i+1)=='-') {
				if (i==0) text = text.substring(2, text.length());
				else {
					char prev = text.charAt(i-1);
					if (prev=='+') text = text.replaceFirst(text.substring(i-1, i+2), "+");
					else if (prev=='*') text = text.replaceFirst(text.substring(i-1, i+2), "*");
					else if (prev=='/') text = text.replaceFirst(text.substring(i-1, i+2), "/");
					else if (Character.isDigit(prev)||prev=='('||prev==')') {
						text = text.replaceFirst(text.substring(i, i+2),"+");
					}
				}
			} else if (text.charAt(i)=='-'&&(i!=0&&text.charAt(i-1)!='('&&text.charAt(i-1)!='+'&&text.charAt(i-1)!='*'&&text.charAt(i-1)!='/')) {
				// deal with non-consecutive '-' occurrences
				char prev = text.charAt(i-1);
				if (Character.isDigit(prev)) {
					text = text.replaceFirst(text.substring(i, i+1), "+-");
				} else if (prev==')') {
					text = text.replaceFirst(text.substring(i, i+1), "+-");
				}
			}
		}
		return text;
	}
	
	// Calculates the value of a mathematical expression without parentheses
	private String getResult(String text) {
		
		text = getFinalText(text);
		String[] nums = text.split("\\+|\\*|\\/");
		while (nums.length>1) {
			double res = -1;
			boolean foundsym1 = false;
			boolean foundsym2 = false;
			int addSubInd = -1;
			int mulDivInd = -1;
			int occ = 0;
			int occ2 = 0;
			for (int i=0; i<text.length(); i++) {
				if (text.charAt(i)=='+') {
					if (!foundsym1) {
						foundsym1 = true;
						addSubInd = i;
						occ++;
					} else occ2++;
				} else if (text.charAt(i)=='*'||text.charAt(i)=='/') {
					foundsym2=true;
					mulDivInd = i;
					occ = occ+1+occ2;
					if (foundsym2) break;
				}
			}
			if ((occ>0)&&(foundsym2)) {
				char s = ' ';
				String s1 = nums[occ-1];
				String s2 = nums[occ];
				if (mulDivInd>=0) s = text.charAt(mulDivInd);
				if (s=='*') res = (Double.parseDouble(s1)*Double.parseDouble(s2));
				else if (s=='/') res = (Double.parseDouble(s1)/Double.parseDouble(s2));
				String sres = Double.toString(res);
				text = text.replace(text.substring(mulDivInd-s1.length(), mulDivInd+s2.length()+1), sres);
				if (nums.length==2) {
					return sres;
				}
				nums = text.split("\\+|\\*|\\/");
			} else if ((occ>0)&&(!foundsym2)) {
				char s = ' ';
				String s1 = nums[occ-1];
				String s2 = nums[occ];
				if (addSubInd>=0) s = text.charAt(addSubInd);
				if (s=='+') res = (Double.parseDouble(s1)+Double.parseDouble(s2));
				String sres = Double.toString(res);
				text = text.replace(text.substring(addSubInd-s1.length(), addSubInd+s2.length()+1), sres);
				if (nums.length==2) {
					return sres;
				}
				nums = text.split("\\+|\\*|\\/");
			}
		}
		return nums[0];
		
	}
	
}