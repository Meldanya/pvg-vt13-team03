package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class CancelButton extends JButton implements ActionListener{

	private JDialog dialog;
	
	public CancelButton(JDialog frame) {
		super("Avbryt");
		this.dialog = frame;
		addActionListener(this);
		setPreferredSize(new Dimension(200, 80));
		setFont(new Font(null, Font.BOLD, 36));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		dialog.dispose();
	}
	
	
}
