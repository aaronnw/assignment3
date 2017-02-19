package model;

import java.util.Observable;

public class Model extends Observable{
	
	String expression; 
	
	public Model(){
		this.expression = "";
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
}
