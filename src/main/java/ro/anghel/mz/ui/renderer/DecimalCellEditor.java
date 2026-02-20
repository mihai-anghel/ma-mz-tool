package ro.anghel.mz.ui.renderer;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import ro.anghel.util.NumberFormatUtil;

/**
 * Implements a cell editor that uses a formatted text field to edit numeric
 * values.
 */
public class DecimalCellEditor extends DefaultCellEditor {

	private static final long serialVersionUID = 1L;

	private boolean editingFloat = false;

	public DecimalCellEditor(final boolean isFloat) {
		super(new JFormattedTextField(
				NumberFormatUtil.FORMAT_2_2.formatterFactory()));
		editingFloat = isFloat;
		final JFormattedTextField fmtTxt = (JFormattedTextField) getComponent();

		final Object zero = isFloat ? (Object) Float.valueOf(0)
				: Double.valueOf(0);
		fmtTxt.setValue(zero);
		fmtTxt.setHorizontalAlignment(SwingConstants.RIGHT);
		fmtTxt.setFocusLostBehavior(JFormattedTextField.PERSIST);

		// React when the user presses Enter while the editor is
		// active. (Tab is handled as specified by
		// JFormattedTextField's focusLostBehavior property.)
		fmtTxt.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				"check");
		fmtTxt.getActionMap().put("check", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(final ActionEvent e) {
				if (!fmtTxt.isEditValid()) { // The text is invalid.
					if (userSaysRevert()) { // reverted
						fmtTxt.postActionEvent(); // inform the editor
					}
				} else {
					try { // The text is valid,
						fmtTxt.commitEdit(); // so use it.
						fmtTxt.postActionEvent(); // stop editing
					} catch (final java.text.ParseException exc) {
					}
				}
			}
		});
	}

	// Override to invoke setValue on the formatted text field.
	@Override
	public Component getTableCellEditorComponent(final JTable table,
			final Object value, final boolean isSelected, final int row,
			final int column) {
		final JFormattedTextField fmtTxt = (JFormattedTextField) super.getTableCellEditorComponent(
				table, value, isSelected, row, column);
		fmtTxt.setValue(value);
		return fmtTxt;
	}

	// Override to ensure that the value remains an Integer.
	@Override
	public Object getCellEditorValue() {
		final JFormattedTextField fmtTxt = (JFormattedTextField) getComponent();
		final Object val = fmtTxt.getValue();
		if (val instanceof Number) {
			return editingFloat ? ((Number) val).floatValue()
					: ((Number) val).doubleValue();

		}
		return null;
	}

	// Override to check whether the edit is valid,
	// setting the value if it is and complaining if
	// it isn't. If it's OK for the editor to go
	// away, we need to invoke the superclass's version
	// of this method so that everything gets cleaned up.
	@Override
	public boolean stopCellEditing() {
		final JFormattedTextField fmtTxt = (JFormattedTextField) getComponent();
		if (fmtTxt.isEditValid()) {
			try {
				fmtTxt.commitEdit();
			} catch (final java.text.ParseException exc) {
			}

		} else { // text is invalid
			if (!userSaysRevert()) { // user wants to edit
				return false; // don't let the editor go away
			}
		}
		return super.stopCellEditing();
	}

	/**
	 * Lets the user know that the text they entered is bad. Returns true if the
	 * user elects to revert to the last good value. Otherwise, returns false,
	 * indicating that the user wants to continue editing.
	 */
	protected boolean userSaysRevert() {
		Toolkit.getDefaultToolkit().beep();
		final JFormattedTextField fmtTxt = (JFormattedTextField) getComponent();
		fmtTxt.selectAll();
		final Object[] options = { "Edit", "Revert" };
		final int answer = JOptionPane.showOptionDialog(
				SwingUtilities.getWindowAncestor(fmtTxt), """
						The value must be a number.\s
						You can either continue editing \
						or revert to the last valid value.""",
				"Invalid Value Entered", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);

		if (answer == 1) { // Revert!
			fmtTxt.setValue(fmtTxt.getValue());
			return true;
		}
		return false;
	}
}
