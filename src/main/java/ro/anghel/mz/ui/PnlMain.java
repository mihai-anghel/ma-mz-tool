package ro.anghel.mz.ui;

import javax.swing.JTabbedPane;

import com.jeta.forms.components.panel.FormPanel;

public class PnlMain extends FormPanel {

	private static final long serialVersionUID = 1L;

	private static final String MODEL = "gui/PnlMain.xml";

	private static final String TAB_MAIN = "TabMain";

	private static final String PNL_OVERVIEW = "PnlOverview";

	private static final String PNL_POSITIONS = "PnlPositions";

	private static final String PNL_FORMATIONS = "PnlFormations";

	private static final String PNL_PLAYER_COMPARATOR = "PnlPlayerComparator";

	private static final String PNL_CONFIGURATOR = "PnlConfigurator";

	private static final String PNL_ABOUT = "PnlAbout";

	public PnlMain() {
		super(MODEL);
	}

	public JTabbedPane getTabMain() {
		return getTabbedPane(TAB_MAIN);
	}

	public PnlTableView getPnlOverview() {
		return (PnlTableView) getPanel(PNL_OVERVIEW);
	}

	public PnlPositions getPnlPositions() {
		return (PnlPositions) getPanel(PNL_POSITIONS);
	}

	public PnlFormations getPnlFormations() {
		return (PnlFormations) getPanel(PNL_FORMATIONS);
	}

	public PnlPlayerComparator getPnlPlayerComparator() {
		return (PnlPlayerComparator) getPanel(PNL_PLAYER_COMPARATOR);
	}

	public PnlConfigurator getPnlConfigurator() {
		return (PnlConfigurator) getPanel(PNL_CONFIGURATOR);
	}

	public PnlAbout getPnlAbout() {
		return (PnlAbout) getPanel(PNL_ABOUT);
	}

}
