package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import registration.Register;

/**
 * @author dat11ajo
 *
 * Main class that launches the GUI.
 */
public class RegisterFrame extends AbstractFrame{
	StartNbrField startNbrField;
	StatusArea statusArea;
	RegisterButton registerButton;
	
	public RegisterFrame(String mode, boolean start){
		super("Registrering av " + mode + "tider");
		setSize(640,480);
		setLayout(new BorderLayout());
		
		// The order in which the following object are initialized is important. 
		Register register = new Register(start);
		startNbrField = new StartNbrField(register);
		statusArea = new StatusArea(register, startNbrField);
		registerButton = new RegisterButton(startNbrField);
		
		add(startNbrField,BorderLayout.CENTER);
		add(statusArea,BorderLayout.PAGE_END);
		add(registerButton,BorderLayout.LINE_END);
		positionFrameInCenter();
	}
	
	public static void main(String[] args) {
		new RegisterFrame("Mål", false);
	}

}
