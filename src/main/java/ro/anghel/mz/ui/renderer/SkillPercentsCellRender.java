package ro.anghel.mz.ui.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableCellRenderer;

import ro.anghel.mz.domain.PositionCalculator;
import ro.anghel.mz.ui.model.SkillPercentsTableColumn;
import ro.anghel.mz.ui.model.SkillPercentsTableModel;

public class SkillPercentsCellRender implements TableCellRenderer {

	public SkillPercentsCellRender() {
	}

	@Override
	public Component getTableCellRendererComponent(final JTable aTable,
			final Object aValue, final boolean aIsSelected,
			final boolean aHasFocus, final int aRow, final int aColumn) {
		// JLabel rend = new JLabel(aValue != null ? aValue.toString() : "");
		final SkillPercentsTableModel model = (SkillPercentsTableModel) aTable
				.getModel();
		final RowSorter<?> sorter = aTable.getRowSorter();
		final int modelRow = sorter != null
				? sorter.convertRowIndexToModel(aRow)
				: aRow;
		final Class<?> cls = aTable.getColumnClass(aColumn);

		final int modelColumn = aTable.convertColumnIndexToModel(aColumn);
		Component rend = aTable.getDefaultRenderer(cls)
				.getTableCellRendererComponent(aTable, aValue, aIsSelected,
						aHasFocus, aRow, aColumn);
		final PositionCalculator posCalc = model.getRowData(modelRow);
		if (modelColumn == SkillPercentsTableColumn.SUM.ordinal()) {
			final Color foreground = aIsSelected
					? aTable.getSelectionForeground()
					: aTable.getForeground();
			rend = SpecialRenderers.getPrSumComponent(foreground, posCalc);
		}

		final Color col = Color.decode(posCalc.getPosition().color());
		// rend.setOpaque(true);
		rend.setBackground(aIsSelected ? col.darker() : col);

		return rend;
	}

}
