package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import registration.Register;

public class RegisterFrame extends JFrame{
	StartNbrField startNbrField;
	StatusArea statusArea;
	RegisterButton registerButton;
	
	public RegisterFrame(){
		super("Registrering");
		setSize(640,480);
		setLayout(new BorderLayout());
		
		Register register = new Register(false);
		
		startNbrField = new StartNbrField(register);
		statusArea = new StatusArea(register, startNbrField);
		registerButton = new RegisterButton(startNbrField);
		
		add(startNbrField,BorderLayout.CENTER);
		add(statusArea,BorderLayout.PAGE_END);
		add(registerButton,BorderLayout.LINE_END);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new RegisterFrame();
	}

}
