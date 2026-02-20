package ro.anghel.mz.ui;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.jeta.forms.components.panel.FormPanel;

import ro.anghel.ctr.Controller;
import ro.anghel.mz.ui.renderer.DecimalCellEditor;
import ro.anghel.mz.ui.renderer.DecimalCellRender;
import ro.anghel.mz.ui.renderer.TableHeaderCellRender;
import ro.anghel.ui.model.EnumBasedTableModel;
import ro.anghel.view.ControllableView;

public class PnlTableView extends FormPanel implements ControllableView {

	private static final long serialVersionUID = 1L;

	private static final String MODEL = "gui/PnlTableView.xml";

	private static final String TBL_DATA = "TblData";

	private static final String LBL_TITLE = "LblTitle";

	private static final TableCellRenderer HEADER_RENDERER = new TableHeaderCellRender();

	private static final Border EMPTY_BORDER = BorderFactory
			.createEmptyBorder(2, 4, 2, 4);

	public PnlTableView() {
		super(MODEL);

		final JLabel lblTitle = getLblTitle();
		final Font font = lblTitle.getFont();
		lblTitle.setFont(font.deriveFont(Font.BOLD));
		lblTitle.setBorder(EMPTY_BORDER);

		final JTable tbl = getTblData();

		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setAutoCreateRowSorter(true);
		final DecimalCellRender rend = new DecimalCellRender();
		tbl.setDefaultRenderer(Float.class, rend);
		tbl.setDefaultRenderer(Double.class, rend);

		tbl.setDefaultEditor(Float.class, new DecimalCellEditor(true));
		tbl.setDefaultEditor(Double.class, new DecimalCellEditor(false));
	}

	public TblCustomized getTblData() {
		return (TblCustomized) getTable(TBL_DATA);
	}

	public boolean setAutoCreateRowSorter(final boolean aAutoCreate) {
		final JTable tbl = getTblData();

		try {
			final Method method = JTable.class.getMethod(
					"setAutoCreateRowSorter", new Class[] { boolean.class });
			if (method != null) {
				// tbl.setAutoCreateRowSorter(true);
				method.invoke(tbl,
						new Object[] { Boolean.valueOf(aAutoCreate) });
				return true;
			}
		} catch (final Exception exc) {
		}
		return false;
	}

	public void setTblDataModel(final EnumBasedTableModel<?, ?> aModel,
			final TableCellRenderer aCellRenderer) {
		final JTable tbl = getTblData();
		tbl.setModel(aModel);
		int cnt = aModel.getRowCount();
		for (int i = 0; i < cnt; ++i) {
			final int h = aModel.getRowHeight();
			if (h >= 0) {
				tbl.setRowHeight(i, h);
			}
		}
		cnt = aModel.getColumnCount();
		for (int i = 0; i < cnt; ++i) {
			final TableColumn tblCol = tbl.getColumnModel().getColumn(i);
			if (aCellRenderer != null) {
				tblCol.setCellRenderer(aCellRenderer);
			}
			tblCol.setHeaderRenderer(HEADER_RENDERER);
			final int w = aModel.getColumnWidth(i);
			tblCol.setMinWidth(5);
			if (w >= 0) {
				tblCol.setPreferredWidth(w);
			}
		}
		if (aCellRenderer == null) {
			final Color background = tbl.getBackground();
			if (background != null) {
				tbl.setSelectionBackground(background.darker());
			}
		}
	}

	public void setTblDataModel(final EnumBasedTableModel<?, ?> aModel) {
		setTblDataModel(aModel, null);
	}

	public JLabel getLblTitle() {
		return getLabel(LBL_TITLE);
	}

	public void setTitle(final String aTitle) {
		getLblTitle().setText(aTitle);
	}

	@Override
	public void addListener(final Controller<?, ?> aController) {
	}

	@Override
	public void removeListener(final Controller<?, ?> aController) {
	}
}
