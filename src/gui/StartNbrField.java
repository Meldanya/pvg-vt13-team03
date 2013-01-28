package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class StartNbrField extends JTextField implements ActionListener{
	StatusArea area;
	public StartNbrField(StatusArea area) {
		Font font = new Font("OurFont",Font.BOLD,96);
		setFont(font);
		this.area = area;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		area.printStartNbr(this.getText(),System.currentTimeMillis());
		this.setText("");
	}
	
}
