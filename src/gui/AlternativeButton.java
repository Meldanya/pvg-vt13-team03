package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class AlternativeButton extends JButton implements ActionListener {

	protected JFrame frame;

	public AlternativeButton(JFrame frame) {
		this.frame = frame;
		addActionListener(this);
		setText(getTitle());
		setFont(new Font(null, Font.BOLD, 30));
		setPreferredSize(new Dimension(200, 120));
	}

	protected abstract String getTitle();
}
