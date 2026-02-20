package ro.anghel.mz.ui.renderer;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import ro.anghel.mz.domain.FormStatus;

public class FormStatusIcons {

	private FormStatusIcons() {
		//
	}

	public static Icon getIcon(final FormStatus status) {
		return switch (status) {
		case OK -> icon("gui/formgood.gif");
		case PLUS -> icon("gui/formplus.gif");
		case MINUS -> icon("gui/formminus.gif");
		case UNKNOWN -> icon("gui/formunknown.gif");
		default -> null;
		};
	}

	private static ImageIcon icon(final String iconName) {
		return new ImageIcon(
				FormStatusIcons.class.getClassLoader().getResource(iconName));
	}
}
