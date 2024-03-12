import java.util.List;
import java.util.ArrayList;

public enum Boat {
    INTACT, DAMAGED, SUNK
}

public class Boat {
    private String name;
    private Cell[] cells;
    private BoatState state;
    private int hits;

    public Boat(String name, int size) {
        this.name = name;
        this.cells = new Cell[size];
        this.state = BoatState.INTACT;
        this.hits = 0;
    }

    public void hit() {
        hits++;
        if (hits >= cells.length) {
            state = BoatState.SUNK;
        } else {
            state = BoatState.DAMAGED;
        }
    }

    public boolean isSunk() {
        return state == BoatState.SUNK;
    }

    public Cell[] getCells() {
        return cells;
    }

    public Cell getCellsAtPosition(int cellPosition) {
        if (cellPosition >= 0 && cellPosition < cells.length) {
            return cells[cellPosition];
        } else {
            return null;
        }
    }

    public Cell getCellByCoordinate(int x, int y) {
        for (Cell cell : cells) {
            if (cell.getX() == x && cell.getY() == y) {
                return cell;
            }

        }
        return null;
    }

    public int getSize() {
        return cells.length;
    }

    public String getName() {
        return name;
    }

    public BoatState getState() {
        return state;
    }

    public void setState(BoatState state) {
        this.state = state;
    }

    public int getHits() {
        return hits;
    }
}
