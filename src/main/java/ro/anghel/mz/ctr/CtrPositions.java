package ro.anghel.mz.ctr;

import ro.anghel.ctr.AbstractController;
import ro.anghel.ctr.Controller;
import ro.anghel.mz.domain.Position;
import ro.anghel.mz.domain.Team;
import ro.anghel.mz.ui.PnlPositions;
import ro.anghel.mz.ui.model.PositionTableModel;
import ro.anghel.mz.ui.renderer.PositionsCellRender;
import ro.anghel.view.ControllableView;

public class CtrPositions<T_PrevCtr extends Controller<?, ?>> extends
		AbstractController<CtrPositions<T_PrevCtr>, T_PrevCtr, CtrPositions.CtrKey, CtrPositions.BoKey, CtrPositions.ViewKey> {

	public enum CtrKey {
	}

	public enum BoKey {
		TEAM
	}

	public enum ViewKey {
		PNL_POSITIONS
	}

	public CtrPositions(final T_PrevCtr aPreviousController,
			final PnlPositions aPnlPositions, final Team aTeam) {
		super(aPreviousController, new ControllableView[] { aPnlPositions },
				new Object[] { aTeam });
	}

	@Override
	protected void addSubControllers() {
	}

	@Override
	protected void addViews(final ControllableView[] aViews) {
		putView(ViewKey.PNL_POSITIONS, aViews[0]);
	}

	@Override
	protected void addBusinessObjects(final Object[] aBusinessObjects) {
		putBusinessObject(BoKey.TEAM, aBusinessObjects[0]);
	}

	@Override
	protected void disposeController() {
	}

	@Override
	protected void initViews() {
		final PnlPositions pnlPositions = getPnlPositions();
		final PositionsCellRender cellRender = new PositionsCellRender();
		for (final Position pos : Position.values()) {
			pnlPositions.getPnlForPosition(pos.name())
					.setTblDataModel(createPositionTableModel(pos), cellRender);
		}
	}

	private PositionTableModel createPositionTableModel(
			final Position position) {
		final Team team = getTeam();
		return new PositionTableModel(position, team);
	}

	public Team getTeam() {
		return (Team) getBusinessObject(BoKey.TEAM);
	}

	private PnlPositions getPnlPositions() {
		return (PnlPositions) getView(ViewKey.PNL_POSITIONS);
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

}
