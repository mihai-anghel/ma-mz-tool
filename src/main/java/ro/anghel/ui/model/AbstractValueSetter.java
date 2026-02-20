package ro.anghel.ui.model;

public abstract class AbstractValueSetter<T_Data, T_Value>
		implements ValueHandler<T_Data, T_Value> {

	private final Class<T_Value> valueClass;

	protected AbstractValueSetter(final Class<T_Value> valueClass) {
		this.valueClass = valueClass;
	}

	@Override
	public boolean isEditable(final T_Data data) {
		return true;
	}

	@Override
	public Class<T_Value> getValueClass() {
		return valueClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean setValueAsObject(final T_Data data, final Object value) {
		return setValue(data, (T_Value) value);
	}
}
