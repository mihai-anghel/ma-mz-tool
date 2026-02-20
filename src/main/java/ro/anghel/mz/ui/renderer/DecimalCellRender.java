package ro.anghel.mz.ui.renderer;

import javax.swing.table.DefaultTableCellRenderer;

import ro.anghel.util.NumberFormatUtil;

public class DecimalCellRender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public DecimalCellRender() {
		setHorizontalAlignment(RIGHT);
	}

	@Override
	public void setValue(final Object aValue) {
		setText(aValue == null ? ""
				: NumberFormatUtil.FORMAT_2_2.numberFormat().format(aValue));
	}

}
