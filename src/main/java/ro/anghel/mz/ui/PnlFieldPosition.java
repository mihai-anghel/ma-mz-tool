package ro.anghel.mz.ui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import ro.anghel.ctr.Controller;

public class PnlFieldPosition extends JPanel {

	private static final long serialVersionUID = 1L;

	public PnlFieldPosition() {
		super();
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
	}

	public void addListener(final Controller<?, ?> aController) {
		for (final Component comp : getComponents()) {
			if (comp instanceof PnlPlayer) {
				comp.addMouseListener((MouseListener) aController);
			}
		}
	}

	public void removeListener(final Controller<?, ?> aController) {
		for (final Component comp : getComponents()) {
			if (comp instanceof PnlPlayer) {
				comp.removeMouseListener((MouseListener) aController);
			}
		}
	}
}
