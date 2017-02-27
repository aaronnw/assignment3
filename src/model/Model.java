package model;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable{
	
	private ArrayList<String> list;
	private Double output; 
	private ArrayList<String> memory;

	//Creates a model and initializes variables
	public Model(){
		list = new ArrayList<String>();
		memory = new ArrayList<String>(); 
		output = 0.0;
	}
	
	public void addChar(char c){
		setChanged();
		notifyObservers("entry");
	}
	public void addToList(String s){
		list.add(s);
		setChanged();
		notifyObservers("entry");
	}
	public void backSpace(){
		if(list.size()>0){
			String lastString = list.get(list.size()-1);
			//If the last string in the list is one character, delete the whole string
			if(lastString.length() == 1){
				list.remove(list.size()-1);
			//If the last string is multiple characters, replace it without the last character
			}else{
				String newLast = lastString.substring(0, lastString.length()-1);
				list.set(list.size()-1, newLast);
			}
			setChanged();
			notifyObservers("entry");
		}
	}
	public void removeListEnd(){
		list.remove(list.size()-1);
		setChanged();
		notifyObservers("entry");
	}
	public void clearExpression(){
		list.clear();
		setChanged();
		notifyObservers("entry");
	}
	public void replaceList(ArrayList<String> newList){
		list = newList;
		setChanged();
		notifyObservers("entry");
	}
	public void storeExpression(){
		if(list.size() == 0){
			memory = new ArrayList<String>();
			memory.add(output.toString());
		}else{
			memory = new ArrayList<String>(list);
		}
	}
	public void recallExpression(){
		list.addAll(memory);
		setChanged();
		notifyObservers("entry");
	}
	public void reportError(){
		list.clear();
		setChanged();
		notifyObservers("error");
	}
	
	//Getters and setters
	
	public void setOutput(Double d){
		output = d; 
		list.clear();
		setChanged();
		notifyObservers("output");
	}
	public void setList(ArrayList<String> list) {
		this.list = list;
		setChanged();
		notifyObservers("entry");
	}
	public double getOutput(){
		return output;
	}
	public ArrayList<String> getList(){
		return list;		
	}
}
