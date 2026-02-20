package ro.anghel.mz.ui.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableCellRenderer;

import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.Position;
import ro.anghel.mz.ui.model.OverviewTableColumn;
import ro.anghel.mz.ui.model.OverviewTableModel;

public class OverviewCellRender implements TableCellRenderer {
	private static final Color DEFAULT_COLOR = new Color(230, 230, 230);

	public OverviewCellRender() {
	}

	@Override
	public Component getTableCellRendererComponent(final JTable aTable,
			final Object aValue, final boolean aIsSelected,
			final boolean aHasFocus, final int aRow, final int aColumn) {
		final OverviewTableModel model = (OverviewTableModel) aTable.getModel();

		final Component rend = getRendererComponent(aTable, aValue, aIsSelected,
				aHasFocus, aRow, aColumn);

		final Color col = getColor(aTable, rend, model, aRow, aColumn);
		rend.setBackground(aIsSelected ? col.darker() : col);
		return rend;
	}

	private static Component getRendererComponent(final JTable aTable,
			final Object aValue, final boolean aIsSelected,
			final boolean aHasFocus, final int aRow, final int aColumn) {
		final OverviewTableModel model = (OverviewTableModel) aTable.getModel();
		final RowSorter<?> sorter = aTable.getRowSorter();
		final int modelRow = sorter != null
				? sorter.convertRowIndexToModel(aRow)
				: aRow;
		final Player player = model.getRowData(modelRow);
		final int modelColumn = aTable.convertColumnIndexToModel(aColumn);
		final Color foreground = aIsSelected ? aTable.getSelectionForeground()
				: aTable.getForeground();
		final OverviewTableColumn[] allTableColumns = OverviewTableColumn
				.values();
		if (modelColumn < allTableColumns.length) {
			switch (allTableColumns[modelColumn]) {
			case FOOT: // F
			{
				return SpecialRenderers.getFootComponent(foreground, player);
			}
			case COUNTRY: // Co
			{
				return SpecialRenderers.getCountryComponent(foreground, player);
			}
			case STATUS: // S
			{
				return SpecialRenderers.getStatusComponent(foreground, player);
			}
			case TRAINING_CAMP: // TC
			{
				return SpecialRenderers.getTCComponent(foreground, player);
			}
			case INJURY: // Ij
			{
				return SpecialRenderers.getInjuryComponent(foreground, player);
			}
			case ALREADY_AT_TRAINING_CAMP: // ATC
			{
				return SpecialRenderers.getATCComponent(foreground, player);
			}
			}
		}
		final Class<?> cls = aTable.getColumnClass(aColumn);
		return aTable.getDefaultRenderer(cls).getTableCellRendererComponent(
				aTable, aValue, aIsSelected, aHasFocus, aRow, aColumn);
	}

	private static Color getColor(final JTable aTable,
			final Component aRenderToModify, final OverviewTableModel aModel,
			final int aRow, final int aColumn) {
		final RowSorter<?> sorter = aTable.getRowSorter();
		final int modelRow = sorter != null
				? sorter.convertRowIndexToModel(aRow)
				: aRow;
		final Player player = aModel.getRowData(modelRow);
		final int modelColumn = aTable.convertColumnIndexToModel(aColumn);
		final OverviewTableColumn[] allTableColumns = OverviewTableColumn
				.values();
		if (modelColumn < allTableColumns.length) {
			switch (allTableColumns[modelColumn]) {

			case NUMBER: // No
			case NAME: // Name
			{
				final String color = player.getBestPosition().color();
				return Color.decode(color);
			}
			case REST: // Rest
			{
				return Color.WHITE;
			}
			case GK: // GK
			case WB: // WB
			case CB: // CB
			case DM: // DM
			case CM: // CM
			case WM: // WM
			case AM: // AM
			case FW: // FW
			{
				final Position pos = Position
						.valueOf(aModel.getColumnName(modelColumn));
				if (player.getBestPosition().equals(pos)) {
					final Font font = aRenderToModify.getFont();
					aRenderToModify.setFont(font.deriveFont(Font.BOLD));
				}
				final String color = pos.color();
				return Color.decode(color);
			}
			}
		}
		return DEFAULT_COLOR; // Color.LIGHT_GRAY;
	}

}
