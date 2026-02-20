package ro.anghel.mz.ui.model;

import ro.anghel.mz.domain.PlayerPosition;
import ro.anghel.ui.model.EnumBasedTableModel;

public class SquadTableModel
		extends EnumBasedTableModel<PlayerPosition, SquadTableColumn> {

	private static final long serialVersionUID = 1L;

	public SquadTableModel(final PlayerPosition[] playerPositions) {
		super(playerPositions, SquadTableColumn.values(), 21);
	}

	public SquadTableModel() {
		this(null);
	}

}
