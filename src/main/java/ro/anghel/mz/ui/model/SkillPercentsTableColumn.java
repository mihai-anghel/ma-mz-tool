package ro.anghel.mz.ui.model;

import ro.anghel.mz.domain.Position;
import ro.anghel.mz.domain.PositionCalculator;
import ro.anghel.ui.model.AbstractValueGetter;
import ro.anghel.ui.model.AbstractValueSetter;
import ro.anghel.ui.model.TableColumn;
import ro.anghel.ui.model.TableColumnHolder;
import ro.anghel.ui.model.ValueHandler;

public enum SkillPercentsTableColumn
		implements TableColumnHolder<PositionCalculator> {
	POS("Pos", "Position", 15,
			new AbstractValueGetter<PositionCalculator, Position>(
					Position.class) {
				@Override
				public Position getValue(final PositionCalculator data) {
					return data.getPosition();
				}
			}), //
	SPEED("Sp (%)", "Speed (%)", 15,
			new AbstractValueSetter<PositionCalculator, Double>(Double.class) {
				@Override
				public Double getValue(final PositionCalculator data) {
					return Double.valueOf(data.getPrSpeed());
				}

				@Override
				public boolean setValue(final PositionCalculator data,
						final Double value) {
					final double pr = value != null ? value.doubleValue() : 0.0;
					data.setPrSpeed(pr);
					return true;
				}
			}), //
	INTELLIGENCE("PI (%)", "Play Intelligence (%)", 15,
			new AbstractValueSetter<PositionCalculator, Double>(Double.class) {
				@Override
				public Double getValue(final PositionCalculator data) {
					return Double.valueOf(data.getPrIntelligence());
				}

				@Override
				public boolean setValue(final PositionCalculator data,
						final Double value) {
					final double pr = value != null ? value.doubleValue() : 0.0;
					data.setPrIntelligence(pr);
					return true;
				}
			}), //
	PASSING("Pa (%)", "Passing (%)", 15,
			new AbstractValueSetter<PositionCalculator, Double>(Double.class) {
				@Override
				public Double getValue(final PositionCalculator data) {
					return Double.valueOf(data.getPrPassing());
				}

				@Override
				public boolean setValue(final PositionCalculator data,
						final Double value) {
					final double pr = value != null ? value.doubleValue() : 0.0;
					data.setPrPassing(pr);
					return true;
				}
			}), //
	SHOOTING("Sh (%)", "Shooting (%)", 15,
			new AbstractValueSetter<PositionCalculator, Double>(Double.class) {
				@Override
				public Double getValue(final PositionCalculator data) {
					return Double.valueOf(data.getPrShooting());
				}

				@Override
				public boolean setValue(final PositionCalculator data,
						final Double value) {
					final double pr = value != null ? value.doubleValue() : 0.0;
					data.setPrShooting(pr);
					return true;
				}
			}), //
	HEADING("He (%)", "Heading (%)", 15,
			new AbstractValueSetter<PositionCalculator, Double>(Double.class) {
				@Override
				public Double getValue(final PositionCalculator data) {
					return Double.valueOf(data.getPrHeading());
				}

				@Override
				public boolean setValue(final PositionCalculator data,
						final Double value) {
					final double pr = value != null ? value.doubleValue() : 0.0;
					data.setPrHeading(pr);
					return true;
				}
			}), //
	KEEPING("Ke (%)", "Keeping (%)", 15,
			new AbstractValueSetter<PositionCalculator, Double>(Double.class) {
				@Override
				public Double getValue(final PositionCalculator data) {
					return Double.valueOf(data.getPrKeeping());
				}

				@Override
				public boolean setValue(final PositionCalculator data,
						final Double value) {
					final double pr = value != null ? value.doubleValue() : 0.0;
					data.setPrKeeping(pr);
					return true;
				}
			}), //
	BALL_CONTROL("BC (%)", "Ball Control (%)", 15,
			new AbstractValueSetter<PositionCalculator, Double>(Double.class) {
				@Override
				public Double getValue(final PositionCalculator data) {
					return Double.valueOf(data.getPrBallControl());
				}

				@Override
				public boolean setValue(final PositionCalculator data,
						final Double value) {
					final double pr = value != null ? value.doubleValue() : 0.0;
					data.setPrBallControl(pr);
					return true;
				}
			}), //
	TACKLING("Ta (%)", "Tackling (%)", 15,
			new AbstractValueSetter<PositionCalculator, Double>(Double.class) {
				@Override
				public Double getValue(final PositionCalculator data) {
					return Double.valueOf(data.getPrTackling());
				}

				@Override
				public boolean setValue(final PositionCalculator data,
						final Double value) {
					final double pr = value != null ? value.doubleValue() : 0.0;
					data.setPrTackling(pr);
					return true;
				}
			}), //
	AERIAL_PASSING("AP (%)", "Aerial Passing (%)", 15,
			new AbstractValueSetter<PositionCalculator, Double>(Double.class) {
				@Override
				public Double getValue(final PositionCalculator data) {
					return Double.valueOf(data.getPrAerialPassing());
				}

				@Override
				public boolean setValue(final PositionCalculator data,
						final Double value) {
					final double pr = value != null ? value.doubleValue() : 0.0;
					data.setPrAerialPassing(pr);
					return true;
				}
			}), //
	SET_PLAYS("SP (%)", "Set Plays (%)", 15,
			new AbstractValueSetter<PositionCalculator, Double>(Double.class) {
				@Override
				public Double getValue(final PositionCalculator data) {
					return Double.valueOf(data.getPrSetPlays());
				}

				@Override
				public boolean setValue(final PositionCalculator data,
						final Double value) {
					final double pr = value != null ? value.doubleValue() : 0.0;
					data.setPrSetPlays(pr);
					return true;
				}
			}), //
	SUM("Sum (%)", "Sum (%)", 15,
			new AbstractValueGetter<PositionCalculator, Double>(Double.class) {
				@Override
				public Double getValue(final PositionCalculator data) {
					return Double.valueOf(data.getPrSum());
				}
			}), STAMINA("St (%)", "Stamina (%)", 15,
					new AbstractValueSetter<PositionCalculator, Double>(
							Double.class) {
						@Override
						public Double getValue(final PositionCalculator data) {
							return Double.valueOf(data.getPrStamina());
						}

						@Override
						public boolean setValue(final PositionCalculator data,
								final Double value) {
							final double pr = value != null
									? value.doubleValue()
									: 0.0;
							data.setPrStamina(pr);
							return true;
						}
					}), //
	EXPERIENCE("Ex (%)", "Experience (%)", 15,
			new AbstractValueSetter<PositionCalculator, Double>(Double.class) {
				@Override
				public Double getValue(final PositionCalculator data) {
					return Double.valueOf(data.getPrExperience());
				}

				@Override
				public boolean setValue(final PositionCalculator data,
						final Double value) {
					final double pr = value != null ? value.doubleValue() : 0.0;
					data.setPrExperience(pr);
					return true;
				}
			}), //
	FORM("Fo (%)", "Form (%)", 15,
			new AbstractValueSetter<PositionCalculator, Double>(Double.class) {
				@Override
				public Double getValue(final PositionCalculator data) {
					return Double.valueOf(data.getPrForm());
				}

				@Override
				public boolean setValue(final PositionCalculator data,
						final Double value) {
					final double pr = value != null ? value.doubleValue() : 0.0;
					data.setPrForm(pr);
					return true;
				}
			});

	private final TableColumn<PositionCalculator, ?> tableColumn;

	<T_Value> SkillPercentsTableColumn(final String name, final String tooltip,
			final int width,
			final ValueHandler<PositionCalculator, T_Value> valueHandler) {
		tableColumn = new TableColumn<>(name, tooltip, width, valueHandler);
	}

	@Override
	public TableColumn<PositionCalculator, ?> getTableColumn() {
		return tableColumn;
	}

}
