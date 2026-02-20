package ro.anghel.ui.model;

public abstract class AbstractValueGetter<T_Data, T_Value>
		implements ValueHandler<T_Data, T_Value> {

	private final Class<T_Value> valueClass;

	protected AbstractValueGetter(final Class<T_Value> valueClass) {
		this.valueClass = valueClass;
	}

	@Override
	public Class<T_Value> getValueClass() {
		return valueClass;
	}

	@Override
	public boolean isEditable(final T_Data data) {
		return false;
	}

	@Override
	public boolean setValue(final T_Data data, final T_Value value) {
		return false;
	}

	@Override
	public boolean setValueAsObject(final T_Data data, final Object value) {
		return false;
	}

}
