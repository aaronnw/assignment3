package controller;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import model.Model;

public class Controller {

	Model m; 
	
	public Controller(Model myModel) {
		this.m = myModel;
	}
	
	public SelectionListener getNumPressListener(String num){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				switch (num) {
				case "0" :
					m.addChar('0');
					break;
				case "1" :
					m.addChar('1');
					break;
				case "2" :
					m.addChar('2');
					break;
				case "3" :
					m.addChar('3');
					break;
				case "4" :
					m.addChar('4');
					break;
				case "5" :
					m.addChar('5');
					break;
				case "6" :
					m.addChar('6');
					break;
				case "7" :
					m.addChar('7');
					break;
				case "8" :
					m.addChar('8');
					break;
				case "9" :
					m.addChar('9');
					break;
				case "+" :
					m.addChar('+');
					break;
				case "-" :
					m.addChar('-');
					break;
				case "*" :
					m.addChar('*');
					break;
				case "/" :
					m.addChar('/');
					break;
				case "." :
					m.addChar('.');
					break;
				
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	public SelectionListener getEqualsListener(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				calculate();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	public void calculate(){
		String exp = m.getExpression();
		m.setExpression(exp);
	}

}
