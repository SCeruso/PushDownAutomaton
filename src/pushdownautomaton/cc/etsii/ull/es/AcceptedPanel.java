package pushdownautomaton.cc.etsii.ull.es;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class AcceptedPanel extends JPanel {
	Boolean accepted;
	
	public AcceptedPanel() {
		
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g.create());
		if (accepted != null) {
			if (accepted)
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.RED);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}
	public Boolean getAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	
}
