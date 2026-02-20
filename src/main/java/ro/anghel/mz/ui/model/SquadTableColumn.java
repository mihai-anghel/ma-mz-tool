package ro.anghel.mz.ui.model;

import ro.anghel.mz.domain.Foot;
import ro.anghel.mz.domain.FormStatus;
import ro.anghel.mz.domain.PlayerPosition;
import ro.anghel.mz.domain.Position;
import ro.anghel.ui.model.AbstractValueGetter;
import ro.anghel.ui.model.TableColumn;
import ro.anghel.ui.model.TableColumnHolder;
import ro.anghel.ui.model.ValueHandler;

public enum SquadTableColumn implements TableColumnHolder<PlayerPosition> {

	POSITION("Pos", "Position", 15,
			new AbstractValueGetter<PlayerPosition, Position>(Position.class) {
				@Override
				public Position getValue(final PlayerPosition data) {
					return data.getPosition();
				}
			}), //
	NUMBER("No", "Number", 15,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getNumber());
				}
			}), //
	NAME("Name", "Name", 100,
			new AbstractValueGetter<PlayerPosition, String>(String.class) {
				@Override
				public String getValue(final PlayerPosition data) {
					return data.getPlayer().getName();
				}
			}), //
	FOOT("F", "Preferred Foot", 5,
			new AbstractValueGetter<PlayerPosition, Foot>(Foot.class) {
				@Override
				public Foot getValue(final PlayerPosition data) {
					return data.getPlayer().getFoot();
				}
			}), //
	AGE("Age", "Age", 15,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getAge());
				}
			}), //
	HEIGHT("Cm", "Height (cm)", 15,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getHeight());
				}
			}), //
	WEIGHT("Kg", "Weight (kg)", 15,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getWeight());
				}
			}), //
	RATING("Rt", "Rating", 15,
			new AbstractValueGetter<PlayerPosition, Double>(Double.class) {
				@Override
				public Double getValue(final PlayerPosition data) {
					return Double.valueOf(data.getRating());
				}
			}), //
	SPEED("Sp", "Speed", 10,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getSpeed());
				}
			}), //
	STAMINA("St", "Stamina", 10,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getStamina());
				}
			}), //
	INTELLIGENCE("PI", "Play Intelligence", 10,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getIntelligence());
				}
			}), //
	PASSING("Pa", "Passing", 10,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getPassing());
				}
			}), //
	SHOOTING("Sh", "Shooting", 10,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getShooting());
				}
			}), //
	HEADING("He", "Heading", 10,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getHeading());
				}
			}), //
	KEEPING("Ke", "Keeping", 10,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getKeeping());
				}
			}), //
	BALL_CONTROL("BC", "Ball Control", 10,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getBallControl());
				}
			}), //
	TACKLING("Ta", "Tackling", 10,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getTackling());
				}
			}), //
	AERIAL_PASSING("AP", "Aerial Passing", 10,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getAerialPassing());
				}
			}), //
	SET_PLAYS("SP", "Set Plays", 10,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getSetPlays());
				}
			}), //
	EXPERIENCE("Ex", "Experience", 10,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getExperience());
				}
			}), //
	FORM("Fo", "Form", 10,
			new AbstractValueGetter<PlayerPosition, Short>(Short.class) {
				@Override
				public Short getValue(final PlayerPosition data) {
					return Short.valueOf(data.getPlayer().getForm());
				}
			}), //
	STATUS("S", "Status", 5,
			new AbstractValueGetter<PlayerPosition, FormStatus>(
					FormStatus.class) {
				@Override
				public FormStatus getValue(final PlayerPosition data) {
					return data.getPlayer().getStatus();
				}
			});

	private final TableColumn<PlayerPosition, ?> tableColumn;

	<T_Value> SquadTableColumn(final String name, final String tooltip,
			final int width,
			final ValueHandler<PlayerPosition, T_Value> valueHandler) {
		tableColumn = new TableColumn<>(name, tooltip, width, valueHandler);
	}

	@Override
	public TableColumn<PlayerPosition, ?> getTableColumn() {
		return tableColumn;
	}
}
