package ro.anghel.mz.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import ro.anghel.ui.model.EnumBasedTableModel;

public class TblCustomized extends JTable {

	private static final long serialVersionUID = 1L;

	public TblCustomized() {
		super();
	}

	public TblCustomized(final int aNumRows, final int aNumColumns) {
		super(aNumRows, aNumColumns);
	}

	public TblCustomized(final Object[][] aRowData,
			final Object[] aColumnNames) {
		super(aRowData, aColumnNames);
	}

	public TblCustomized(final TableModel aTableModel,
			final TableColumnModel aColumnModel,
			final ListSelectionModel aSelectionModel) {
		super(aTableModel, aColumnModel, aSelectionModel);
	}

	public TblCustomized(final TableModel aTableModel,
			final TableColumnModel aColumnModel) {
		super(aTableModel, aColumnModel);
	}

	public TblCustomized(final TableModel aTableModel) {
		super(aTableModel);
	}

	public TblCustomized(final Vector<? extends Vector<?>> aRowData,
			final Vector<?> aColumnNames) {
		super(aRowData, aColumnNames);
	}

	@Override
	protected JTableHeader createDefaultTableHeader() {
		return new JTableHeader(columnModel) {

			private static final long serialVersionUID = 1L;

			@Override
			public String getToolTipText(final MouseEvent aEvent) {
				final TableModel tblModel = getModel();
				if (tblModel instanceof EnumBasedTableModel) {
					final Point p = aEvent.getPoint();
					final int index = columnModel.getColumnIndexAtX(p.x);
					final int realIndex = columnModel.getColumn(index)
							.getModelIndex();
					return ((EnumBasedTableModel<?, ?>) tblModel)
							.getColumnHeaderTooltip(realIndex);
				}
				return null;
			}
		};
	}

}
