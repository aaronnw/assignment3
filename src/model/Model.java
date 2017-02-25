package model;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable{
	
	ArrayList<String> list;
	Double output; 
	ArrayList<String> memory;

	public Model(){
		list = new ArrayList<String>();
		memory = new ArrayList<String>(); 
		output = 0.0;
	}
	public void addChar(char c){
		setChanged();
		notifyObservers("entry");
	}
	public ArrayList<String> getList(){
		return list;		
	}
	public void addToList(String s){
		list.add(s);
		setChanged();
		notifyObservers("entry");
	}
	public void backSpace(){
		if(list.size()>0){
			String lastString = list.get(list.size()-1);
			if(lastString.length() == 1){
				list.remove(list.size()-1);
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
	public void storeExpression(){
		memory = new ArrayList<String>(list);
	}
	public void recallExpression(){
		list.addAll(memory);
		setChanged();
		notifyObservers("entry");
	}
}
