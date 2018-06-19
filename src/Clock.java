
public class Clock {
	public enum Length {
		FOUR, SIX, EIGHT
	}

	public class Timer {
		// fields
		private Length length;
		private int timeRemaining;

		// constructors
		public Timer(Length length) {
			this.length = length;

			if (length.equals(Length.FOUR))
				this.timeRemaining = 4;
			else if (length.equals(Length.SIX))
				this.timeRemaining = 6;
			else if (length.equals(Length.EIGHT))
				this.timeRemaining = 8;
		}

		public Length length() {
			return length;
		}

		public int timeRemaining() {
			return timeRemaining;
		}
	}
}
