package controller;

import java.util.ArrayList;
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
		Stack<String> stack = m.getStack();
		if(stack.size()>0){
			String top = stack.peek();
			if(isOp(top)){
				m.addToStack(Character.toString(c));
			}else{
				if(isOp(c)){
					m.addToStack(Character.toString(c));
				}else{
					m.removeStackTop();
					m.addToStack(top+c);
				}
			}
		}else{
			m.addToStack(Character.toString(c));
		}
	}

	public void calculate(){
		//Flip the stack first to go left to right
		m.flipStack();
		//Change the stack to a list
		stackToList();
		//Check the syntax
		Boolean validInput = false;
		validInput = checkSyntax();
		if(validInput){
			//Calculate multiplication and division 
			calcPriorityOps();
			//Then perform other ops
			calcResult();
			//Round or remove decimal
			ArrayList<String> list = m.getList();
			if(isLongable(list.get(0))){
				long answer = simplifyAnswer(list.get(0));
				m.setFinalAnswer(answer);
			}else{
				double answer = Double.parseDouble(list.get(0));
				m.setFinalAnswer(answer);
			}
		}else{
			System.out.println("Syntax error");
		}
		
	}
	
	public void stackToList(){
		Stack<String> s = m.getStack();
		ArrayList<String> list = new ArrayList<String>();
		//Change the stack to a list
		while(s.size()>0){
			list.add(s.pop());
		}
		m.setList(list);
	}
	
	public boolean checkSyntax(){
		ArrayList<String> list = m.getList();
		if(list.size() == 0){
			return false;
		}
		if(isOp(list.get(0)) || isOp(list.get(list.size()-1))){
			return false;
		}
		for(int i = 1; i < list.size()-1; i ++){
			if(isOp(list.get(i))){
				if(isOp(list.get(i-1))){
					return false;
				}
			}else{
				if(!isOp(list.get(i-1))){
					return false;
				}
			}
		}
		return true;
	}
	
	public void calcPriorityOps(){
		ArrayList<String> list = m.getList();
		//Iterate through the list, starting from the second and going to the second to last
		for(int i = 1; i < list.size() -1 ; i ++){
			Double result = 0.0;
			String current = list.get(i);
			//If there is a priority op
			if(isPriorityOp(current)){
				String next = list.get(i+1);
				String prev = list.get(i-1);
				if(current.equals("*")){
					result = Double.parseDouble(prev) * Double.parseDouble(next); 
				}else if(current.equals("/")){
					result = Double.parseDouble(prev) / Double.parseDouble(next); 
				}
			    	list.set(i-1, result.toString());
			    	//Remove i twice to remove the next two elements
					list.remove(i);
					list.remove(i);
					//Call recursively to find and calculate the next op
					calcPriorityOps();
			}			
		}
		m.setList(list);
	}

	public void calcResult(){
		ArrayList<String> list = m.getList();
		if(list.size() < 3){
			m.setOutput(Double.parseDouble(list.get(0)));
			return;
		}
		if(!isOp(list.get(0)) && isOp(list.get(1)) && !isOp(list.get(2))){
			Double result = 0.0; 
			Double first = Double.parseDouble(list.get(0));
			String op = list.get(1);
			Double second = Double.parseDouble(list.get(2));
			if(op.equals("+")){
				 result = first + second;
			}else if(op.equals("-")){
				 result = first - second;
			}
			list.set(0, result.toString());
			//Remove 1 twice to remove the next two elements
			list.remove(1);
			list.remove(1);
			calcResult();
		}else{
			System.out.println("Syntax err");
		}
		
	}
	
	public long simplifyAnswer(String s){
		double answer = Double.parseDouble(s);
		Long newAnswer = (long) 0;
		if(answer %1 == 0){
			newAnswer = (long) answer;
 		}
		return newAnswer;
	}
	//Yeah it's what you think
	private boolean isLongable(String s){
		double answer = Double.parseDouble(s);
		if(answer %1 == 0){
			return true;
 		}
		return false;
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
	
	private boolean isPriorityOp(String s){
		if(s.equals("*") || s.equals("/")){
			return true;
		}
		return false;
	}
	
}
