package it.unibo.mparty.utilities;

/**
 * This class models a generic Pair of two items. The pair is set once it is
 * created and has methods to access to the two elements.
 * @param <X> - type of first element.
 * @param <Y> - type of second element.
 */
public class Pair<X,Y> {
    private final X x;
	private final Y y;

	/**
	 * Constructor for a new Pair.
	 * @param x - first element of pair.
	 * @param y - second element of pair.
	 */
	public Pair(final X x, final Y y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Method to get the first element of pair.
	 * @return - first element.
	 */
	public X getFirst() {
		return this.x;
	}

	/**
	 * Method to get the second element of pair.
	 * @return - second element of pair.
	 */
	public Y getSecond() {
		return this.y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.x == null) ? 0 : this.x.hashCode());
		result = prime * result + ((this.y == null) ? 0 : this.y.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pair other = (Pair) obj;
		if (this.x == null) {
			if (other.x != null)
				return false;
		} else if (!this.x.equals(other.x)) {
			return false;
		}
		if (this.y == null) {
            return other.y == null;
		} else {
			return this.y.equals(other.y);
		}
    }

	/**
	 {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Pair [x=" + this.x + ", y=" + this.y + "]";
	}
}
