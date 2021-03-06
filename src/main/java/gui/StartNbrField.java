package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import parser.Parser;
import registration.Register;

/**
 * @author dat11ajo
 * 
 */
public class StartNbrField extends JTextField implements ActionListener {
	private Register register;

	public StartNbrField(Register register) {
		// TODO: namnet är namnet på ett teckensnitt, inte ett påhittat. Det kan
		// vara null; då används ett standardteckensnitt
		Font font = new Font(null, Font.BOLD, 96);
		setFont(font);
		this.register = register;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String text = this.getText();
		text = text.trim();
		Parser parser = new Parser();
		
		if (text.equals("")) {
			new StartNbrPopup(register);
		} else {
			try {
				register.register(parser.parse(text));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), "Felaktig inmatning, se manualen för korrekt formatering", "Ett fel har uppstått",
				        JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
