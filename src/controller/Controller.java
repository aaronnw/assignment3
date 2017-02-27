package controller;

import java.util.ArrayList;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import model.Model;

public class Controller {

	private final Model m;
	
	//Create a controller with a model to update
	public Controller(Model myModel) {
		this.m = myModel;
	}
	//Listen for a number press
	//This application treats the decimal as a number
	public SelectionListener getNumPressListener(String num){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				addToExpression(num.toCharArray()[0]);			
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}
		};
	}
	//Listen for the equals button
	public SelectionListener getEqualsListener(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				calculate();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		};
	}
	//Listen for the clear button
	public SelectionListener getClearPressListener(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				m.clearExpression();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		};
	}
	//Listen for the backspace button
	public SelectionListener getBackPressListener(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				m.backSpace();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		};
	}
	//Listen for the memory add button
	public SelectionListener getMemoryAddListener(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				m.storeExpression();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}
		};
	}
	//Listen for the memory recall button
	public SelectionListener getMemoryRecallListener(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				m.recallExpression();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}
		};
	}
	/*Adds characters to the list of inputs, combining decimals and multiple digit numbers*/
	public void addToExpression(char c){
		ArrayList<String> list = m.getList();
		if(list.size()>0){
			String front = list.get(list.size()-1);
			if(front.contains(".") && c == '.'){
				return;
			}
			if(isOp(front) && isOp(c)){
				list.remove(list.size()-1);
			}
			if(!isOp(front) && !isOp(c)){
				m.removeListEnd();
				m.addToList(front+c);
				return;
			}
			
		}
		m.addToList(Character.toString(c));
	}
	//When equals is pressed, calculate the result of the expression
	public void calculate(){
		//Check the syntax
		Boolean validInput = false;
		validInput = checkSyntax();
		if(validInput){
			//Calculate multiplication and division 
			calcPriorityOps();
			//Then perform other ops
			calcResult();
		}else{
			m.reportError();
		}
		
	}
	//Check if the entered expression is valid
	public boolean checkSyntax(){
		ArrayList<String> list = m.getList();
		//Check for empty string
		if(list.size() == 0){
			return false;
		}
		//Check for operators to begin or end the expression
		if(isOp(list.get(0)) || isOp(list.get(list.size()-1))){
			return false;
		}
		for(int i = 1; i < list.size(); i ++){
			//Checks for consecutive operators or numbers
			if(isOp(list.get(i))){
				if(isOp(list.get(i-1))){
					return false;
				}
			}else{
				if(!isOp(list.get(i-1))){
					return false;
				}
			}
			//Checks for lonely decimal points
			if(list.get(i).equals(".")){
				return false;
			}
		}
		return true;
	}
	//Calculates multiplication and division
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
	//Calculate the final result
	public void calcResult(){
		ArrayList<String> list = m.getList();
		//Set the result and return when the list is too small to be simplified
		if(list.size() < 3){
			m.setOutput(Double.parseDouble(list.get(0)));
			return;
		}
		//If there is a string with a number, then operator, then number, calculate the result and repeat
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
			m.reportError();
		}		
	}
	//Check if a character is an operator
	private boolean isOp(char c){
		if(c == '*' || c == '/' || c=='+' || c=='-' ){
			return true;
		}
		return false;
	}
	//Check if a string is an operator
	private boolean isOp(String s){
		if(s.equals("*") || s.equals("/") || s.equals("+") || s.equals("-") ){
			return true;
		}
		return false;
	}
	//Check if a string is a multiplication or division symbol
	private boolean isPriorityOp(String s){
		if(s.equals("*") || s.equals("/")){
			return true;
		}
		return false;
	}
	
}