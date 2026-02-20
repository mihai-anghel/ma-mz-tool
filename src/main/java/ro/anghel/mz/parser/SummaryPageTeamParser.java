package ro.anghel.mz.parser;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ro.anghel.mz.domain.Foot;
import ro.anghel.mz.domain.FormStatus;
import ro.anghel.mz.domain.Injury;
import ro.anghel.mz.domain.InjurySeverity;
import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.Team;

public class SummaryPageTeamParser {

	private static final String PLAYER_REG_EX = """
			(?<no>\\d+)\\s+\
			(?<name>\\S+( \\S+)*)\\s+\
			(?<value>\\d+( \\d+)*) (?<valueCurrency>\\S+)\\s+\
			(?<salary>\\d+( \\d+)*) (?<salaryCurrency>\\S+)\\s+\
			(?<age>\\d+)\\s+\
			(?<born>\\d+)\\s+\
			(?<speed>\\d+)\\s+\
			(?<stamina>\\d+)\\s+\
			(?<intelligence>\\d+)\\s+\
			(?<passing>\\d+)\\s+\
			(?<shooting>\\d+)\\s+\
			(?<heading>\\d+)\\s+\
			(?<keeping>\\d+)\\s+\
			(?<ballControl>\\d+)\\s+\
			(?<tackling>\\d+)\\s+\
			(?<aerialPassing>\\d+)\\s+\
			(?<setPlays>\\d+)\\s+\
			(?<experience>\\d+)\\s+\
			(?<form>\\d+)""";

	private static final Pattern PLAYER_PATTERN = Pattern.compile(PLAYER_REG_EX,
			Pattern.MULTILINE | Pattern.DOTALL);

	private static final Comparator<Player> PLAYER_BY_NR_COMPARATOR = Comparator
			.comparingInt(Player::getNumber).thenComparingLong(Player::getId);

	private final String stringToParse;

	private final Team team;

	public SummaryPageTeamParser(final Team team, final String stringToParse) {
		super();
		this.stringToParse = stringToParse != null ? stringToParse : "";
		this.team = team;
	}

	public void parse() {
		team.setCurrency("");
		team.setName("Unknown");
		team.setId(-1);

		final Set<Player> players = new TreeSet<>(PLAYER_BY_NR_COMPARATOR);

		final Matcher matcher = PLAYER_PATTERN.matcher(stringToParse);
		long id = 1;
		while (matcher.find()) {
			final Player player = parsePlayer(id, matcher);
			players.add(player);
			++id;
		}
		team.setPlayers(players.toArray(new Player[players.size()]));
	}

	private Player parsePlayer(final long id, final Matcher matcher) {

		if (-1 == team.getId()) {
			team.setId(id);
			team.setCurrency(matcher.group("salaryCurrency"));
			team.setPriColor("#990000");
			team.setSecColor("#FF5500");
			team.setShirtPattern(12);
		}

		final short number = Short.parseShort(matcher.group("no"));
		final String name = matcher.group("name");
		final long value = Long
				.parseLong(matcher.group("value").replaceAll(" ", ""));
		final long salary = Long
				.parseLong(matcher.group("salary").replaceAll(" ", ""));
		final short age = Short.parseShort(matcher.group("age"));
		final short height = 180;
		final short weight = 80;

		final short speed = Short.parseShort(matcher.group("speed"));
		final short stamina = Short.parseShort(matcher.group("stamina"));
		final short intelligence = Short
				.parseShort(matcher.group("intelligence"));
		final short passing = Short.parseShort(matcher.group("passing"));
		final short shooting = Short.parseShort(matcher.group("shooting"));
		final short heading = Short.parseShort(matcher.group("heading"));
		final short keeping = Short.parseShort(matcher.group("keeping"));
		final short ballControl = Short
				.parseShort(matcher.group("ballControl"));
		final short tackling = Short.parseShort(matcher.group("tackling"));
		final short aerialPassing = Short
				.parseShort(matcher.group("aerialPassing"));
		final short setPlays = Short.parseShort(matcher.group("setPlays"));
		final short experience = Short.parseShort(matcher.group("experience"));
		final short form = Short.parseShort(matcher.group("form"));

		final Foot foot = Foot.BOTH;
		final FormStatus status = FormStatus.UNKNOWN;

		final int goals = 0;
		final int yc = 0;
		final int rc = 0;
		final boolean junior = age <= 18;
		final boolean tcThisSeason = false;
		final InjurySeverity injurySeverity = InjurySeverity.NONE;
		final Injury injury = new Injury(injurySeverity, "", 0);
		final short trCampNr = 0;
		final String trainingCamp = "";
		final String trainingCampDomain = "";
		return new Player(id, team, number, name, value, salary, age, height,
				weight, speed, stamina, intelligence, passing, shooting,
				heading, keeping, ballControl, tackling, aerialPassing,
				setPlays, experience, form, foot, "?", "UNKNOWN", status, goals,
				yc, rc, trainingCamp, trainingCampDomain, trCampNr, injury,
				junior, tcThisSeason);

	}
}
