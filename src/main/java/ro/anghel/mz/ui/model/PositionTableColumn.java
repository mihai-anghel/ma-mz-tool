package ro.anghel.mz.ui.model;

import ro.anghel.mz.domain.Foot;
import ro.anghel.mz.domain.Player;
import ro.anghel.ui.model.AbstractValueGetter;
import ro.anghel.ui.model.TableColumn;
import ro.anghel.ui.model.TableColumnHolder;
import ro.anghel.ui.model.ValueHandler;

public enum PositionTableColumn implements TableColumnHolder<Player> {
	NUMBER("No", "Number", 15,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getNumber());
				}
			}), //
	NAME("Name", "Name", 100,
			new AbstractValueGetter<Player, String>(String.class) {
				@Override
				public String getValue(final Player data) {
					return data.getName();
				}
			}), //
	FOOT("F", "Preferred Foot", 5,
			new AbstractValueGetter<Player, Foot>(Foot.class) {
				@Override
				public Foot getValue(final Player data) {
					return data.getFoot();
				}
			}), //
	CM("Cm", "Height (cm)", 15,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getHeight());
				}
			}), //
	KG("Kg", "Weight (kg)", 15,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getWeight());
				}
			}), //
	RATING("Rt", "Rating", 15,
			new AbstractValueGetter<Player, Double>(Double.class) {
				@Override
				public Double getValue(final Player data) {
					// calculate it in the table model
					return Double.valueOf(0.0);
				}
			});

	private final TableColumn<Player, ?> tableColumn;

	<T_Value> PositionTableColumn(final String name, final String tooltip,
			final int width, final ValueHandler<Player, T_Value> valueHandler) {
		tableColumn = new TableColumn<>(name, tooltip, width, valueHandler);
	}

	@Override
	public TableColumn<Player, ?> getTableColumn() {
		return tableColumn;
	}

}
