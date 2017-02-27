package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import controller.Controller;
import model.Model;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CalcView implements Observer{
	private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bDec, bAdd, bMin, bMult, bDiv, bEq, bClear, bBack, bMadd, bMre;
	private Text screen;
	private final Controller c;
	private final Model m;

	//Create the view with a given model and controller
	public CalcView (Model myModel, Controller controller) {
		c = controller;
		m = myModel;
	}
	//Create a shell to display to the user
	public void showView(){
		Display display = new Display();
		Shell shell = new Shell( display );
		shell.setText( "SWT Calculator" ); 
		shell.setSize( 400, 500 );
		
		initializeComponents(shell);
		setListeners();
		
		//Center the shell and display it
		Monitor m = display.getPrimaryMonitor();
	    Rectangle monitorSize = m.getBounds();
	    Rectangle windowSize = shell.getBounds();
	    int x = monitorSize.x + (monitorSize.width - windowSize.width) / 2;
	    int y = monitorSize.y + (monitorSize.height - windowSize.height) / 2;
	    shell.setLocation(x, y);
	    shell.setMinimumSize(350, 400);
	    shell.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		shell.open();
		while( !shell.isDisposed() ) { if( !display.readAndDispatch() ) { display.sleep();
		}
		}
	}
	//Called whenever changes are made to the model
	@Override
	public void update(Observable o, Object arg) {
		//Convert to a long if possible to remove unnecessary decimals
		if(arg.equals("output")){
			if(isLongable(m.getOutput())){
				Long output = (long) m.getOutput();
				screen.setText(output.toString());
			}else{
				Double output = m.getOutput();
				screen.setText(output.toString());
			}
		//Format the expression list to display more cleanly
		}else if(arg.equals("entry")){
			String formattedList = formatList(m.getList());
			screen.setText(formattedList);
		}else if(arg.equals("error")){
			screen.setText("Syntax error in expression");
		}
		//Display 0 if there are no results to display
		if(screen.getText().length() == 0){
			screen.setText("0");
		}
		
	}
	//Set up the screen components
	public void initializeComponents(final Shell shell){
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.marginBottom = 5;
		gridLayout.marginHeight = 5;
		gridLayout.marginLeft = 5;
		gridLayout.marginRight = 5;
		gridLayout.verticalSpacing = 10;
		gridLayout.horizontalSpacing = 10;
		gridLayout.makeColumnsEqualWidth = true;
		shell.setLayout(gridLayout);
		GridData screenData = new GridData();
	    screenData.horizontalSpan = 4;
	    screenData.grabExcessHorizontalSpace = true;
	    screenData.horizontalAlignment = GridData.FILL;
	    screen = new Text(shell, SWT.SINGLE | SWT.BORDER);
	    screen.setOrientation(SWT.RIGHT_TO_LEFT);
	    screen.setTextDirection(SWT.LEFT_TO_RIGHT);
	    screen.setText("0");
	    screen.setEditable(false);
	    screen.setLayoutData(screenData);
	    GridData buttonData = new GridData();
	    buttonData.minimumHeight = 50;
	    buttonData.minimumWidth = 70;
	    buttonData.grabExcessHorizontalSpace = true;
	    buttonData.grabExcessVerticalSpace = true;
	    buttonData.horizontalAlignment = GridData.FILL;
	    buttonData.verticalAlignment = GridData.FILL;
	    bClear = new Button(shell, SWT.PUSH);
	    bClear.setLayoutData(buttonData);
	    bClear.setText("CE");
		bBack = new Button(shell, SWT.PUSH);
		bBack.setLayoutData(buttonData);
		bBack.setText("<-");
		bMadd = new Button(shell, SWT.PUSH);
		bMadd.setLayoutData(buttonData);
		bMadd.setText("M-Add");
		bMre = new Button(shell, SWT.PUSH);
		bMre.setLayoutData(buttonData);
		bMre.setText("M-Recall");
		b7 = new Button(shell, SWT.PUSH);
		b7.setLayoutData(buttonData);
		b7.setText("7");
		b8 = new Button(shell, SWT.PUSH);
		b8.setLayoutData(buttonData);
		b8.setText("8");
		b9 = new Button(shell, SWT.PUSH);
		b9.setLayoutData(buttonData);
		b9.setText("9");
		bDiv = new Button(shell, SWT.PUSH);
		bDiv.setLayoutData(buttonData);
		bDiv.setText(" / ");
		b4 = new Button(shell, SWT.PUSH);
		b4.setLayoutData(buttonData);
		b4.setText("4");
		b5 = new Button(shell, SWT.PUSH);
		b5.setLayoutData(buttonData);
		b5.setText("5");
		b6 = new Button(shell, SWT.PUSH);
		b6.setLayoutData(buttonData);
		b6.setText("6");
		bMult = new Button(shell, SWT.PUSH);
		bMult.setLayoutData(buttonData);
		bMult.setText(" * ");
		b1 = new Button(shell, SWT.PUSH);
		b1.setLayoutData(buttonData);
		b1.setText("1");
		b2 = new Button(shell, SWT.PUSH);
		b2.setLayoutData(buttonData);
		b2.setText("2");
		b3 = new Button(shell, SWT.PUSH);
		b3.setLayoutData(buttonData);
		b3.setText("3");
		bMin = new Button(shell, SWT.PUSH);
		bMin.setLayoutData(buttonData);
		bMin.setText(" - ");
		b0 = new Button(shell, SWT.PUSH);
		b0.setLayoutData(buttonData);
		b0.setText("0");
		bDec = new Button(shell, SWT.PUSH);
		bDec.setLayoutData(buttonData);
		bDec.setText(" . ");
		bAdd = new Button(shell, SWT.PUSH);
		bAdd.setLayoutData(buttonData);
		bAdd.setText(" + ");
		bEq = new Button(shell, SWT.PUSH);
		bEq.setLayoutData(buttonData);
		bEq.setText(" = ");
	}
	//Set the listeners in the controller to listen to the components
	public void setListeners(){
		b0.addSelectionListener(c.getNumPressListener("0"));
		b1.addSelectionListener(c.getNumPressListener("1"));
		b2.addSelectionListener(c.getNumPressListener("2"));
		b3.addSelectionListener(c.getNumPressListener("3"));
		b4.addSelectionListener(c.getNumPressListener("4"));
		b5.addSelectionListener(c.getNumPressListener("5"));
		b6.addSelectionListener(c.getNumPressListener("6"));
		b7.addSelectionListener(c.getNumPressListener("7"));
		b8.addSelectionListener(c.getNumPressListener("8"));
		b9.addSelectionListener(c.getNumPressListener("9"));
		bAdd.addSelectionListener(c.getNumPressListener("+"));
		bMin.addSelectionListener(c.getNumPressListener("-"));
		bMult.addSelectionListener(c.getNumPressListener("*"));
		bDiv.addSelectionListener(c.getNumPressListener("/"));
		bDec.addSelectionListener(c.getNumPressListener("."));
		bEq.addSelectionListener(c.getEqualsListener());
		bClear.addSelectionListener(c.getClearPressListener());
		bBack.addSelectionListener(c.getBackPressListener());
		bMadd.addSelectionListener(c.getMemoryAddListener());
		bMre.addSelectionListener(c.getMemoryRecallListener());
		
	}
	//Yeah it's what you think
	private boolean isLongable(Double d){
		if(d %1 == 0){
			return true;
 		}
		return false;
	}
	//Take a list of strings and return one string of the parts
	private String formatList(ArrayList<String> list){
		String result = "";
		for(String s:list){
			result = result + s;			
		}
		return result;
	}

}
