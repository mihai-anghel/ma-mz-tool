package ro.anghel.mz.ui.renderer;

import java.awt.Component;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableHeaderCellRender implements TableCellRenderer {

	private static Border emptyBorder = null;

	public TableHeaderCellRender() {
		//
	}

	@Override
	public Component getTableCellRendererComponent(final JTable aTable,
			final Object aValue, final boolean aIsSelected,
			final boolean aHasFocus, final int aRow, final int aColumn) {
		final Component rend = aTable.getTableHeader().getDefaultRenderer()
				.getTableCellRendererComponent(aTable, aValue, aIsSelected,
						aHasFocus, aRow, aColumn);
		if (rend instanceof final DefaultTableCellRenderer defRend) {
			final Border border = defRend.getBorder();
			if (border instanceof EmptyBorder) {
				if (emptyBorder == null) {
					final Insets ins = ((EmptyBorder) border).getBorderInsets();
					emptyBorder = BorderFactory.createEmptyBorder(ins.top, 1,
							ins.bottom, 1);
				}
				defRend.setBorder(emptyBorder);
			}
			defRend.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return rend;
	}
}
