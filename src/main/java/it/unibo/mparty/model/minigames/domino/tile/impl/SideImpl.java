package it.unibo.mparty.model.minigames.domino.tile.impl;

import it.unibo.mparty.model.minigames.domino.tile.api.Side;

import java.util.Objects;

/**
 * Implementation of the {@link Side} interface. Represents a side of a domino tile.
 */
public class SideImpl implements Side {

    private final int value;
    private boolean isMatched;

    /**
     * Constructs a new {@link SideImpl} with the specified value.
     *
     * @param value the value of the side
     */
    public SideImpl(final int value) {
        this.value = value;
        this.isMatched = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMatched() {
        return this.isMatched;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMatched() {
        this.isMatched = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "SideImpl{" +
                "value=" + this.value +
                ", isMatched=" + this.isMatched +
                "}\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SideImpl side = (SideImpl) o;
        return this.value == side.value && this.isMatched == side.isMatched;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.value, this.isMatched);
    }
}
