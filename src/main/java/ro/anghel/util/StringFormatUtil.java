package ro.anghel.util;

public class StringFormatUtil {

	public static String capitalize(final String aString) {

		final String[] arr = aString.split("(\\s|_)+");
		final StringBuilder sb = new StringBuilder();
		String sep = "";
		for (int i = 0; i < arr.length; ++i) {
			final String elem = arr[i];
			if (elem.length() > 0) {
				sb.append(sep).append(elem.substring(0, 1).toUpperCase())
						.append(elem.substring(1).toLowerCase());
				sep = " ";
			}
		}
		return sb.toString();
	}

}
