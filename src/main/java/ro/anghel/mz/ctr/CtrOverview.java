package ro.anghel.mz.ctr;

import ro.anghel.ctr.AbstractController;
import ro.anghel.ctr.Controller;
import ro.anghel.mz.domain.Team;
import ro.anghel.mz.ui.PnlTableView;
import ro.anghel.mz.ui.model.OverviewTableModel;
import ro.anghel.mz.ui.renderer.OverviewCellRender;
import ro.anghel.view.ControllableView;

public class CtrOverview<T_PrevCtr extends Controller<?, ?>> extends
		AbstractController<CtrOverview<T_PrevCtr>, T_PrevCtr, CtrOverview.CtrKey, CtrOverview.BoKey, CtrOverview.ViewKey> {

	public enum CtrKey {
	}

	public enum BoKey {
		TEAM
	}

	public enum ViewKey {
		PNL_OVERVIEW
	}

	public CtrOverview(final T_PrevCtr aPreviousController,
			final PnlTableView aPnlOverview, final Team aTeam) {
		super(aPreviousController, new ControllableView[] { aPnlOverview },
				new Object[] { aTeam });
	}

	@Override
	protected void addSubControllers() {
	}

	@Override
	protected void addViews(final ControllableView[] aViews) {
		putView(ViewKey.PNL_OVERVIEW, aViews[0]);
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
		final PnlTableView pnlOverview = getPnlOverview();
		final OverviewCellRender cellRender = new OverviewCellRender();
		final Team team = getTeam();
		pnlOverview.setTitle("Overview Of Team <" + team.getName() + ">");
		pnlOverview.setTblDataModel(new OverviewTableModel(team), cellRender);

	}

	public Team getTeam() {
		return (Team) getBusinessObject(BoKey.TEAM);
	}

	private PnlTableView getPnlOverview() {
		return (PnlTableView) getView(ViewKey.PNL_OVERVIEW);
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
