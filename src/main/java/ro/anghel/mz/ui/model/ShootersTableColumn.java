package ro.anghel.mz.ui.model;

import ro.anghel.mz.domain.Player;
import ro.anghel.ui.model.AbstractValueGetter;
import ro.anghel.ui.model.TableColumn;
import ro.anghel.ui.model.TableColumnHolder;
import ro.anghel.ui.model.ValueHandler;

public enum ShootersTableColumn implements TableColumnHolder<Player> {
	NUMBER("No", "Number", 10,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getNumber());
				}
			}), //
	NAME("Name", "Name", 90,
			new AbstractValueGetter<Player, String>(String.class) {
				@Override
				public String getValue(final Player data) {
					return data.getName();
				}
			}), //
	SET_PLAYS("SP", "Set Plays", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getSetPlays());
				}
			}), //
	SHOOTING("Sh", "Shooting", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getShooting());
				}
			}), //
	PASSING("Pa", "Passing", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getPassing());
				}
			}), //
	AERIAL_PASSING("AP", "Aerial Passing", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getAerialPassing());
				}
			}), //
	FORM("Fo", "Form", 5, new AbstractValueGetter<Player, Short>(Short.class) {
		@Override
		public Short getValue(final Player data) {
			return Short.valueOf(data.getForm());
		}
	}), //
	EXPERIENCE("Ex", "Experience", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getExperience());
				}
			}), //
	STAMINA("St", "Stamina", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getStamina());
				}
			});

	private final TableColumn<Player, ?> tableColumn;

	<T_Value> ShootersTableColumn(final String name, final String tooltip,
			final int width, final ValueHandler<Player, T_Value> valueHandler) {
		tableColumn = new TableColumn<>(name, tooltip, width, valueHandler);
	}

	@Override
	public TableColumn<Player, ?> getTableColumn() {
		return tableColumn;
	}
}
