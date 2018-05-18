
public class Member implements Comparable<Member> {
	private Actor actor;

	private int tier;
	private boolean identified;
	private int power;

	// constructors
	public Member(Actor actor) {
		this(actor, 1, false, 1);
	}

	public Member(Actor actor, int tier, boolean identified, int power) {
		this.actor = actor;
		this.tier = tier;
		this.identified = identified;
		this.power = power;
	}

	// methods
	public Actor getData() {
		return actor;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	public boolean unknown() {
		return (identified == false);
	}

	public void identify() {
		identified = true;
	}

	public int getPower() {
		return power;
	}

	@Override
	public int compareTo(Member other) {
		int compares = 0;

		if (power > other.power)
			compares = 1;
		else if (power < other.power)
			compares = -1;

		return compares;
	}
}
