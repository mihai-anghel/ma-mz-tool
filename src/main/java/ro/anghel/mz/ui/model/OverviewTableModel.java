package ro.anghel.mz.ui.model;

import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.Team;
import ro.anghel.ui.model.EnumBasedTableModel;

public class OverviewTableModel
		extends EnumBasedTableModel<Player, OverviewTableColumn> {

	private static final long serialVersionUID = 1L;

	private String currency = "";

	public OverviewTableModel(final Team team) {
		super(team != null ? team.getPlayers() : null,
				OverviewTableColumn.values(), 21);
		currency = team != null ? team.getCurrency() : "";
	}

	public OverviewTableModel() {
		this(null);
	}

	@Override
	public String getColumnName(final int columnIndex) {
		if (!"".equals(currency) && (columnIndex == OverviewTableColumn.SALARY
				.ordinal()
				|| columnIndex == OverviewTableColumn.VALUE.ordinal())) {
			return super.getColumnName(columnIndex) + " (" + currency + ")";
		}
		return super.getColumnName(columnIndex);
	}

	@Override
	public String getColumnHeaderTooltip(final int columnIndex) {
		if (!"".equals(currency) && (columnIndex == OverviewTableColumn.SALARY
				.ordinal()
				|| columnIndex == OverviewTableColumn.VALUE.ordinal())) {
			return super.getColumnHeaderTooltip(columnIndex) + " (" + currency
					+ ")";
		}
		return super.getColumnHeaderTooltip(columnIndex);
	}

}
