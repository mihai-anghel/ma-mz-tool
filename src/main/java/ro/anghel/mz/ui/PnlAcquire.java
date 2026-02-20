package ro.anghel.mz.ui;

import javax.swing.JButton;
import javax.swing.JTextArea;

import com.jeta.forms.components.panel.FormPanel;

public class PnlAcquire extends FormPanel {

	private static final long serialVersionUID = 1L;

	private static final String MODEL = "gui/PnlAcquire.xml";

	private static final String TXT_PASTED = "TxtPasted";

	private static final String BTN_OK = "BtnOk";

	private static final String BTN_CANCEL = "BtnCancel";

	private static final String BTN_PASTE = "BtnPaste";

	public PnlAcquire() {
		super(MODEL);
	}

	public JTextArea getTxtPasted() {
		return (JTextArea) getTextComponent(TXT_PASTED);
	}

	public JButton getBtnOk() {
		return (JButton) getButton(BTN_OK);
	}

	public JButton getBtnCancel() {
		return (JButton) getButton(BTN_CANCEL);
	}

	public JButton getBtnPaste() {
		return (JButton) getButton(BTN_PASTE);
	}
}
