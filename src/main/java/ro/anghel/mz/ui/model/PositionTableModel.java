package ro.anghel.mz.ui.model;

import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.Position;
import ro.anghel.mz.domain.Team;
import ro.anghel.ui.model.EnumBasedTableModel;

public class PositionTableModel
		extends EnumBasedTableModel<Player, PositionTableColumn> {

	private static final long serialVersionUID = 1L;

	private final Position position;

	public PositionTableModel(final Position position, final Team team) {
		super(team != null ? team.getSortedByPosition(position) : null,
				PositionTableColumn.values(), -1);
		this.position = position;
	}

	public PositionTableModel(final Position position) {
		this(position, null);
	}

	public Position getPosition() {
		return position;
	}

	@Override
	public String getColumnName(final int columnIndex) {
		if (columnIndex == PositionTableColumn.RATING.ordinal()) {
			return position.name();
		}
		return super.getColumnName(columnIndex);
	}

	@Override
	public String getColumnHeaderTooltip(final int columnIndex) {
		if (columnIndex == PositionTableColumn.RATING.ordinal()) {
			return position.longName() + " Rating";
		}
		return super.getColumnHeaderTooltip(columnIndex);
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		if (columnIndex == PositionTableColumn.RATING.ordinal()) {
			final Player player = getRowData(rowIndex);
			return Double
					.valueOf(position.calculator().calculateRating(player));
		}
		return super.getValueAt(rowIndex, columnIndex);
	}

}
