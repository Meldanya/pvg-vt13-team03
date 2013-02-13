package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextField;

import racer.RacerTime;
import registration.Register;

public class PopupStartNbrField extends JTextField implements ActionListener {

	private Register register;
	private RacerTime time;
	private JDialog dialog;

	public PopupStartNbrField(Register register, RacerTime time, JDialog dialog) {
		this.register = register;
		this.time = time;
		this.dialog = dialog;
		addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!getText().trim().equals("")) {
			register.register(getText(), time);
			dialog.dispose();
			return;
		}
		setText("");
		requestFocus();

	}

}
