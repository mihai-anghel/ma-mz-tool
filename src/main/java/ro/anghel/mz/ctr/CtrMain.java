package ro.anghel.mz.ctr;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;

import ro.anghel.ctr.AbstractController;
import ro.anghel.ctr.Controller;
import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.PlayerPosition;
import ro.anghel.mz.domain.Team;
import ro.anghel.mz.ui.FrmMain;
import ro.anghel.mz.ui.PnlMain;
import ro.anghel.mz.ui.TblCustomized;
import ro.anghel.ui.model.EnumBasedTableModel;
import ro.anghel.view.ControllableView;

public class CtrMain<T_PrevCtr extends Controller<?, ?>> extends
		AbstractController<CtrMain<T_PrevCtr>, T_PrevCtr, CtrMain.CtrKey, CtrMain.BoKey, CtrMain.ViewKey>
		implements ChangeListener, MouseListener {

	public enum CtrKey {
		OVERVIEW, POSITIONS, FORMATIONS, PLAYER_COMPARATOR, CONFIGURATOR
	}

	public enum BoKey {
		TEAM
	}

	public enum ViewKey {
		FRM_MAIN
	}

	public CtrMain(final T_PrevCtr aPreviousController, final Team aTeam) {
		super(aPreviousController, new ControllableView[] { new FrmMain() },
				new Object[] { aTeam });
	}

	@Override
	protected void addBusinessObjects(final Object[] aBusinessObjects) {
		putBusinessObject(BoKey.TEAM, aBusinessObjects[0]);
	}

	@Override
	protected void addSubControllers() {
		final Team team = getTeam();
		final FrmMain frmMain = getFrmMain();
		final PnlMain pnlMain = frmMain.getPnlMain();
		putSubController(CtrKey.OVERVIEW, new CtrOverview<>(
				this, pnlMain.getPnlOverview(), team));
		putSubController(CtrKey.POSITIONS, new CtrPositions<>(
				this, pnlMain.getPnlPositions(), team));
		putSubController(CtrKey.FORMATIONS,
				new CtrFormations<>(this,
						pnlMain.getPnlFormations(), team));
		putSubController(CtrKey.PLAYER_COMPARATOR,
				new CtrPlayerComparator<>(this,
						pnlMain.getPnlPlayerComparator(), team));
		putSubController(CtrKey.CONFIGURATOR,
				new CtrConfigurator<>(this,
						pnlMain.getPnlConfigurator()));

	}

	@Override
	protected void addViews(final ControllableView[] aViews) {
		putView(ViewKey.FRM_MAIN, aViews[0]);
	}

	public Team getTeam() {
		return (Team) getBusinessObject(BoKey.TEAM);
	}

	protected FrmMain getFrmMain() {
		return (FrmMain) getView(ViewKey.FRM_MAIN);
	}

	@Override
	protected void disposeController() {
	}

	@Override
	protected void initViews() {
		getFrmMain().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	@Override
	protected void initBusinessObjects() {
	}

	@Override
	protected void initController() {
	}

	@Override
	protected void setVisible(final boolean aVisible) {
		getFrmMain().setVisible(aVisible);
	}

	@Override
	protected void updateController() {
	}

	@Override
	public void stateChanged(final ChangeEvent aEvent) {
		final FrmMain frm = getFrmMain();
		final JTabbedPane tab = frm.getPnlMain().getTabMain();
		if (tab == aEvent.getSource()) {
			if (tab.getSelectedIndex() == 2) {
				@SuppressWarnings("unchecked")
				final CtrFormations<CtrMain<T_PrevCtr>> ctrFormations = (CtrFormations<CtrMain<T_PrevCtr>>) getSubController(
						CtrKey.FORMATIONS);
				if (ctrFormations.isDirty()) {
					ctrFormations.reinit();
				}
			}
		}
	}

	@Override
	public void mouseClicked(final MouseEvent aEvent) {
		final Object src = aEvent.getSource();
		if (aEvent.getClickCount() == 2 && src instanceof final TblCustomized tbl) {
			final int row = tbl.getSelectedRow();
			if (row >= 0) {
				final int modelRow = tbl.convertRowIndexToModel(row);
				final TableModel model = tbl.getModel();
				Object obj = null;
				if (model instanceof EnumBasedTableModel) {
					obj = ((EnumBasedTableModel<?, ?>) model)
							.getRowData(modelRow);
				}
				Player player = null;
				if (obj instanceof Player) {
					player = (Player) obj;
				} else if (obj instanceof PlayerPosition) {
					player = ((PlayerPosition) obj).getPlayer();
				}
				if (player != null) {
					final CtrPlayerDetails<CtrMain<T_PrevCtr>> ctrPlayerDetails = new CtrPlayerDetails<>(
							this, getFrmMain(), player);
					ctrPlayerDetails.init();
					ctrPlayerDetails.dispose();
				}
			}
		}

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
