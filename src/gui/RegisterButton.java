package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class RegisterButton extends JButton implements ActionListener{
	StartNbrField field;
	public RegisterButton(StartNbrField field){
		super("Registrera");
		this.field=field;
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		field.actionPerformed(e);
	}
}
