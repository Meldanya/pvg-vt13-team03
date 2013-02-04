package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.SysexMessage;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import racer.RacerTime;
import registration.Register;

public class OKButton extends JButton implements ActionListener {
	

	private PopupStartNbrField field;
	
	public OKButton(PopupStartNbrField field) {
		super("OK");
		this.field = field;
		
		addActionListener(this);
		setPreferredSize(new Dimension(200, 80));
		setFont(new Font(null, Font.BOLD, 36));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		field.actionPerformed(e);
	}

}
