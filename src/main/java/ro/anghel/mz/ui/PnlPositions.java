package ro.anghel.mz.ui;

import com.jeta.forms.components.panel.FormPanel;

import ro.anghel.ctr.Controller;
import ro.anghel.mz.domain.Position;
import ro.anghel.view.ControllableView;

public class PnlPositions extends FormPanel implements ControllableView {

	private static final long serialVersionUID = 1L;

	private static final String MODEL = "gui/PnlPositions.xml";

	private static final String PNL_POS_PREFIX = "Pnl";

	public PnlPositions() {
		super(MODEL);
		for (final Position pos : Position.values()) {
			getPnlForPosition(pos.name()).setTitle(pos.longName());
		}
	}

	public PnlTableView getPnlForPosition(final String positionName) {
		return (PnlTableView) getPanel(PNL_POS_PREFIX + positionName);
	}

	@Override
	public void addListener(final Controller<?, ?> aController) {
	}

	@Override
	public void removeListener(final Controller<?, ?> aController) {
	}
}
