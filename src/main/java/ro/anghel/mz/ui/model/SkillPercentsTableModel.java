package ro.anghel.mz.ui.model;

import ro.anghel.mz.domain.PositionCalculator;
import ro.anghel.ui.model.EnumBasedTableModel;

public class SkillPercentsTableModel extends
		EnumBasedTableModel<PositionCalculator, SkillPercentsTableColumn> {

	private static final long serialVersionUID = 1L;

	public SkillPercentsTableModel(final PositionCalculator gkCalculator,
			final PositionCalculator wbCalculator,
			final PositionCalculator cbCalculator,
			final PositionCalculator dmCalculator,
			final PositionCalculator cmCalculator,
			final PositionCalculator wmCalculator,
			final PositionCalculator amCalculator,
			final PositionCalculator fwCalculator) {
		super(new PositionCalculator[] { gkCalculator, wbCalculator,
				cbCalculator, dmCalculator, cmCalculator, wmCalculator,
				amCalculator, fwCalculator }, SkillPercentsTableColumn.values(),
				-1);
	}

	@Override
	public void setValueAt(final Object value, final int rowIndex,
			final int columnIndex) {
		super.setValueAt(value, rowIndex, columnIndex);
		if (isCellEditable(rowIndex, columnIndex)) {
			fireTableCellUpdated(rowIndex,
					SkillPercentsTableColumn.SUM.ordinal());
		}
	}

}
