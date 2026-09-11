public class Board {
    private int[] tiles;

    // Initializes a board object
    public Board(int[] tiles) {
        // TODO: initialize tiles
        this.tiles = tiles;
    }

    // Returns the tile at the specified row and column
    public int getTile(int row, int col) {
        // TODO: implement
        return tiles[row * 3 + col];
    }

    // Returns the empty tile
    public int getEmpty() {
        // TODO: implement
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col ++){
                if (tiles[row * 3 + col] == 0) {
                    return tiles[row * 3 + col];
                }
            }
        }
        return 0;
    }

    // Randomizes the board
    public void shuffle() {
        // TODO: implement
    }
}
