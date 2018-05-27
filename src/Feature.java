
public enum Feature {
	RAGE(Class.BARBARIAN, 1), UNARMORED_DEFENSE_BARBARIAN(Class.BARBARIAN, 1);
	
	Class job;
	byte level;
	
	Feature(Class job, int level) {
		this.job = job;
		this.level = (byte) level;
	}
}
