package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;

import racer.RacerTime;
import registration.Register;

public class StartNbrPopup extends JDialog {

	private RacerTime time;
	private Register register;
	
	public StartNbrPopup(Register register) {
		this.register = register;
		setModal(true);
		setLayout(new GridBagLayout());
		setResizable(false);
		time = new RacerTime();
		initComponents();
		pack();
		positionFrameInCenter();
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	// OBS: Duplicerad kod, inte säker på lösning än
	private void positionFrameInCenter(){

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		int w = getSize().width;
		int h = getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;

		setLocation(x, y);
	}
	
	private void initComponents(){
		GridBagConstraints gb;
		
		JLabel label = new JLabel();
		label.setText("<html><div style=\"text-align: center;\">" + "Tid (" + time.toString() + ") registrerad!<BR>Skriv in startnummer:</html>");
		label.setFont(new Font(null, Font.PLAIN, 25));
		gb = new GridBagConstraints();
		gb.gridwidth = 2;
		gb.gridx = 0;
		gb.gridy = 0;
		add(label, gb);

		PopupStartNbrField field = new PopupStartNbrField(register, time, this);
		field.setFont(new Font(null, Font.BOLD, 42));
		gb = new GridBagConstraints();
		gb.gridwidth = 2;
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.gridx = 0;
		gb.gridy = 1;
		add(field, gb);

		OKButton button1 = new OKButton(field);
		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 2;
		add(button1, gb);

		CancelButton button2 = new CancelButton(this);
		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 2;
		add(button2, gb);
	}

}
