package ro.anghel.mz.domain;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import java.util.TreeMap;

public class FormationTypeGenerator {

	private static final Comparator<String> GROUPED_FORMATION_TYPE_COMPARATOR = String::compareTo;

	public static GroupedFormationType[] generate(final int startingCount) {
		final Map<String, GroupedFormationType> groupedMap = new TreeMap<>(
				GROUPED_FORMATION_TYPE_COMPARATOR);

		// Map Position -> possible counts
		final Map<Position, short[]> possibleCounts = new EnumMap<>(
				Position.class);
		possibleCounts.put(Position.GK, Position.GK.counts());
		possibleCounts.put(Position.WB, Position.WB.counts());
		possibleCounts.put(Position.CB, Position.CB.counts());
		possibleCounts.put(Position.DM, Position.DM.counts());
		possibleCounts.put(Position.CM, Position.CM.counts());
		possibleCounts.put(Position.WM, Position.WM.counts());
		possibleCounts.put(Position.AM, Position.AM.counts());
		possibleCounts.put(Position.FW, Position.FW.counts());

		// Start recursion
		generateRecursive(new EnumMap<>(Position.class), // current counts per
															// position
				new EnumMap<>(PositionGroup.class), // current sum per group
				possibleCounts, groupedMap, startingCount, Position.values(),
				0);

		return groupedMap.values().toArray(new GroupedFormationType[0]);
	}

	private static void generateRecursive(
			final Map<Position, Short> currentCounts,
			final Map<PositionGroup, Short> groupSums,
			final Map<Position, short[]> possibleCounts,
			final Map<String, GroupedFormationType> groupedMap,
			final int startingCount, final Position[] positions,
			final int index) {
		if (index >= positions.length) {
			// check total players match startingCount
			final int total = currentCounts.values().stream()
					.mapToInt(Short::intValue).sum();

			// check group min counts
			boolean valid = total == startingCount;
			for (final PositionGroup group : PositionGroup.values()) {
				final short sum = groupSums.getOrDefault(group, (short) 0);
				if (sum < group.minCount()) {
					valid = false;
					break;
				}
			}

			if (valid) {
				final FormationType type = new FormationType(
						currentCounts.getOrDefault(Position.GK, (short) 1),
						currentCounts.getOrDefault(Position.WB, (short) 0),
						currentCounts.getOrDefault(Position.CB, (short) 0),
						currentCounts.getOrDefault(Position.DM, (short) 0),
						currentCounts.getOrDefault(Position.CM, (short) 0),
						currentCounts.getOrDefault(Position.WM, (short) 0),
						currentCounts.getOrDefault(Position.AM, (short) 0),
						currentCounts.getOrDefault(Position.FW, (short) 0));

				final String key = type.toGrouppedString(true);
				GroupedFormationType grp = groupedMap.get(key);
				if (grp == null) {
					grp = new GroupedFormationType(type);
					groupedMap.put(key, grp);
				}
				grp.addFormationType(type);
			}
			return;
		}

		final Position pos = positions[index];
		final PositionGroup group = posGroup(pos); // helper to find group for a
													// position
		final short currentGroupSum = groupSums.getOrDefault(group, (short) 0);

		for (final short cnt : possibleCounts.get(pos)) {
			final short newGroupSum = (short) (currentGroupSum + cnt);
			final int totalAssigned = currentCounts.values().stream()
					.mapToInt(Short::intValue).sum() + cnt;

			// enforce group max and total starting count
			if (newGroupSum > group.maxCount()
					|| totalAssigned > startingCount) {
				continue;
			}

			// update maps
			currentCounts.put(pos, cnt);
			groupSums.put(group, newGroupSum);

			generateRecursive(currentCounts, groupSums, possibleCounts,
					groupedMap, startingCount, positions, index + 1);

			// backtrack
			currentCounts.remove(pos);
			groupSums.put(group, currentGroupSum);
		}
	}

	// helper: find the PositionGroup a Position belongs to
	private static PositionGroup posGroup(final Position pos) {
		for (final PositionGroup group : PositionGroup.values()) {
			for (final Position p : group.positions()) {
				if (p == pos) {
					return group;
				}
			}
		}
		throw new IllegalArgumentException("Unknown position: " + pos);
	}
}
