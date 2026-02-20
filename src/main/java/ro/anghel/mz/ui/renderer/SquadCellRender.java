package ro.anghel.mz.ui.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableCellRenderer;

import ro.anghel.mz.domain.PlayerPosition;
import ro.anghel.mz.ui.model.SquadTableColumn;
import ro.anghel.mz.ui.model.SquadTableModel;

public class SquadCellRender implements TableCellRenderer {

	public SquadCellRender() {
	}

	@Override
	public Component getTableCellRendererComponent(final JTable aTable,
			final Object aValue, final boolean aIsSelected,
			final boolean aHasFocus, final int aRow, final int aColumn) {
		// JLabel rend = new JLabel(aValue != null ? aValue.toString() : "");
		final SquadTableModel model = (SquadTableModel) aTable.getModel();
		final RowSorter<?> sorter = aTable.getRowSorter();
		final int modelRow = sorter != null
				? sorter.convertRowIndexToModel(aRow)
				: aRow;
		final PlayerPosition pos = model.getRowData(modelRow);
		final Class<?> cls = aTable.getColumnClass(aColumn);

		final int modelColumn = aTable.convertColumnIndexToModel(aColumn);
		Component rend = null;
		if (modelColumn == SquadTableColumn.FOOT.ordinal()) {
			final Color foreground = aIsSelected
					? aTable.getSelectionForeground()
					: aTable.getForeground();
			rend = SpecialRenderers.getFootComponent(foreground,
					pos.getPlayer());
		} else if (modelColumn == SquadTableColumn.STATUS.ordinal()) {
			final Color foreground = aIsSelected
					? aTable.getSelectionForeground()
					: aTable.getForeground();
			rend = SpecialRenderers.getStatusComponent(foreground,
					pos.getPlayer());
		} else {
			rend = aTable.getDefaultRenderer(cls).getTableCellRendererComponent(
					aTable, aValue, aIsSelected, aHasFocus, aRow, aColumn);
		}
		final Color col = Color.decode(pos.getPosition().color());
		// rend.setOpaque(true);
		rend.setBackground(aIsSelected ? col.darker() : col);

		return rend;
	}

}
