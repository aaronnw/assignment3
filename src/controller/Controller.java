package controller;

import java.util.Stack;

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
					addToExpression('0');
					break;
				case "1" :
					addToExpression('1');
					break;
				case "2" :
					addToExpression('2');
					break;
				case "3" :
					addToExpression('3');
					break;
				case "4" :
					addToExpression('4');
					break;
				case "5" :
					addToExpression('5');
					break;
				case "6" :
					addToExpression('6');
					break;
				case "7" :
					addToExpression('7');
					break;
				case "8" :
					addToExpression('8');
					break;
				case "9" :
					addToExpression('9');
					break;
				case "+" :
					addToExpression('+');
					break;
				case "-" :
					addToExpression('-');
					break;
				case "*" :
					addToExpression('*');
					break;
				case "/" :
					addToExpression('/');
					break;
				case "." :
					addToExpression('.');
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
	/*Adds characters to the stack of input, combining decimals and multiple digit numbers*/
	public void addToExpression(char c){
		Stack<String> stack = m.getQueue();
		if(stack.size()>0){
			String top = stack.peek();
			if(isOp(top)){
				m.addToStack(Character.toString(c));
			}else{
				if(isOp(c)){
					m.addToStack(Character.toString(c));
				}else{
					m.removeQHead();
					m.addToStack(top+c);
				}
			}
		}else{
			m.addToStack(Character.toString(c));
		}
	}

	public void calculate(){
		System.out.println("equals");
		//Use two stacks
		//Flip the stack first to go left to right
		//Then go through flipping the stack back, performing priority ops
		//Then perform other ops
		
	}
	private boolean isOp(char c){
		if(c == '*' || c == '/' || c=='+' || c=='-' ){
			return true;
		}
		return false;
	}
	private boolean isOp(String s){
		if(s.equals("*") || s.equals("/") || s.equals("+") || s.equals("-") ){
			return true;
		}
		return false;
	}
	
	private boolean isPriorityOp(char c){
		if(c == '*' || c == '/' ){
			return true;
		}
		return false;
	}

}
