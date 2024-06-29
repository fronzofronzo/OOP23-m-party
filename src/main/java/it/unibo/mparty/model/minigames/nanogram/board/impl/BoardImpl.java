package it.unibo.mparty.model.minigames.nanogram.board.impl;

import it.unibo.mparty.model.minigames.nanogram.board.api.Board;
import it.unibo.mparty.utilities.Position;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the {@link Board} interface for a Nanogram game.
 * This class represents the game board and provides methods to interact with the board state.
 */
public class BoardImpl implements Board { //SimpleBoard
    //private final Map<Position, Boolean> grid;
    //private final Map<Position, Boolean> showGrid; //todo: put into the sotto classe of BoadImpl, called as HardBoard
    // HardBoard --extend--> Board   //interfaces
    // HardBoardImpl --implement--> HardBoard  //classes

    private final Map<Position, Boolean> hitCells;
    private final int size;

    public BoardImpl(int size) {
        this.size = size;
        hitCells = new HashMap<>();
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void setCellState(Position position, boolean state) {
        hitCells.put(position, state);
    }

    @Override
    public boolean getCellState(Position position) {
        return hitCells.get(position);
    }


//    /**
//     * Constructs a new BoardImpl instance with the specified grid, showGrid, and size.
//     *
//     * @param grid     the map representing the complete grid of cell states.
//     * @param showGrid the map representing the visible grid of cell states.
//     * @param size     the size of the board (assuming it's a square board).
//     */
//    public BoardImpl(final Map<Position, Boolean> grid, final Map<Position, Boolean> showGrid, final int size) {
//        this.grid = grid;
//        this.showGrid = showGrid;
//        this.size = size;
//    }
//
//    /**
//     * Constructs a new BoardImpl instance with the specified grid and size.
//     * Initializes showGrid as an empty map.
//     *
//     * @param grid the map representing the complete grid of cell states.
//     * @param size the size of the board (assuming it's a square board).
//     */
//    public BoardImpl(final Map<Position, Boolean> grid, final int size) {
//        this.grid = grid;
//        this.showGrid = new HashMap<Position, Boolean>();
//        this.size = size;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public int getSize() {
//        return this.size;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public Map<Position, Boolean> getShowGrid() {
//        return this.showGrid;
//    } //todo -> hardboar
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public Boolean getCellState(final int x, final int y) {
//        return this.grid.get(new Position(x, y));
//    }
//
//    /**
//     * {@inheritDoc}
//     */

}
