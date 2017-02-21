package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

public class Model extends Observable{
	
	String expression;
	Stack<String> inputStack;
	ArrayList<String> list;
	Double output; 

	public Model(){
		this.expression = "";
		inputStack = new Stack<String>();
		list = new ArrayList<String>();
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
	public Stack<String> getStack(){
		return inputStack;		
	}
	public void addToStack(String s){
		inputStack.add(s);
		expression = inputStack.toString();
		setChanged();
		notifyObservers();
	}
	public void removeStackTop(){
		inputStack.pop();
	}
	public void flipStack(){
		Stack<String> newStack = new Stack<String>();
		while(inputStack.size() > 0){
			newStack.add(inputStack.pop());
		}
		inputStack = newStack;
	}
	public void replaceStack(Stack<String> s){
		inputStack = s;
	}
	
	public void setOutput(Double d){
		output = d; 
		expression = d.toString();
		setChanged();
		notifyObservers();
	}

	public ArrayList<String> getList() {
		return list;
	}
	public void setList(ArrayList<String> list) {
		this.list = list;
		expression = list.toString();
		setChanged();
		notifyObservers();
	}
	public void setFinalAnswer(Long l){
		expression = l.toString();
		setChanged();
		notifyObservers();
	}
	public void setFinalAnswer(Double d){
		expression = d.toString();
		setChanged();
		notifyObservers();
	}
	
}
