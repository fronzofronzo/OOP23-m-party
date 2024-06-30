package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Side;

import java.util.Objects;

public class SideImpl implements Side {

    private final int value;
    private boolean isMatched;

    public SideImpl(final int value, final boolean isMatched) {
        this.value = value;
        this.isMatched = isMatched;
    }

    public SideImpl(final int value) {
        this.value = value;
        this.isMatched = false;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public boolean isMatched() {
        return this.isMatched;
    }

    @Override
    public void setMatched() {
        this.isMatched = true;
    }

    @Override
    public String toString() {
        return "SideImpl{" +
                "value=" + value +
                ", isMarched=" + isMatched +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SideImpl side = (SideImpl) o;
        return value == side.value && isMatched == side.isMatched;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, isMatched);
    }
}
