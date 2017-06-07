package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class CalculatorController {
	
	@FXML private MenuItem about;
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
				if (last=='+'||last=='-'||last=='*'||last=='/'||!textNotNull) {
					if (text!=null) text = text+btn;
					else text = btn;
					details.setText(text);
					textNotNull = true;
				}
			} else if (e.getSource()==parclose) { // check if ')' can be appended to the string
				if (Character.isDigit(last)) {
					if (text!=null) text = text+btn;
					else text = btn;
					details.setText(text);
					textNotNull = true;
				}
			} else if (e.getSource()==add||e.getSource()==sub||e.getSource()==mul||e.getSource()==div) {
				// check if math symbols can be appended to the string
				if (Character.isDigit(last)||last==')') {
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
	
	@FXML
	private void menuItemClicked(ActionEvent e) {
		
	}
	
	private String calculate(String text) {
		return null;
	}
}
