package model;

import java.util.Observable;
import java.util.Stack;

public class Model extends Observable{
	
	String expression;
	Stack<String> stack;
	
	public Model(){
		this.expression = "";
		stack = new Stack<String>();
	}
	public void addChar(char c){
		expression = expression + c;
		setChanged();
		notifyObservers();
	}

	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
		
	}
	public Stack<String> getQueue(){
		return stack;		
	}
	public void addToStack(String s){
		stack.add(s);
		expression = stack.toString();
		setChanged();
		notifyObservers();
	}
	public void removeQHead(){
		stack.pop();
	}
}
