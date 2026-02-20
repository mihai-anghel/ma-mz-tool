package ro.anghel.mz.ui.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableCellRenderer;

import ro.anghel.mz.domain.Player;
import ro.anghel.mz.ui.model.PositionTableColumn;
import ro.anghel.mz.ui.model.PositionTableModel;

public class PositionsCellRender implements TableCellRenderer {

	public PositionsCellRender() {
	}

	@Override
	public Component getTableCellRendererComponent(final JTable aTable,
			final Object aValue, final boolean aIsSelected,
			final boolean aHasFocus, final int aRow, final int aColumn) {
		// JLabel rend = new JLabel(aValue != null ? aValue.toString() : "");
		final PositionTableModel model = (PositionTableModel) aTable.getModel();

		final int modelColumn = aTable.convertColumnIndexToModel(aColumn);
		Component rend = null;
		if (modelColumn == PositionTableColumn.FOOT.ordinal()) {
			final RowSorter<?> sorter = aTable.getRowSorter();
			final int modelRow = sorter != null
					? sorter.convertRowIndexToModel(aRow)
					: aRow;
			final Player player = model.getRowData(modelRow);
			final Color foreground = aIsSelected
					? aTable.getSelectionForeground()
					: aTable.getForeground();
			rend = SpecialRenderers.getFootComponent(foreground, player);
		} else {
			final Class<?> cls = aTable.getColumnClass(aColumn);
			rend = aTable.getDefaultRenderer(cls).getTableCellRendererComponent(
					aTable, aValue, aIsSelected, aHasFocus, aRow, aColumn);
		}
		final Color col = Color.decode(model.getPosition().color());
		// rend.setOpaque(true);
		rend.setBackground(aIsSelected ? col.darker() : col);
		return rend;
	}

}
