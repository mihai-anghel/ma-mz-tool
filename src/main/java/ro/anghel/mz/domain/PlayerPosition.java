package ro.anghel.mz.domain;

public class PlayerPosition {

	private final Position position;

	private final Player player;

	public PlayerPosition(final Position aPosition, final Player aPlayer) {
		position = aPosition;
		player = aPlayer;
	}

	public Position getPosition() {
		return position;
	}

	public Player getPlayer() {
		return player;
	}

	public double getRating() {
		return position.calculator().calculateRating(player);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(position.name()).append(" ").append(player).append(" ")
				.append(getRating());
		return sb.toString();
	}
}
