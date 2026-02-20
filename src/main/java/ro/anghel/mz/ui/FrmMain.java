package ro.anghel.mz.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.event.ChangeListener;

import ro.anghel.ctr.Controller;
import ro.anghel.mz.domain.Position;
import ro.anghel.view.ControllableView;

public class FrmMain extends JFrame implements ControllableView {

	private static final long serialVersionUID = 1L;

	private PnlMain pnlMain = null;

	public FrmMain() {
		pnlMain = new PnlMain();
		getContentPane().add(pnlMain);
		setTitle("MA ManagerZone Tool");
		final Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		screenSize.setSize(screenSize.getWidth(), screenSize.getHeight() - 50);
		setSize(screenSize);
		setLocationRelativeTo(null);
		final URL imgURL = getClass().getClassLoader()
				.getResource("gui/mz.gif");
		setIconImage(new ImageIcon(imgURL).getImage());
		pnlMain.getPnlOverview().setTitle("Overview");
	}

	public PnlMain getPnlMain() {
		return pnlMain;
	}

	@Override
	public void addListener(final Controller<?, ?> aController) {
		pnlMain.getTabMain().addChangeListener((ChangeListener) aController);

		final PnlTableView pnlOV = pnlMain.getPnlOverview();
		pnlOV.getTblData().addMouseListener((MouseListener) aController);

		final PnlPositions pnlPos = pnlMain.getPnlPositions();
		for (final Position pos : Position.values()) {
			pnlPos.getPnlForPosition(pos.name()).getTblData()
					.addMouseListener((MouseListener) aController);
		}

		final PnlFormations pnlFm = pnlMain.getPnlFormations();
		pnlFm.getPnlSquad().getTblData()
				.addMouseListener((MouseListener) aController);
		pnlFm.getPnlSubs().getTblData()
				.addMouseListener((MouseListener) aController);
		pnlFm.getPnlPenalty().getTblData()
				.addMouseListener((MouseListener) aController);
		pnlFm.getPnlFreekick().getTblData()
				.addMouseListener((MouseListener) aController);
	}

	@Override
	public void removeListener(final Controller<?, ?> aController) {
		pnlMain.getTabMain().removeChangeListener((ChangeListener) aController);

		final PnlTableView pnlOV = pnlMain.getPnlOverview();
		pnlOV.getTblData().removeMouseListener((MouseListener) aController);

		final PnlPositions pnlPos = pnlMain.getPnlPositions();
		for (final Position pos : Position.values()) {
			pnlPos.getPnlForPosition(pos.name()).getTblData()
					.removeMouseListener((MouseListener) aController);
		}

		final PnlFormations pnlFm = pnlMain.getPnlFormations();
		pnlFm.getPnlSquad().getTblData()
				.removeMouseListener((MouseListener) aController);
		pnlFm.getPnlSubs().getTblData()
				.removeMouseListener((MouseListener) aController);
		pnlFm.getPnlPenalty().getTblData()
				.removeMouseListener((MouseListener) aController);
		pnlFm.getPnlFreekick().getTblData()
				.removeMouseListener((MouseListener) aController);
	}
}
