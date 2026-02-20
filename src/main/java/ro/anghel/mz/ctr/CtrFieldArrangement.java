package ro.anghel.mz.ctr;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import ro.anghel.ctr.AbstractController;
import ro.anghel.ctr.Controller;
import ro.anghel.mz.domain.Foot;
import ro.anghel.mz.domain.Formation;
import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.PlayerPosition;
import ro.anghel.mz.domain.Team;
import ro.anghel.mz.ui.PnlField;
import ro.anghel.mz.ui.PnlFieldPosition;
import ro.anghel.mz.ui.PnlPlayer;
import ro.anghel.mz.ui.renderer.ColorCalculator;
import ro.anghel.view.ControllableView;

public class CtrFieldArrangement<T_PrevCtr extends Controller<?, ?>> extends
		AbstractController<CtrFieldArrangement<T_PrevCtr>, T_PrevCtr, CtrFieldArrangement.CtrKey, CtrFieldArrangement.BoKey, CtrFieldArrangement.ViewKey>
		implements MouseListener {

	public enum CtrKey {
	}

	public enum BoKey {
		FORMATION
	}

	public enum ViewKey {
		PNL_FIELD
	}

	public CtrFieldArrangement(final T_PrevCtr aPreviousController,
			final PnlField aPnlField, final Formation aFormation) {
		super(aPreviousController, new ControllableView[] { aPnlField },
				new Object[] { aFormation });
	}

	@Override
	protected void addBusinessObjects(final Object[] aBusinessObjects) {
		putBusinessObject(BoKey.FORMATION, aBusinessObjects[0]);
	}

	@Override
	protected void addSubControllers() {
	}

	@Override
	protected void addViews(final ControllableView[] aViews) {
		putView(ViewKey.PNL_FIELD, aViews[0]);
	}

	public Formation getFormation() {
		return (Formation) getBusinessObject(BoKey.FORMATION);
	}

	protected PnlField getPnlField() {
		return (PnlField) getView(ViewKey.PNL_FIELD);
	}

	@Override
	protected void disposeController() {
		final PnlField pnlField = getPnlField();
		pnlField.removeAllPnlPlayers();
	}

	@Override
	protected void initViews() {
		final PnlField pnlField = getPnlField();
		final Formation formation = getFormation();

		for (final PlayerPosition playerPos : formation.getStarting()) {
			final PnlPlayer pnlPlayer = createPnlPlayer(playerPos);
			addPlayerPositionToField(playerPos, pnlField, pnlPlayer);
		}
	}

	private static PnlPlayer createPnlPlayer(final PlayerPosition playerPos) {
		final Player player = playerPos.getPlayer();
		final Team team = player.getTeam();

		final String trainingCamp = player.getTrainingCamp();
		final Color priColor = Color.decode(team.getPriColor());
		final Color secColor = Color.decode(team.getSecColor());
		final int shirtPattern = team.getShirtPattern();
		final Color nrColor = ColorCalculator.calculateNrColor(shirtPattern,
				priColor, secColor);
		return new PnlPlayer(shirtPattern, player.getNumber(), player.getName(),
				playerPos.getPosition().name(), priColor, secColor, nrColor,
				player.getInjury().getInjurySeverity().niceName(),
				trainingCamp != null && !trainingCamp.isEmpty());
	}

	private static void addPlayerPositionToField(final PlayerPosition playerPos,
			final PnlField pnlField, final PnlPlayer pnlPlayer) {

		switch (playerPos.getPosition()) {
		case GK -> pnlField.getPnlPosGK().add(pnlPlayer);
		case CB -> pnlField.getPnlPosCB().add(pnlPlayer);
		case DM -> pnlField.getPnlPosDM().add(pnlPlayer);
		case CM -> pnlField.getPnlPosCM().add(pnlPlayer);
		case AM -> pnlField.getPnlPosAM().add(pnlPlayer);
		case FW -> pnlField.getPnlPosFW().add(pnlPlayer);

		case WB -> addByPreferredFoot(playerPos.getPlayer().getFoot(),
				pnlField.getPnlPosWB1(), pnlField.getPnlPosWB2(), pnlPlayer);

		case WM -> addByPreferredFoot(playerPos.getPlayer().getFoot(),
				pnlField.getPnlPosWM1(), pnlField.getPnlPosWM2(), pnlPlayer);
		}
	}

	private static void addByPreferredFoot(final Foot foot,
			final PnlFieldPosition pnlLeft, final PnlFieldPosition pnlRight,
			final PnlPlayer pnlPlayer) {
		switch (foot) {
		case LEFT -> {
			if (pnlLeft.getComponentCount() == 0) {
				pnlLeft.add(pnlPlayer);
			} else if (pnlRight.getComponentCount() == 0) {
				pnlRight.add(pnlPlayer);
			}
		}
		case RIGHT -> {
			if (pnlRight.getComponentCount() == 0) {
				pnlRight.add(pnlPlayer);
			} else if (pnlLeft.getComponentCount() == 0) {
				pnlLeft.add(pnlPlayer);
			}
		}
		case BOTH -> {
			if (pnlLeft.getComponentCount() == 0) {
				pnlLeft.add(pnlPlayer);
			} else if (pnlRight.getComponentCount() == 0) {
				pnlRight.add(pnlPlayer);
			}
		}
		}
	}

	@Override
	protected void initBusinessObjects() {
	}

	@Override
	protected void initController() {
	}

	@Override
	protected void setVisible(final boolean aVisible) {
		if (aVisible) {
			final PnlField pnlField = getPnlField();
			// pnlField.doLayout();
			pnlField.validate();
		}
	}

	@Override
	protected void updateController() {
	}

	@Override
	public void mouseClicked(final MouseEvent aEvent) {
		final Object src = aEvent.getSource();
		if (src instanceof final PnlPlayer pnlPlayer) {
			final Formation formation = getFormation();
			final Player player = formation.searchPlayerInStarting(
					Short.parseShort(pnlPlayer.getLblNumber().getText()));
			if (player != null) {
				final CtrPlayerDetails<CtrFieldArrangement<T_PrevCtr>> ctrPlayerDetails = new CtrPlayerDetails<>(
						this, getParentFrame(pnlPlayer), player);
				ctrPlayerDetails.init();
				ctrPlayerDetails.dispose();
			}
		}
	}

	private static JFrame getParentFrame(final Container aContainer) {
		for (Container c = aContainer; c != null; c = c.getParent()) {
			if (c instanceof JFrame) {
				return (JFrame) c;
			}
		}
		return null;
	}

	@Override
	public void mouseEntered(final MouseEvent aEvent) {
	}

	@Override
	public void mouseExited(final MouseEvent aEvent) {
	}

	@Override
	public void mousePressed(final MouseEvent aEvent) {
	}

	@Override
	public void mouseReleased(final MouseEvent aEvent) {
	}

}
