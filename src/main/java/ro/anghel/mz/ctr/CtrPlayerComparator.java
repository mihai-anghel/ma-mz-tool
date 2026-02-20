package ro.anghel.mz.ctr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import ro.anghel.ctr.AbstractController;
import ro.anghel.ctr.Controller;
import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.Team;
import ro.anghel.mz.ui.PnlPlayerComparator;
import ro.anghel.mz.ui.PnlPlayerDetails;
import ro.anghel.view.ControllableView;

public class CtrPlayerComparator<T_PrevCtr extends Controller<?, ?>> extends
		AbstractController<CtrPlayerComparator<T_PrevCtr>, T_PrevCtr, CtrPlayerComparator.CtrKey, CtrPlayerComparator.BoKey, CtrPlayerComparator.ViewKey>
		implements ActionListener {

	public enum CtrKey {
	}

	public enum BoKey {
		TEAM
	}

	public enum ViewKey {
		PNL_PLAYER_COMPARATOR
	}

	private CtrPlayerInfo<CtrPlayerComparator<T_PrevCtr>> ctrPlayerInfo1 = null;

	private CtrPlayerInfo<CtrPlayerComparator<T_PrevCtr>> ctrPlayerInfo2 = null;

	public CtrPlayerComparator(final T_PrevCtr aPreviousController,
			final PnlPlayerComparator aPnlPlayerComparator, final Team aTeam) {
		super(aPreviousController,
				new ControllableView[] { aPnlPlayerComparator },
				new Object[] { aTeam });
	}

	@Override
	protected void addSubControllers() {
	}

	@Override
	protected void addViews(final ControllableView[] aViews) {
		putView(ViewKey.PNL_PLAYER_COMPARATOR, aViews[0]);
	}

	@Override
	protected void addBusinessObjects(final Object[] aBusinessObjects) {
		putBusinessObject(BoKey.TEAM, aBusinessObjects[0]);
	}

	@Override
	protected void disposeController() {
		if (ctrPlayerInfo1 != null) {
			ctrPlayerInfo1.dispose();
		}
		if (ctrPlayerInfo2 != null) {
			ctrPlayerInfo2.dispose();
		}
	}

	@Override
	protected void initViews() {
		final PnlPlayerComparator pnlPlayerComparator = getPnlPlayerComparator();
		final Team team = getTeam();
		final Player[] players = team.getPlayers();

		@SuppressWarnings("unchecked")
		final JComboBox<Player> cmbPlayer1 = (JComboBox<Player>) pnlPlayerComparator
				.getCmbPlayer1();
		cmbPlayer1.setModel(new DefaultComboBoxModel<>(players));
		final Player player1 = (Player) cmbPlayer1.getSelectedItem();
		if (player1 != null) {
			if (ctrPlayerInfo1 != null) {
				ctrPlayerInfo1.dispose();
			}
			final PnlPlayerDetails pnlPlayerDetails1 = pnlPlayerComparator
					.getPnlPlayerDetails1();
			ctrPlayerInfo1 = new CtrPlayerInfo<>(this, pnlPlayerDetails1,
					player1);
			ctrPlayerInfo1.init();
		}

		@SuppressWarnings("unchecked")
		final JComboBox<Player> cmbPlayer2 = (JComboBox<Player>) pnlPlayerComparator
				.getCmbPlayer2();
		cmbPlayer2.setModel(new DefaultComboBoxModel<>(players));
		final Player player2 = (Player) cmbPlayer2.getSelectedItem();
		if (player2 != null) {
			if (ctrPlayerInfo2 != null) {
				ctrPlayerInfo2.dispose();
			}
			final PnlPlayerDetails pnlPlayerDetails2 = pnlPlayerComparator
					.getPnlPlayerDetails2();
			ctrPlayerInfo2 = new CtrPlayerInfo<>(this, pnlPlayerDetails2,
					player2);
			ctrPlayerInfo2.init();
		}
	}

	public Team getTeam() {
		return (Team) getBusinessObject(BoKey.TEAM);
	}

	private PnlPlayerComparator getPnlPlayerComparator() {
		return (PnlPlayerComparator) getView(ViewKey.PNL_PLAYER_COMPARATOR);
	}

	@Override
	protected void initBusinessObjects() {
	}

	@Override
	protected void initController() {
	}

	@Override
	protected void setVisible(final boolean aVisible) {
	}

	@Override
	protected void updateController() {
	}

	@Override
	public void actionPerformed(final ActionEvent aEvent) {
		final PnlPlayerComparator pnlPlayerComparator = getPnlPlayerComparator();
		@SuppressWarnings("unchecked")
		final JComboBox<Player> cmbPlayer1 = (JComboBox<Player>) pnlPlayerComparator
				.getCmbPlayer1();
		if (cmbPlayer1 == aEvent.getSource()) {
			final Player player1 = (Player) cmbPlayer1.getSelectedItem();
			if (player1 != null) {
				if (ctrPlayerInfo1 != null) {
					ctrPlayerInfo1.dispose();
				}
				final PnlPlayerDetails pnlPlayerDetails1 = pnlPlayerComparator
						.getPnlPlayerDetails1();
				ctrPlayerInfo1 = new CtrPlayerInfo<>(this, pnlPlayerDetails1,
						player1);
				ctrPlayerInfo1.init();
			}
		}

		@SuppressWarnings("unchecked")
		final JComboBox<Player> cmbPlayer2 = (JComboBox<Player>) pnlPlayerComparator
				.getCmbPlayer2();
		if (cmbPlayer2 == aEvent.getSource()) {
			final Player player2 = (Player) cmbPlayer2.getSelectedItem();
			if (player2 != null) {
				if (ctrPlayerInfo2 != null) {
					ctrPlayerInfo2.dispose();
				}
				final PnlPlayerDetails pnlPlayerDetails2 = pnlPlayerComparator
						.getPnlPlayerDetails2();
				ctrPlayerInfo2 = new CtrPlayerInfo<>(this, pnlPlayerDetails2,
						player2);
				ctrPlayerInfo2.init();
			}
		}
	}

}
