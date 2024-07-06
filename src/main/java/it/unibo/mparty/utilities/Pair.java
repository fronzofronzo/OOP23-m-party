package it.unibo.mparty.utilities;

public class Pair<X,Y> {
    private final X x;
	private final Y y;
	
	public Pair(final X x, final Y y) {
		super();
		this.x = x;
		this.y = y;
	}

	public X getFirst() {
		return this.x;
	}

	public Y getSecond() {
		return this.y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.x == null) ? 0 : this.x.hashCode());
		result = prime * result + ((this.y == null) ? 0 : this.y.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (this.x == null) {
			if (other.x != null)
				return false;
		} else if (!this.x.equals(other.x))
			return false;
		if (this.y == null) {
            return other.y == null;
		} else return this.y.equals(other.y);
    }

	@Override
	public String toString() {
		return "Pair [x=" + this.x + ", y=" + this.y + "]";
	}
}
