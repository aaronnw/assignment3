package view;

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
	Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bDec, bAdd, bMin, bMult, bDiv, bEq;
	Text screen;
	Controller c;
	Model m;

	public CalcView (Model myModel, Controller controller) {
		c = controller;
		m = myModel;
	}
	public void showView(){
		Display display = new Display();
		Shell shell = new Shell( display );
		shell.setText( "SWT Calculator" ); 
		shell.setSize( 200, 300 );
		
		
		initializeComponents(shell);
		setListeners();
		
		//Center the shell and display it
		Monitor m = display.getPrimaryMonitor();
	    Rectangle monitorSize = m.getBounds();
	    Rectangle windowSize = shell.getBounds();
	    int x = monitorSize.x + (monitorSize.width - windowSize.width) / 2;
	    int y = monitorSize.y + (monitorSize.height - windowSize.height) / 2;
	    shell.setLocation(x, y);
		shell.open();
		while( !shell.isDisposed() ) { if( !display.readAndDispatch() ) { display.sleep();
		}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		screen.setText(m.getExpression());
		
	}
	public void initializeComponents(final Shell shell){
		GridLayout gridLayout = new GridLayout(4, false);
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
	    buttonData.minimumHeight = 10;
	    buttonData.minimumWidth = 10;
	    buttonData.grabExcessHorizontalSpace = true;
	    buttonData.grabExcessVerticalSpace = true;
	    buttonData.horizontalAlignment = GridData.FILL;
	    buttonData.verticalAlignment = GridData.FILL;
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
	}
}
