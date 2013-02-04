package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartOrFinishFrame extends AbstractFrame {

	public StartOrFinishFrame() {
		super("Start eller mål?");
		setLayout(new GridBagLayout());
		initComponents();
		pack();

		positionFrameInCenter();
	}

	private void initComponents() {
		GridBagConstraints gb;

		JLabel label = new JLabel();
		label.setText("<HTML>Vill du registrera<BR>start- eller måltid?</HTML>");
		label.setFont(new Font(null, Font.BOLD, 25));
		gb = new GridBagConstraints();
		gb.gridwidth = 2;
		gb.ipady = 50;
		gb.gridx = 0;
		gb.gridy = 0;
		add(label, gb);

		StartButton button1 = new StartButton(this);
		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 1;
		add(button1, gb);

		FinishButton button2 = new FinishButton(this);
		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 1;
		add(button2, gb);
	}

	public static void main(String[] args) {
		new StartOrFinishFrame();
	}

}
