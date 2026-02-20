package ro.anghel.mz.ctr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import ro.anghel.ctr.AbstractController;
import ro.anghel.ctr.Controller;
import ro.anghel.mz.domain.PositionCalculator;
import ro.anghel.mz.domain.Team;
import ro.anghel.mz.parser.ProfilePageTeamParserOld;
import ro.anghel.mz.parser.SummaryPageTeamParser;
import ro.anghel.mz.parser.TestDataGenerator;
import ro.anghel.mz.ui.FrmAcquire;
import ro.anghel.mz.ui.PnlAcquire;
import ro.anghel.view.ControllableView;

public class CtrAcquire<T_PrevCtr extends Controller<?, ?>> extends
		AbstractController<CtrAcquire<T_PrevCtr>, T_PrevCtr, CtrAcquire.CtrKey, CtrAcquire.BoKey, CtrAcquire.ViewKey>
		implements ActionListener {

	private static final short NR_OF_GENERATED_PLAYERS = 30;

	public enum CtrKey {
	}

	public enum BoKey {
		TEAM
	}

	public enum ViewKey {
		FRM_ACQUIRE
	}

	public CtrAcquire(final T_PrevCtr aPreviousController) {
		super(aPreviousController, new ControllableView[] { new FrmAcquire() },
				new Object[] { new Team() });
	}

	public static void main(final String[] aArgs) {
		Locale.setDefault(Locale.ENGLISH);
		// javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel(
					"com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (final Exception exc) {
			exc.printStackTrace();
		}
		start();
	}

	private static void start() {
		new CtrAcquire<>(null).init();
	}

	@Override
	protected void addBusinessObjects(final Object[] aBusinessObjects) {
		putBusinessObject(BoKey.TEAM, aBusinessObjects[0]);
	}

	@Override
	protected void addSubControllers() {
	}

	@Override
	protected void addViews(final ControllableView[] aViews) {
		putView(ViewKey.FRM_ACQUIRE, aViews[0]);
	}

	public Team getTeam() {
		return (Team) getBusinessObject(BoKey.TEAM);
	}

	protected FrmAcquire getFrmAcquire() {
		return (FrmAcquire) getView(ViewKey.FRM_ACQUIRE);
	}

	@Override
	protected void disposeController() {
		getFrmAcquire().dispose();
	}

	@Override
	protected void initViews() {
		getFrmAcquire()
				.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	@Override
	protected void initBusinessObjects() {
		PositionCalculator.load();
	}

	@Override
	protected void initController() {
	}

	@Override
	protected void setVisible(final boolean aVisible) {
		getFrmAcquire().setVisible(aVisible);
	}

	@Override
	protected void updateController() {
	}

	@Override
	public void actionPerformed(final ActionEvent aEvent) {
		final FrmAcquire frm = getFrmAcquire();
		final PnlAcquire pnlAcquire = frm.getPnlAcquire();

		final JButton btnPaste = pnlAcquire.getBtnPaste();
		boolean bOK = false;
		if (btnPaste == aEvent.getSource()) {
			pnlAcquire.getTxtPasted().paste();
			bOK = true;
		} else {
			final JButton btnOK = pnlAcquire.getBtnOk();
			bOK = btnOK == aEvent.getSource();
		}
		if (bOK) {
			final Team team = getTeam();
			final String stringToParse = pnlAcquire.getTxtPasted().getText();
			final ProfilePageTeamParserOld profileParser = new ProfilePageTeamParserOld(
					team, stringToParse);
			profileParser.parse();
			if (team.getPlayers().length == 0) {
				final SummaryPageTeamParser summaryParser = new SummaryPageTeamParser(
						team, stringToParse);
				summaryParser.parse();
			}
			if (team.getPlayers().length == 0) {
				final TestDataGenerator generator = new TestDataGenerator(team);
				generator.generateTestData(NR_OF_GENERATED_PLAYERS);
			}
			if (team.getPlayers().length > 0) {
				dispose();
				new CtrMain<>(this, team).init();
			}
			return;
		}
		final JButton btnCancel = pnlAcquire.getBtnCancel();
		if (btnCancel == aEvent.getSource()) {
			dispose();
			return;
		}
	}

}
