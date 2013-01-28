package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import registration.Register;

public class RegisterFrame extends JFrame{
	StartNbrField startNbrfield;
	StatusArea statusArea;
	RegisterButton registerButton;
	
	public RegisterFrame(){
		super("Registrering");
		setSize(640,480);
		setLayout(new BorderLayout());
		
		Register register = new Register(false);
		
		statusArea = new StatusArea();
		startNbrfield = new StartNbrField(register, statusArea);
		registerButton = new RegisterButton(startNbrfield);
		
		add(startNbrfield,BorderLayout.CENTER);
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
