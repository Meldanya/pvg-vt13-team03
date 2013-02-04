package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import racer.RacerTime;

public class StartNbrPopup extends AbstractFrame {

	private RacerTime time;
	
	public StartNbrPopup(String title) {
		super(title);
		setLayout(new GridBagLayout());
		setResizable(false);
		time = new RacerTime();
		initComponents();
		pack();
		positionFrameInCenter();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void initComponents(){
		GridBagConstraints gb;
		
		JLabel label = new JLabel();
		label.setText("<html><div style=\"text-align: center;\">" + "Tid registrerad!<BR>Skriv in startnummer</html>");
		label.setFont(new Font(null, Font.BOLD, 25));
		gb = new GridBagConstraints();
		gb.gridwidth = 2;
		gb.gridx = 0;
		gb.gridy = 0;
		add(label, gb);

		JTextField textField = new JTextField();
		textField.setFont(new Font(null, Font.BOLD, 42));
		gb = new GridBagConstraints();
		gb.gridwidth = 2;
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.gridx = 0;
		gb.gridy = 1;
		add(textField, gb);

		OKButton button1 = new OKButton();
		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 2;
		add(button1, gb);

		CancelButton button2 = new CancelButton();
		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 2;
		add(button2, gb);
	}

}
