package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegisterFrame extends JFrame{
	StartNbrField startNbrfield;
	StatusArea statusArea;
	RegisterButton registerButton;
	
	public RegisterFrame(){
		super("Registrering");
		setSize(640,480);
		setLayout(new BorderLayout());
		
		statusArea = new StatusArea();
		startNbrfield = new StartNbrField(statusArea);
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
