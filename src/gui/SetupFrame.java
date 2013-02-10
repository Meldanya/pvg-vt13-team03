package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

public class SetupFrame extends AbstractFrame {

	public SetupFrame() {
		super("Start eller m\u00E5l?");
		setLayout(new GridBagLayout());
		setResizable(false);
		initComponents();
		pack();

		positionFrameInCenter();
	}

	private void initComponents() {
		GridBagConstraints gb;

		JLabel label = new JLabel();
		label.setText("<html><div style=\"text-align: center;\">" + "Vill du registrera<BR>start- eller m\u00E5ltid?</html>");
		label.setFont(new Font(null, Font.BOLD, 25));
		gb = new GridBagConstraints();
		gb.gridwidth = 3;
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
		
		MassStartButton button3 = new MassStartButton(this);
		gb = new GridBagConstraints();
		gb.gridx = 2;
		gb.gridy = 1;
		add(button3, gb);
	}

}
