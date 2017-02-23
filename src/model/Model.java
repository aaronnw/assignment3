package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

public class Model extends Observable{
	
	String expression;
	ArrayList<String> list;
	Double output; 

	public Model(){
		this.expression = "";
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
	public ArrayList<String> getList(){
		return list;		
	}
	public void addToList(String s){
		list.add(s);
		expression = list.toString();
		setChanged();
		notifyObservers();
	}
	public void removeListFront(){
		list.remove(0);
	}
	public void replaceList(ArrayList<String> newList){
		list = newList;
	}
	
	public void setOutput(Double d){
		output = d; 
		expression = d.toString();
		setChanged();
		notifyObservers();
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
