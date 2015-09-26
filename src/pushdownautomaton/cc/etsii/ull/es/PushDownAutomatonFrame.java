package pushdownautomaton.cc.etsii.ull.es;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PushDownAutomatonFrame extends JFrame{
	PushDownAutomaton automaton;
	JPanel panel;
	JPanel textPanel;
	AcceptedPanel acceptedPanel;
	JTextField textField;
	JButton button;
	Boolean accepted;
	
	public PushDownAutomatonFrame(PushDownAutomaton automaton) {
		setAutomaton(automaton);
		setPanel(new JPanel(new GridLayout(2, 1)));
		setTextPanel(new JPanel(new GridLayout(2, 1)));
		setTextField(new JTextField());
		setAcceptedPanel(new AcceptedPanel());
		setButton(new JButton("Comprobar"));
		
		getPanel().add(getTextField());
		getPanel().add(getButton());
		getTextPanel().add(getPanel());
		
		getTextPanel().add(getAcceptedPanel());
		
		getButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = getTextField().getText();
				
				getAutomaton().setInputString(text);
				accepted = getAutomaton().evaluateEntry();
				getAcceptedPanel().setAccepted(accepted);
				repaint();
			}
		});
		this.add(getTextPanel());
	}

	public PushDownAutomaton getAutomaton() {
		return automaton;
	}

	public void setAutomaton(PushDownAutomaton automaton) {
		this.automaton = automaton;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public JPanel getTextPanel() {
		return textPanel;
	}

	public void setTextPanel(JPanel textPanel) {
		this.textPanel = textPanel;
	}

	public AcceptedPanel getAcceptedPanel() {
		return acceptedPanel;
	}

	public void setAcceptedPanel(AcceptedPanel acceptedPanel) {
		this.acceptedPanel = acceptedPanel;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	
	
}
