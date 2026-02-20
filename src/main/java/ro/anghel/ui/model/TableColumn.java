package ro.anghel.ui.model;

public final class TableColumn<T_Data, T_Value> {

	private final String name;

	private final String tooltip;

	private final int width;

	private final ValueHandler<T_Data, T_Value> valueHandler;

	public TableColumn(final String name, final String tooltip, final int width,
			final ValueHandler<T_Data, T_Value> valueHandler) {
		this.name = name;
		this.tooltip = tooltip;
		this.width = width;
		this.valueHandler = valueHandler;
	}

	public String getName() {
		return name;
	}

	public String getTooltip() {
		return tooltip;
	}

	public int getWidth() {
		return width;
	}

	public ValueHandler<T_Data, T_Value> getValueHandler() {
		return valueHandler;
	}

}
