package ro.anghel.ui.model;

import javax.swing.table.AbstractTableModel;

public class EnumBasedTableModel<T_Data, T_Enum extends Enum<T_Enum> & TableColumnHolder<T_Data>>
		extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private T_Data[] data;

	private T_Enum[] columnInfos;

	private int rowHeight;

	public EnumBasedTableModel(final T_Data[] data, final T_Enum[] columnInfos,
			final int rowHeight) {
		this.data = data;
		this.columnInfos = columnInfos;
		this.rowHeight = rowHeight;
	}

	public EnumBasedTableModel(final T_Enum[] columnInfos,
			final int rowHeight) {
		this(null, columnInfos, rowHeight);
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	public T_Data getRowData(final int rowIndex) {
		if (data == null || rowIndex < 0 || rowIndex >= data.length) {
			return null;
		}
		return data[rowIndex];
	}

	public int getRowHeight() {
		return rowHeight;
	}

	@Override
	public int getColumnCount() {
		return columnInfos.length;
	}

	public int getColumnWidth(final int columnIndex) {
		return columnInfos[columnIndex].getTableColumn().getWidth();
	}

	public String getColumnHeaderTooltip(final int columnIndex) {
		return columnInfos[columnIndex].getTableColumn().getTooltip();
	}

	@Override
	public String getColumnName(final int columnIndex) {
		return columnInfos[columnIndex].getTableColumn().getName();
	}

	@Override
	public Class<?> getColumnClass(final int columnIndex) {
		return columnInfos[columnIndex].getTableColumn().getValueHandler()
				.getValueClass();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		final T_Data obj = getRowData(rowIndex);
		return columnInfos[columnIndex].getTableColumn().getValueHandler()
				.getValue(obj);
	}

	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		if (columnIndex >= 0 && columnIndex < columnInfos.length) {
			final T_Data obj = getRowData(rowIndex);
			return columnInfos[columnIndex].getTableColumn().getValueHandler()
					.isEditable(obj);
		}
		return false;
	}

	@Override
	public void setValueAt(final Object value, final int rowIndex,
			final int columnIndex) {
		if (columnIndex >= 0 && columnIndex < columnInfos.length) {
			final T_Data obj = getRowData(rowIndex);
			if (columnInfos[columnIndex].getTableColumn().getValueHandler()
					.setValueAsObject(obj, value)) {
				fireTableCellUpdated(rowIndex, columnIndex);
			}
		}
	}
}
