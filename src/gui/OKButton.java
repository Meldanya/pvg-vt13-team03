package gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class OKButton extends JButton{

	public OKButton() {
		super("OK");
		setPreferredSize(new Dimension(200, 80));
		setFont(new Font(null, Font.BOLD, 36));
	}
}
