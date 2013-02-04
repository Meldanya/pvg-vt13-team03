package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CancelButton extends JButton implements ActionListener{

	private JFrame frame;
	
	public CancelButton(JFrame frame) {
		super("Avbryt");
		this.frame = frame;
		addActionListener(this);
		setPreferredSize(new Dimension(200, 80));
		setFont(new Font(null, Font.BOLD, 36));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		frame.dispose();
	}
	
	
}
