package ro.anghel.ui.model;

public interface ValueHandler<T_Data, T_Value> {

	Class<T_Value> getValueClass();

	T_Value getValue(T_Data data);

	boolean isEditable(T_Data data);

	boolean setValue(T_Data data, T_Value value);

	boolean setValueAsObject(T_Data data, Object value);

}
