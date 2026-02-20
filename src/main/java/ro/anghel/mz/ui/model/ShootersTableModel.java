package ro.anghel.mz.ui.model;

import ro.anghel.mz.domain.Player;
import ro.anghel.ui.model.EnumBasedTableModel;

public class ShootersTableModel
		extends EnumBasedTableModel<Player, ShootersTableColumn> {

	private static final long serialVersionUID = 1L;

	public ShootersTableModel(final Player[] players) {
		super(players, ShootersTableColumn.values(), -1);
	}

	public ShootersTableModel() {
		this(null);
	}

}
