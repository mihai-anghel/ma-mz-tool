package ro.anghel.mz.ui;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;

import ro.anghel.ctr.Controller;
import ro.anghel.view.ControllableView;

public class DlgPlayerDetails extends JDialog implements ControllableView {

	private static final long serialVersionUID = 1L;

	private PnlPlayerDetails pnlPlayerDetails = null;

	public DlgPlayerDetails(final JFrame aParentWindow) {
		super(aParentWindow);
		setModal(true);
		pnlPlayerDetails = new PnlPlayerDetails();
		getContentPane().add(pnlPlayerDetails);
		setTitle("Player Details");
		final URL imgURL = getClass().getClassLoader()
				.getResource("gui/mz.gif");
		setIconImage(new ImageIcon(imgURL).getImage());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public PnlPlayerDetails getPnlPlayerDetails() {
		return pnlPlayerDetails;
	}

	@Override
	public void addListener(final Controller<?, ?> aController) {
	}

	@Override
	public void removeListener(final Controller<?, ?> aController) {
	}
}
