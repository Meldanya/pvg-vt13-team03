package gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class CancelButton extends JButton{

	public CancelButton() {
		super("Avbryt");
		setPreferredSize(new Dimension(200, 80));
		setFont(new Font(null, Font.BOLD, 36));
	}
}
