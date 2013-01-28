package gui;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class StatusArea extends JScrollPane {
	JTextArea area;
	public StatusArea(){
		area = new JTextArea(4,0);
		area.setEditable(false);
		this.setViewportView(area);
	}
	
	protected void printStartNbr(String startNbr, long time){
		println("Startnummer " + startNbr + " har fått tiden " + time + " registrerad.");
	}
	
	private void println(String line){
		area.append("\n" + line);
	}
}
