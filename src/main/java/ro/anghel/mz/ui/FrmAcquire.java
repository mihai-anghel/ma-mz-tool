package ro.anghel.mz.ui;

import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import ro.anghel.ctr.Controller;
import ro.anghel.view.ControllableView;

public class FrmAcquire extends JFrame implements ControllableView {

	private static final long serialVersionUID = 1L;

	private PnlAcquire pnlAcquire = null;

	public FrmAcquire() {
		pnlAcquire = new PnlAcquire();
		getContentPane().add(pnlAcquire);
		setTitle("MA ManagerZone Tool");
		setSize(640, 480);
		setLocationRelativeTo(null);
		final URL imgURL = getClass().getClassLoader()
				.getResource("gui/mz.gif");
		setIconImage(new ImageIcon(imgURL).getImage());
	}

	public PnlAcquire getPnlAcquire() {
		return pnlAcquire;
	}

	@Override
	public void addListener(final Controller<?, ?> aController) {
		pnlAcquire.getBtnOk().addActionListener((ActionListener) aController);
		pnlAcquire.getBtnCancel()
				.addActionListener((ActionListener) aController);
		pnlAcquire.getBtnPaste()
				.addActionListener((ActionListener) aController);
	}

	@Override
	public void removeListener(final Controller<?, ?> aController) {
		pnlAcquire.getBtnOk()
				.removeActionListener((ActionListener) aController);
		pnlAcquire.getBtnCancel()
				.removeActionListener((ActionListener) aController);
		pnlAcquire.getBtnPaste()
				.removeActionListener((ActionListener) aController);
	}
}
