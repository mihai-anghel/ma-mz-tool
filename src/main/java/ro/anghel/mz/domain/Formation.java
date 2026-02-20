package ro.anghel.mz.domain;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Formation {

	private final Set<PlayerPosition> starting;
	private final FormationType formationType;
	private final Team team;
	private final Set<PlayerPosition> first4Substitutes;
	private PlayerPosition lastSubstitute;

	private static final Comparator<PlayerPosition> FORMATION_COMPARATOR = Comparator
			.comparing(PlayerPosition::getPosition) //
			.thenComparing(p -> p.getPlayer().getFoot()) //
			.thenComparing(p -> p.getPlayer().getId());

	private static final Comparator<Player> PENALTY_COMPARATOR = Comparator
			.comparingInt(Player::getSetPlays).reversed()
			.thenComparingInt(Player::getShooting).reversed()
			.thenComparingInt(Player::getForm).reversed()
			.thenComparingInt(Player::getExperience).reversed()
			.thenComparingInt(Player::getStamina).reversed()
			.thenComparingLong(Player::getId);

	private static final Comparator<Player> FREE_KICK_COMPARATOR = Comparator
			.comparingInt(Player::getSetPlays).reversed()
			.thenComparingInt(Player::getPassing).reversed()
			.thenComparingInt(Player::getAerialPassing).reversed()
			.thenComparingInt(Player::getShooting).reversed()
			.thenComparingInt(Player::getForm).reversed()
			.thenComparingInt(Player::getExperience).reversed()
			.thenComparingInt(Player::getStamina).reversed()
			.thenComparingLong(Player::getId);

	private static final Comparator<PlayerPosition> SUBSTITUTED_COMPARATOR = Comparator
			.comparingInt(
					(final PlayerPosition p) -> p.getPlayer().getStamina())
			.thenComparingDouble(PlayerPosition::getRating)
			.thenComparingInt(p -> p.getPlayer().getForm())
			.thenComparingInt(p -> p.getPlayer().getExperience())
			.thenComparingInt(p -> p.getPlayer().getAge())
			.thenComparingLong(p -> p.getPlayer().getId());

	private Player captain;
	private Set<Player> penaltyShooters;
	private Set<Player> freekickShooters;

	public Formation(final FormationType formationType, final Team team) {
		this.formationType = formationType;
		this.team = team;
		starting = new TreeSet<>(FORMATION_COMPARATOR);
		first4Substitutes = new TreeSet<>(FORMATION_COMPARATOR);

		final Set<Player> donePlayers = new HashSet<>();
		final PlayerPosition[] sortedPositions = team.getSortedPositions();
		final short startingCount = formationType.getPlayerCount();

		fillStarting(sortedPositions, donePlayers, startingCount);
		fillSubstitutes(sortedPositions, donePlayers, startingCount);
		establishCaptain();
		establishShooters();
	}

	public Formation(final FormationType formationType, final Team team,
			final Player[] arrangedPlayers) {
		this.formationType = formationType;
		this.team = team;
		starting = new TreeSet<>(FORMATION_COMPARATOR);
		first4Substitutes = new TreeSet<>(FORMATION_COMPARATOR);

		final Set<Player> donePlayers = new HashSet<>();
		final PlayerPosition[] sortedPositions = team.getSortedPositions();
		final short startingCount = formationType.getPlayerCount();

		fillStartingArranged(arrangedPlayers, donePlayers);
		fillSubstitutes(sortedPositions, donePlayers, startingCount);
		establishCaptain();
		establishShooters();
	}

	private void fillStartingArranged(final Player[] arrangedPlayers,
			final Set<Player> donePlayers) {
		short i = 0;
		short cnt = 0;
		for (final Position pos : Position.values()) {
			cnt += formationType.getCount(pos);
			for (; i < cnt && i < arrangedPlayers.length; ++i) {
				starting.add(new PlayerPosition(pos, arrangedPlayers[i]));
				donePlayers.add(arrangedPlayers[i]);
			}
		}
	}

	private void fillStarting(final PlayerPosition[] sortedPositions,
			final Set<Player> donePlayers, final short startingCount) {
		for (final PlayerPosition playerPos : sortedPositions) {
			if (starting.size() == startingCount) {
				break;
			}
			final Player player = playerPos.getPlayer();
			if (donePlayers.contains(player)) {
				continue;
			}
			final Position pos = playerPos.getPosition();
			if (getAssignedCount(starting, pos) < formationType.getCount(pos)) {
				starting.add(playerPos);
				donePlayers.add(player);
			}
		}
	}

	private void fillSubstitutes(final PlayerPosition[] sortedPositions,
			final Set<Player> donePlayers, final short startingCount) {
		if (starting.size() != startingCount) {
			return;
		}

		final FormationType substFormationType = establishSubstitutesFormationType(
				team.getNonRestedPlayers().length);

		for (final PlayerPosition playerPos : sortedPositions) {
			if (first4Substitutes.size() == 4) {
				break;
			}
			if (!substFormationType.containsPosition(playerPos.getPosition())) {
				continue;
			}
			final Player player = playerPos.getPlayer();
			if (donePlayers.contains(player)) {
				continue;
			}
			if (getAssignedCount(first4Substitutes,
					playerPos.getPosition()) < substFormationType
							.getCount(playerPos.getPosition())) {
				first4Substitutes.add(playerPos);
				donePlayers.add(player);
			}
		}

		for (final PlayerPosition playerPos : sortedPositions) {
			if (lastSubstitute != null) {
				break;
			}
			if (playerPos.getPosition() == Position.GK) {
				continue;
			}
			if (!formationType.containsPosition(playerPos.getPosition())) {
				continue;
			}
			final Player player = playerPos.getPlayer();
			if (donePlayers.contains(player)) {
				continue;
			}
			if (getAssignedCount(first4Substitutes,
					playerPos.getPosition()) < formationType
							.getCount(playerPos.getPosition())) {
				lastSubstitute = playerPos;
				donePlayers.add(player);
			}
		}
	}

	private FormationType establishSubstitutesFormationType(
			final int nrOfPlayers) {
		short gkCnt = 0, wbCnt = 0, cbCnt = 0, dmCnt = 0, cmCnt = 0, wmCnt = 0,
				amCnt = 0, fwCnt = 0;
		short cnt = 0;

		final Set<PlayerPosition> startingOrderedForSubst = new TreeSet<>(
				SUBSTITUTED_COMPARATOR);
		startingOrderedForSubst.addAll(starting);

		for (final PlayerPosition playerPos : startingOrderedForSubst) {
			if (cnt == 3) {
				break;
			}
			switch (playerPos.getPosition()) {
			case WB -> {
				++wbCnt;
				++cnt;
			}
			case CB -> {
				++cbCnt;
				++cnt;
			}
			case DM -> {
				++dmCnt;
				++cnt;
			}
			case CM -> {
				++cmCnt;
				++cnt;
			}
			case WM -> {
				++wmCnt;
				++cnt;
			}
			case AM -> {
				++amCnt;
				++cnt;
			}
			case FW -> {
				++fwCnt;
				++cnt;
			}
			}
		}
		if (nrOfPlayers - starting.size() - cnt > 0) {
			gkCnt++;
		}

		return new FormationType(gkCnt, wbCnt, cbCnt, dmCnt, cmCnt, wmCnt,
				amCnt, fwCnt);
	}

	private static short getAssignedCount(
			final Set<PlayerPosition> playerPosSet, final Position position) {
		return (short) playerPosSet.stream()
				.filter(p -> p.getPosition().equals(position)).count();
	}

	public PlayerPosition[] getStarting() {
		return starting.toArray(PlayerPosition[]::new);
	}

	public PlayerPosition[] getSubstitutes() {
		final int len = lastSubstitute != null ? first4Substitutes.size() + 1
				: first4Substitutes.size();
		final PlayerPosition[] subs = first4Substitutes
				.toArray(new PlayerPosition[len]);
		if (lastSubstitute != null) {
			subs[len - 1] = lastSubstitute;
		}
		return subs;
	}

	public double getAverageRating() {
		if (starting.isEmpty()) {
			return 0;
		}
		return starting.stream().mapToDouble(PlayerPosition::getRating)
				.average().orElse(0);
	}

	public Player searchPlayerInStarting(final short playerNr) {
		return starting.stream().map(PlayerPosition::getPlayer)
				.filter(p -> p.getNumber() == playerNr).findFirst()
				.orElse(null);
	}

	public Player getCaptain() {
		return captain;
	}

	private void establishCaptain() {
		captain = starting.stream()
				.reduce((p1, p2) -> compareForCaptain(p1, p2) > 0 ? p1 : p2)
				.map(PlayerPosition::getPlayer).orElse(null);
	}

	private static int compareForCaptain(final PlayerPosition p1,
			final PlayerPosition p2) {
		final int exDif = p1.getPlayer().getExperience()
				- p2.getPlayer().getExperience();
		if (exDif != 0) {
			return exDif > 0 ? 1 : -1;
		}

		final int frDif = p1.getPlayer().getForm() - p2.getPlayer().getForm();
		if (frDif != 0) {
			return frDif > 0 ? 1 : -1;
		}

		final double rtDif = p1.getRating() - p2.getRating();
		return rtDif > 0 ? 1 : rtDif < 0 ? -1 : 0;
	}

	private void establishShooters() {
		penaltyShooters = new TreeSet<>(PENALTY_COMPARATOR);
		freekickShooters = new TreeSet<>(FREE_KICK_COMPARATOR);

		for (final PlayerPosition pos : starting) {
			final Player player = pos.getPlayer();
			penaltyShooters.add(player);
			freekickShooters.add(player);
		}

		for (final PlayerPosition pos : first4Substitutes) {
			final Player player = pos.getPlayer();
			penaltyShooters.add(player);
			freekickShooters.add(player);
		}

		if (lastSubstitute != null) {
			final Player player = lastSubstitute.getPlayer();
			penaltyShooters.add(player);
			freekickShooters.add(player);
		}
	}

	public Player[] getPenaltyShooters() {
		return penaltyShooters.toArray(Player[]::new);
	}

	public Player[] getFreekickShooters() {
		return freekickShooters.toArray(Player[]::new);
	}

	public FormationType getFormationType() {
		return formationType;
	}

	public Team getTeam() {
		return team;
	}

	public Player[] getAllPlayers() {
		final int len = starting.size() + first4Substitutes.size()
				+ (lastSubstitute != null ? 1 : 0);
		final Player[] all = new Player[len];
		int idx = 0;
		for (final PlayerPosition pos : starting) {
			all[idx++] = pos.getPlayer();
		}
		for (final PlayerPosition pos : first4Substitutes) {
			all[idx++] = pos.getPlayer();
		}
		if (lastSubstitute != null) {
			all[idx] = lastSubstitute.getPlayer();
		}
		return all;
	}
}
