package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import registration.Register;

/**
 * @author dat11ajo
 * 
 */
public class StartNbrField extends JTextField implements ActionListener{
	Register register;
	public StartNbrField(Register register) {
	   // TODO: namnet är namnet på ett teckensnitt, inte ett påhittat. Det kan vara null; då används ett standardteckensnitt
		Font font = new Font("OurFont",Font.BOLD,96);
		setFont(font);
		this.register = register;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = this.getText();
		text = text.trim();
		if (!text.equals("")){
			register.register(text);
		}
	}
	
}
