package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public abstract class AbstractFrame extends JFrame {

	public AbstractFrame(String title) {
		super(title);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	protected void positionFrameInCenter() {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		int w = getSize().width;
		int h = getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;

		setLocation(x, y);

		setVisible(true);
	}
}
