import controller.Controller;
import model.Model;
import view.CalcView;

public class Driver {
	public static void main(String[] args){
		 Model myModel = new Model();
	     Controller controller  = new Controller(myModel);
	     CalcView cView = new CalcView(myModel, controller);
	     myModel.addObserver(cView);
	     cView.showView();
	  
	}
}
