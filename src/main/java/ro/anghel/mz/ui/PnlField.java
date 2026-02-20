package ro.anghel.mz.ui;

import com.jeta.forms.components.panel.FormPanel;

import ro.anghel.ctr.Controller;
import ro.anghel.view.ControllableView;

public class PnlField extends FormPanel implements ControllableView {

	private static final long serialVersionUID = 1L;

	private static final String MODEL = "gui/PnlField.xml";

	private static final String PNL_POS_GK = "PnlPosGK";

	private static final String PNL_POS_WB1 = "PnlPosWB1";

	private static final String PNL_POS_WB2 = "PnlPosWB2";

	private static final String PNL_POS_CB = "PnlPosCB";

	private static final String PNL_POS_DM = "PnlPosDM";

	private static final String PNL_POS_CM = "PnlPosCM";

	private static final String PNL_POS_WM1 = "PnlPosWM1";

	private static final String PNL_POS_WM2 = "PnlPosWM2";

	private static final String PNL_POS_AM = "PnlPosAM";

	private static final String PNL_POS_FW = "PnlPosFW";

	public PnlField() {
		super(MODEL);
	}

	public PnlFieldPosition getPnlPosGK() {
		return (PnlFieldPosition) getPanel(PNL_POS_GK);
	}

	public PnlFieldPosition getPnlPosWB1() {
		return (PnlFieldPosition) getPanel(PNL_POS_WB1);
	}

	public PnlFieldPosition getPnlPosWB2() {
		return (PnlFieldPosition) getPanel(PNL_POS_WB2);
	}

	public PnlFieldPosition getPnlPosCB() {
		return (PnlFieldPosition) getPanel(PNL_POS_CB);
	}

	public PnlFieldPosition getPnlPosDM() {
		return (PnlFieldPosition) getPanel(PNL_POS_DM);
	}

	public PnlFieldPosition getPnlPosCM() {
		return (PnlFieldPosition) getPanel(PNL_POS_CM);
	}

	public PnlFieldPosition getPnlPosWM1() {
		return (PnlFieldPosition) getPanel(PNL_POS_WM1);
	}

	public PnlFieldPosition getPnlPosWM2() {
		return (PnlFieldPosition) getPanel(PNL_POS_WM2);
	}

	public PnlFieldPosition getPnlPosAM() {
		return (PnlFieldPosition) getPanel(PNL_POS_AM);
	}

	public PnlFieldPosition getPnlPosFW() {
		return (PnlFieldPosition) getPanel(PNL_POS_FW);
	}

	@Override
	public void addListener(final Controller<?, ?> aController) {
		getPnlPosGK().addListener(aController);
		getPnlPosWB1().addListener(aController);
		getPnlPosWB2().addListener(aController);
		getPnlPosCB().addListener(aController);
		getPnlPosDM().addListener(aController);
		getPnlPosCM().addListener(aController);
		getPnlPosWM1().addListener(aController);
		getPnlPosWM2().addListener(aController);
		getPnlPosAM().addListener(aController);
		getPnlPosFW().addListener(aController);
	}

	@Override
	public void removeListener(final Controller<?, ?> aController) {
		getPnlPosGK().removeListener(aController);
		getPnlPosWB1().removeListener(aController);
		getPnlPosWB2().removeListener(aController);
		getPnlPosCB().removeListener(aController);
		getPnlPosDM().removeListener(aController);
		getPnlPosCM().removeListener(aController);
		getPnlPosWM1().removeListener(aController);
		getPnlPosWM2().removeListener(aController);
		getPnlPosAM().removeListener(aController);
		getPnlPosFW().removeListener(aController);
	}

	public void removeAllPnlPlayers() {
		getPnlPosGK().removeAll();
		getPnlPosWB1().removeAll();
		getPnlPosWB2().removeAll();
		getPnlPosCB().removeAll();
		getPnlPosDM().removeAll();
		getPnlPosCM().removeAll();
		getPnlPosWM1().removeAll();
		getPnlPosWM2().removeAll();
		getPnlPosAM().removeAll();
		getPnlPosFW().removeAll();
	}
}
