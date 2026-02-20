package ro.anghel.mz.domain;

import static ro.anghel.mz.domain.PositionGroup.ATT;
import static ro.anghel.mz.domain.PositionGroup.DEF;
import static ro.anghel.mz.domain.PositionGroup.GLK;
import static ro.anghel.mz.domain.PositionGroup.MID;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class GroupedFormationType {

	private static final int STARTING_COUNT = 11;

	private final Map<PositionGroup, Short> countMap = new EnumMap<>(
			PositionGroup.class);

	private final Set<FormationType> formationTypes = new TreeSet<>(
			Comparator.comparing(FormationType::toString));

	private static final GroupedFormationType[] GROUPED_FORMATION_TYPES = FormationTypeGenerator
			.generate(STARTING_COUNT);

	public static GroupedFormationType[] all() {
		return GROUPED_FORMATION_TYPES.clone();
	}

	public GroupedFormationType(final short aGoalkeeperCount,
			final short aDefenderCount, final short aMidfielderCount,
			final short aAttackerCount) {
		countMap.put(GLK, aGoalkeeperCount);
		countMap.put(DEF, aDefenderCount);
		countMap.put(MID, aMidfielderCount);
		countMap.put(ATT, aAttackerCount);
	}

	public GroupedFormationType(final FormationType formationType) {
		this(formationType.getCount(GLK), formationType.getCount(DEF),
				formationType.getCount(MID), formationType.getCount(ATT));
	}

	public short getCount(final PositionGroup group) {
		return countMap.get(group);
	}

	public FormationType[] getFormationTypes() {
		return formationTypes.toArray(new FormationType[formationTypes.size()]);
	}

	public boolean addFormationType(final FormationType aFormationType) {
		if (countMap.get(DEF) == aFormationType.getCount(DEF)
				&& countMap.get(MID) == aFormationType.getCount(MID)
				&& countMap.get(ATT) == aFormationType.getCount(ATT)) {
			return formationTypes.add(aFormationType);
		}
		return false;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		String sep = "";
		for (final PositionGroup grp : PositionGroup.values()) {
			if (!grp.equals(GLK)) {
				sb.append(sep).append(getCount(grp));
				sep = "-";
			}
		}
		return sb.toString();
	}

}
