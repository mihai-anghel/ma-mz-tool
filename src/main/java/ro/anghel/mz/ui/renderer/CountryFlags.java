package ro.anghel.mz.ui.renderer;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CountryFlags {

	private static final boolean NO_CONNECTION = downloadFlag("ro") == null;

	private static final Map<String, Icon> ICON_MAP = new HashMap<>();

	private CountryFlags() {
		//
	}

	public static Icon getFlag(final String aCountry) {
		if (NO_CONNECTION) {
			return null;
		}
		return ICON_MAP.computeIfAbsent(aCountry, CountryFlags::downloadFlag);
	}

	private static Icon downloadFlag(final String aCountry) {
		final String imageUrl = "http://static.managerzone.com/img/flags/s_"
				+ aCountry.toLowerCase() + ".gif";
		try {
			final Icon img = new ImageIcon(new URL(imageUrl), aCountry);
			if (img.getIconHeight() <= 0 || img.getIconWidth() <= 0) {
				return null;
			}
			return img;
		} catch (final IOException exc) {
			return null;
		}
	}
}
