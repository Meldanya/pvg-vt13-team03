package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartOrFinishFrame extends JFrame {

	public StartOrFinishFrame() {
		super("Start eller mål?");
		setLayout(new GridBagLayout());
		
		initComponents();
		pack();
		positionFrameInCenter();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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

	private void positionFrameInCenter() {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		int w = getSize().width;
		int h = getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;

		setLocation(x, y);
	}

	public static void main(String[] args) {
		new StartOrFinishFrame();
	}

}
