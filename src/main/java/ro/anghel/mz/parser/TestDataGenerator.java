package ro.anghel.mz.parser;

import java.util.Arrays;
import java.util.List;

import ro.anghel.genetic.util.RandomUtils;
import ro.anghel.mz.domain.Foot;
import ro.anghel.mz.domain.FormStatus;
import ro.anghel.mz.domain.Injury;
import ro.anghel.mz.domain.InjurySeverity;
import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.Team;
import ro.anghel.mz.ui.renderer.ColorCalculator;

public class TestDataGenerator {

	private static final String[] F_NAMES = new String[] { "Mihai", "Adrian",
			"Viorel", "Tiberius", "Dan", "Ioan", "Marius", "Cristian", "Lucian",
			"Sorin", "Valentin", "Gabriel", "Marian", "Ovidiu", "Iulian",
			"Radu", "Florin", "Petre", "Daniel", "Cornel", "Claudiu", "George",
			"Ulrich", "Udo", "Markus", "Jan" };
	private static final String[] L_NAMES = new String[] { "Hagi", "Popescu",
			"Petrescu", "Camataru", "Mutu", "Chivu", "Munteanu", "Raducioiu",
			"Dobrin", "Balaci", "Belodedici", "Dumitrescu", "Stelea", "Lupu",
			"Lupescu", "Contra", "Lung", "Ilie", "Rotariu", "Schmidt", "Klein",
			"Boloni", "Gross" };
	private static final List<String> F_NAMES_DE = Arrays.asList("Ulrich",
			"Udo", "Markus", "Jan");
	private static final List<String> L_NAMES_DE = Arrays.asList("Schmidt",
			"Klein", "Boloni", "Gross");

	private static final String[][] COUNTRIES = { { "RO", "ROMANIA" },
			{ "DE", "GERMANY" }, { "AT", "AUSTRIA" }, { "CH", "SWITZERLAND" },
			{ "LU", "LUXEMBOURG" }, { "BE", "BELGIUM" },
			{ "LI", "LIECHTENSTEIN" } };

	private final Team team;

	public TestDataGenerator(final Team team) {
		this.team = team;
	}

	public void generateTestData(final short playerCount) {
		final Player[] players = new Player[playerCount];
		for (short i = 0; i < players.length; ++i) {
			final short age = (short) random(15, 42);
			final String fName = randomOf(F_NAMES);
			final String lName = randomOf(L_NAMES);
			final String name = fName + " " + lName;
			final boolean isGerman = F_NAMES_DE.contains(fName)
					|| L_NAMES_DE.contains(lName);
			final String[] country = isGerman ? randomOf(COUNTRIES)
					: COUNTRIES[0];
			final InjurySeverity injSev = random(100) < 10
					? randomOf(InjurySeverity.values())
					: InjurySeverity.NONE;
			final Injury injury = new Injury(injSev, injSev.niceName(),
					injSev.ordinal());
			players[i] = new Player(i, team, (short) (i + 1), name,
					random(100000, 1000000), random(1000, 15000), age,
					(short) random(150, 220), (short) random(50, 120),
					(short) random(11), (short) random(11), (short) random(11),
					(short) random(11), (short) random(11), (short) random(11),
					(short) random(11), (short) random(11), (short) random(11),
					(short) random(11), (short) random(11), (short) random(11),
					(short) random(11), randomOf(Foot.values()), country[0],
					country[1], FormStatus.UNKNOWN, random(25), random(15),
					random(8), "", "", (short) 0, injury, age < 19, false);
		}
		team.setName("Generated Test Team");
		team.setPlayers(players);
		team.setCurrency("EUR");
		team.setPriColor(randomColor());
		team.setSecColor(randomColor());
		team.setShirtPattern((int) random(1, 20));
	}

	private static String randomColor() {
		return ColorCalculator.toHexColor(random(255), random(255),
				random(255));
	}

	private static <T> T randomOf(final T[] values) {
		final int idx = random(values.length);
		return values[idx];
	}

	private static int random(final int bound) {
		return RandomUtils.nextInt(bound);
	}

	private static long random(final long origin, final long bound) {
		return RandomUtils.nextLong(origin, bound);
	}

}
