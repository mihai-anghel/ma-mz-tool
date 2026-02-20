package ro.anghel.mz.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Team {
	private Player[] players;

	private String currency = "";

	private long id = -1;

	private String name = "";

	private String priColor = "#FFA500"; // ORANGE
	private String secColor = "#8B0000"; // RED.darker()
	private int shirtPattern = 1;

	public Team() {
		//
	}

	public Team(final long aId, final String aName, final String aPriColor,
			final String aSecColor, final int aShirtPattern) {
		players = new Player[] {};
		id = aId;
		name = aName;
		priColor = aPriColor;
		secColor = aSecColor;
		shirtPattern = aShirtPattern;
	}

	public String getPriColor() {
		return priColor;
	}

	public String getSecColor() {
		return secColor;
	}

	public void setPriColor(final String aPriColor) {
		priColor = aPriColor;
	}

	public void setSecColor(final String aSecColor) {
		secColor = aSecColor;
	}

	public Player[] getPlayers() {
		return players.clone();
	}

	public void setPlayers(final Player[] aPlayers) {
		players = aPlayers;
	}

	public Player[] getNonRestedPlayers() {
		final ArrayList<Player> nonRested = new ArrayList<>();

		for (final Player player : players) {
			if (!player.isResting()) {
				nonRested.add(player);
			}
		}
		return nonRested.toArray(new Player[nonRested.size()]);
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(final String aCurrency) {
		currency = aCurrency;
	}

	public long getId() {
		return id;
	}

	public void setId(final long aId) {
		id = aId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String aName) {
		name = aName;
	}

	public int getShirtPattern() {
		return shirtPattern;
	}

	public void setShirtPattern(final int aShirtPattern) {
		shirtPattern = aShirtPattern;
	}

	public Player[] getSortedByPosition(final Position position) {
		final PositionCalculator calc = position.calculator();
		final Comparator<Player> comparator = Comparator
				.comparingDouble(calc::calculateRating)
				.thenComparingLong(Player::getId);
		final Set<Player> set = new TreeSet<>(comparator);
		set.addAll(Arrays.asList(players));
		return set.toArray(new Player[set.size()]);
	}

	public PlayerPosition[] getSortedPositions() {
		final Set<PlayerPosition> sortedPositions = new TreeSet<>(
				(aPlayerPosition1, aPlayerPosition2) -> {
					final double v1 = aPlayerPosition1.getRating();
					final double v2 = aPlayerPosition2.getRating();
					return v1 < v2 ? 1 : -1;
				});

		for (final Player player : players) {
			if (!player.isResting()) {
				sortedPositions.add(new PlayerPosition(Position.GK, player));
				sortedPositions.add(new PlayerPosition(Position.WB, player));
				sortedPositions.add(new PlayerPosition(Position.CB, player));
				sortedPositions.add(new PlayerPosition(Position.DM, player));
				sortedPositions.add(new PlayerPosition(Position.CM, player));
				sortedPositions.add(new PlayerPosition(Position.WM, player));
				sortedPositions.add(new PlayerPosition(Position.AM, player));
				sortedPositions.add(new PlayerPosition(Position.FW, player));
			}
		}

		return sortedPositions
				.toArray(new PlayerPosition[sortedPositions.size()]);
	}

}
