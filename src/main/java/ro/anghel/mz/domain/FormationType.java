package ro.anghel.mz.domain;

import java.util.EnumMap;
import java.util.Map;
import java.util.StringJoiner;

public class FormationType {

	private final Map<Position, Short> countMap;

	public FormationType(final short countOfGK, final short countOfWB,
			final short countOfCB, final short countOfDM, final short countOfCM,
			final short countOfWM, final short countOfAM,
			final short countOfFW) {
		countMap = new EnumMap<>(Position.class);
		countMap.put(Position.GK, countOfGK);
		countMap.put(Position.WB, countOfWB);
		countMap.put(Position.CB, countOfCB);
		countMap.put(Position.DM, countOfDM);
		countMap.put(Position.CM, countOfCM);
		countMap.put(Position.WM, countOfWM);
		countMap.put(Position.AM, countOfAM);
		countMap.put(Position.FW, countOfFW);
	}

	public FormationType(final FormationType formationType) {
		countMap = new EnumMap<>(Position.class);
		countMap.putAll(formationType.countMap);
	}

	public short getCount(final Position position) {
		return countMap.getOrDefault(position, (short) 0);
	}

	public short getCount(final PositionGroup group) {
		short cnt = 0;
		for (final Position pos : group.positions()) {
			cnt += getCount(pos);
		}
		return cnt;
	}

	public short getPlayerCount() {
		short total = 0;
		for (final short count : countMap.values()) {
			total += count;
		}
		return total;
	}

	public boolean containsPosition(final Position position) {
		return getCount(position) > 0;
	}

	// --- String representations ---

	public String toGrouppedString(final boolean includeGLK) {
		final StringJoiner sj = new StringJoiner("-");
		for (final PositionGroup grp : PositionGroup.values()) {
			if (grp != PositionGroup.GLK || includeGLK) {
				sj.add(String.valueOf(getCount(grp)));
			}
		}
		return sj.toString();
	}

	@Override
	public String toString() {
		return toShortString();
	}

	public String toPositionString() {
		final StringJoiner sj = new StringJoiner("-");
		for (final Position pos : Position.values()) {
			sj.add(getCount(pos) + pos.name());
		}
		return sj.toString();
	}

	public String toPositionShortString() {
		final StringJoiner sj = new StringJoiner("-");
		for (final Position pos : Position.values()) {
			sj.add(String.valueOf(getCount(pos)));
		}
		return sj.toString();
	}

	public String toShortString() {
		final StringBuilder sb = new StringBuilder(toGrouppedString(false));
		sb.append("     (").append(toPositionShortString()).append(")");
		return sb.toString();
	}

	public String toLongString() {
		final StringBuilder sb = new StringBuilder(toGrouppedString(false));
		sb.append(" (").append(toPositionString()).append(")");
		return sb.toString();
	}
}
