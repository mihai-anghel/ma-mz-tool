package ro.anghel.mz.ui.model;

import ro.anghel.mz.domain.Foot;
import ro.anghel.mz.domain.FormStatus;
import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.Position;
import ro.anghel.ui.model.AbstractValueGetter;
import ro.anghel.ui.model.AbstractValueSetter;
import ro.anghel.ui.model.TableColumn;
import ro.anghel.ui.model.TableColumnHolder;
import ro.anghel.ui.model.ValueHandler;

public enum OverviewTableColumn implements TableColumnHolder<Player> {
	REST("Rest", "Rest Player", 15,
			new AbstractValueSetter<Player, Boolean>(Boolean.class) {
				@Override
				public Boolean getValue(final Player data) {
					return Boolean.valueOf(data.isResting());
				}

				@Override
				public boolean setValue(final Player data,
						final Boolean value) {
					final boolean isResting = value != null
							? value.booleanValue()
							: false;
					data.setResting(isResting);
					return true;
				}
			}), //
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
	COUNTRY("Co", "Country", 5,
			new AbstractValueGetter<Player, String>(String.class) {
				@Override
				public String getValue(final Player data) {
					return data.getCountryShortName();
				}
			}), //
	VALUE("Val", "Value", 40,
			new AbstractValueGetter<Player, Long>(Long.class) {
				@Override
				public Long getValue(final Player data) {
					return Long.valueOf(data.getValue());
				}
			}), //
	SALARY("Sal", "Salary", 40,
			new AbstractValueGetter<Player, Long>(Long.class) {
				@Override
				public Long getValue(final Player data) {
					return Long.valueOf(data.getSalary());
				}
			}), //
	AGE("Age", "Age", 15, new AbstractValueGetter<Player, Short>(Short.class) {
		@Override
		public Short getValue(final Player data) {
			return Short.valueOf(data.getAge());
		}
	}), //
	FOOT("F", "Preferred Foot", 5,
			new AbstractValueGetter<Player, Foot>(Foot.class) {
				@Override
				public Foot getValue(final Player data) {
					return data.getFoot();
				}
			}), //
	HEIGHT("Cm", "Height (cm)", 15,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getHeight());
				}
			}), //
	WEIGHT("Kg", "Weight (kg)", 15,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getWeight());
				}
			}), //
	SPEED("Sp", "Speed", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getSpeed());
				}
			}), //
	STAMINA("St", "Stamina", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getStamina());
				}
			}), //
	INTELLIGENCE("PI", "Play Intelligence", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getIntelligence());
				}
			}), //
	PASSING("Pa", "Passing", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getPassing());
				}
			}), //
	SHOOTING("Sh", "Shooting", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getShooting());
				}
			}), //
	HEADING("He", "Heading", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getHeading());
				}
			}), //
	KEEPING("Ke", "Keeping", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getKeeping());
				}
			}), //
	BALL_CONTROL("BC", "Ball Control", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getBallControl());
				}
			}), //
	TACKLING("Ta", "Tackling", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getTackling());
				}
			}), //
	AERIAL_PASSING("AP", "Aerial Passing", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getAerialPassing());
				}
			}), //
	SET_PLAYS("SP", "Set Plays", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getSetPlays());
				}
			}), //
	EXPERIENCE("Ex", "Experience", 5,
			new AbstractValueGetter<Player, Short>(Short.class) {
				@Override
				public Short getValue(final Player data) {
					return Short.valueOf(data.getExperience());
				}
			}), //
	FORM("Fo", "Form", 5, new AbstractValueGetter<Player, Short>(Short.class) {
		@Override
		public Short getValue(final Player data) {
			return Short.valueOf(data.getForm());
		}
	}), //
	GK(Position.GK.name(), Position.GK.longName() + " Rating", 15,
			new AbstractValueGetter<Player, Double>(Double.class) {
				@Override
				public Double getValue(final Player data) {
					return Double.valueOf(
							Position.GK.calculator().calculateRating(data));
				}
			}), //
	WB(Position.WB.name(), Position.WB.longName() + " Rating", 15,
			new AbstractValueGetter<Player, Double>(Double.class) {
				@Override
				public Double getValue(final Player data) {
					return Double.valueOf(
							Position.WB.calculator().calculateRating(data));
				}
			}), //
	CB(Position.CB.name(), Position.CB.longName() + " Rating", 15,
			new AbstractValueGetter<Player, Double>(Double.class) {
				@Override
				public Double getValue(final Player data) {
					return Double.valueOf(
							Position.CB.calculator().calculateRating(data));
				}
			}), //
	DM(Position.DM.name(), Position.DM.longName() + " Rating", 15,
			new AbstractValueGetter<Player, Double>(Double.class) {
				@Override
				public Double getValue(final Player data) {
					return Double.valueOf(
							Position.DM.calculator().calculateRating(data));
				}
			}), //
	CM(Position.CM.name(), Position.CM.longName() + " Rating", 15,
			new AbstractValueGetter<Player, Double>(Double.class) {
				@Override
				public Double getValue(final Player data) {
					return Double.valueOf(
							Position.CM.calculator().calculateRating(data));
				}
			}), //
	WM(Position.WM.name(), Position.WM.longName() + " Rating", 15,
			new AbstractValueGetter<Player, Double>(Double.class) {
				@Override
				public Double getValue(final Player data) {
					return Double.valueOf(
							Position.WM.calculator().calculateRating(data));
				}
			}), //
	AM(Position.AM.name(), Position.AM.longName() + " Rating", 15,
			new AbstractValueGetter<Player, Double>(Double.class) {
				@Override
				public Double getValue(final Player data) {
					return Double.valueOf(
							Position.AM.calculator().calculateRating(data));
				}
			}), //
	FW(Position.FW.name(), Position.FW.longName() + " Rating", 15,
			new AbstractValueGetter<Player, Double>(Double.class) {
				@Override
				public Double getValue(final Player data) {
					return Double.valueOf(
							Position.FW.calculator().calculateRating(data));
				}
			}), //
	STATUS("S", "Status", 5,
			new AbstractValueGetter<Player, FormStatus>(FormStatus.class) {
				@Override
				public FormStatus getValue(final Player data) {
					return data.getStatus();
				}
			}), //
	GOALS("G", "Goals", 5,
			new AbstractValueGetter<Player, Integer>(Integer.class) {
				@Override
				public Integer getValue(final Player data) {
					return Integer.valueOf(data.getGoals());
				}
			}), //
	YELLOW_CARDS("YC", "Yellow Cards", 5,
			new AbstractValueGetter<Player, Integer>(Integer.class) {
				@Override
				public Integer getValue(final Player data) {
					return Integer.valueOf(data.getYellowCards());
				}
			}), //
	RED_CARDS("RC", "Red Cards", 5,
			new AbstractValueGetter<Player, Integer>(Integer.class) {
				@Override
				public Integer getValue(final Player data) {
					return Integer.valueOf(data.getRedCards());
				}
			}), //
	TRAINING_CAMP("TC", "Training Camp", 5,
			new AbstractValueGetter<Player, String>(String.class) {
				@Override
				public String getValue(final Player data) {
					return data.getTrainingCamp();
				}
			}), //
	INJURY("Ij", "Injury", 5,
			new AbstractValueGetter<Player, String>(String.class) {
				@Override
				public String getValue(final Player data) {
					return data.getInjury().getInjuryName();
				}
			}), //
	JUNIOR("Jr", "Junior", 5,
			new AbstractValueGetter<Player, Boolean>(Boolean.class) {
				@Override
				public Boolean getValue(final Player data) {
					return Boolean.valueOf(data.isJunior());
				}
			}), //
	ALREADY_AT_TRAINING_CAMP("AT", "Already At Training Camp This Season", 5,
			new AbstractValueGetter<Player, Boolean>(Boolean.class) {
				@Override
				public Boolean getValue(final Player data) {
					return Boolean.valueOf(data.isAlreadyAtTrainingCamp());
				}
			});

	private final TableColumn<Player, ?> tableColumn;

	<T_Value> OverviewTableColumn(final String name, final String tooltip,
			final int width, final ValueHandler<Player, T_Value> valueHandler) {
		tableColumn = new TableColumn<>(name, tooltip, width, valueHandler);
	}

	@Override
	public TableColumn<Player, ?> getTableColumn() {
		return tableColumn;
	}

}
