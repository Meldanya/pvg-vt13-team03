package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import registration.Register;

public class StartNbrField extends JTextField implements ActionListener{
	StatusArea area;
	Register register;
	public StartNbrField(Register register, StatusArea area) {
		Font font = new Font("OurFont",Font.BOLD,96);
		setFont(font);
		this.area = area;
		this.register = register;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = this.getText();
		text = text.trim();
		if (text.equals("")){
			
		} else {
			register.register(Integer.valueOf(text), (int) (System.currentTimeMillis() / 1000));
//			area.printStartNbr(text,System.currentTimeMillis());
		}
		this.setText("");
	}
	
}
