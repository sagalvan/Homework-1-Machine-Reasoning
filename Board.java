/** 
 * A Board class that implements the 8-Tile puzzle game. Includes functions to calculate the heuristic of the board
 * 
 */
import java.util.Random;

public class Board {
    private int[] tiles;
    private int emptyRow;
    private int emptyCol;

    
    /**
     * Initializes a board with the solved start state. Sets the empty tile location
     */
    public Board() {
        int[] startState = {0,1,2,3,4,5,6,7,8};
        this.tiles = startState;
        emptyRow = 0;
        emptyCol = 0;
    }


    /**
     * Initializes a board with a given start state
     * 
     * @param nums  The given start state of the board
     */
    public Board(int[] nums) {
        this.tiles = nums.clone();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (nums[row * 3 + col] == 0) {
                    emptyRow = row;
                    emptyCol = col;
                }
            }
        }
    }

    //Constructor to create board from an existing board

    public Board(Board other) {
        this.tiles = new int[9];
    
        for (int i = 0; i < 9; i++) {
            this.tiles[i] = other.tiles[i];
        }
    
        this.emptyRow = other.emptyRow;
        this.emptyCol = other.emptyCol;
    }


    /**
     * Gets the row where the empty tile is located
     * 
     * @return  Integer value of the row
     */
    public int getEmptyRow() {
        return emptyRow;
    }


    /**
     * Gets the column where the empty tile is located
     * 
     * @return  Integer value of the column
     */
    public int getEmptyCol() {
        return emptyCol;
    }

    /**
     * Gets the tiles of the board
     * 
     * @return  the int array of tiles in a board
     */
    public int[] getTiles() {
        return tiles;
    }

    /**
     * Gets the tile from the specified (row, col) location on the board
     * 
     * @param row   Integer value of the row
     * @param col   Integer value of the column
     * @return      The tile value at the specified (row, col)
     */
    public int getTile(int row, int col) {
        return tiles[row * 3 + col];
    }


    /**
     * Gets the (row, col) location of a the tile of a specified value
     * 
     * @param value The interested tile value
     * @return      An int array containing the [row, col] values
     */
    public int[] getTile( int value) {
        int[] location = new int[2];

        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                if (tiles[row * 3 + col]== value){
                    location[0] = row;
                    location[1] = col;
                }
            }
        }
        return location;
    }

   
    /**
     * Chekcs if the specified tile (row, col) is adjacent with the empty tile.
     * 
     * @param row   Integer value of the row
     * @param col   Integer value of the column
     * @return      True if the specified tile is adjacent to the empty tile. False if otherwise.
     */
    public boolean isAdjacent(int row, int col) {
        int valrow = row;
        int valcol = col;

        if (valrow == emptyRow){
            if(valcol == emptyCol + 1 || valcol == emptyCol - 1){
                return true;
            }
            
        }
        if (valcol == emptyCol){
            if(valrow == emptyRow + 1 || valrow == emptyRow - 1){
                return true;
            }
        
        }

        return false;
    }


    /**
     * Checks if the tile at a specified location is in the bounds of the board
     * 
     * @param row   Integer value of the row
     * @param col   Integer value of the column
     * @return      A true or false whether the tile is in the board bounds
     */
    public boolean inBounds(int row, int col) {
        if (row >= 3 || row < 0 || col >= 3 || col < 0) {
            return false;
        }
        return true;
    }

    
    /**
     * Moves tile(row, col) with the empty tile. If move is successful, returns true. Otherwise returns false.
     * 
     * @param row   Integer value of the row
     * @param col   Integer value of the column
     * @return      Returns true if move was sucessful, false if otherwise
     */
    public boolean moveTile(int row, int col) {
        if (inBounds(row, col) == false) {
            return false;
        }

        if (isAdjacent(row, col)) {
            int temp = tiles[emptyRow * 3 + emptyCol]; // should be 0
            tiles[emptyRow * 3 + emptyCol] = tiles[row * 3 + col];
            tiles[row * 3 + col] = temp;
            emptyRow = row;
            emptyCol = col;
            return true;
            }

        return false;
    } 


    /**
     * Shuffles the board by randomly moving tiles by a set number of steps
     * 
     * @param numSteps  Specified number of random moves on the board
     */
    public void shuffle(int numSteps) {
        Random random = new Random();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < numSteps; i++) {
            int[] dir = directions[random.nextInt(4)];
            while (moveTile((emptyRow + dir[0]), (emptyCol + dir[1])) == false) { // if a chosen direction is out of bounds, choose another random direction
                dir = directions[random.nextInt(4)];
            }    
        }
    }

    
    /**
     * Checks if the current state of the board is solved
     * 
     * @return  Returns a true if the current state of the board is solved, false if otherwise
     */
    public boolean isSolved() {
        int expectedValue = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int tileValue = tiles[row * 3 + col];
                if (tileValue != expectedValue){
                    return false;
                }
                expectedValue++;
            }
        }
        return true;
    }

 
    /**
     * Gets the neighboring tiles of the blank tile
     * 
     * @return  An array of valid neighboring coordiates
     */
    public int[][] getNeighbors() {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] neighbors = {{-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}};

        for (int i = 0; i < 4; i++) {
            int[] dir = directions[i];
            if (inBounds(emptyRow + dir[0], emptyCol + dir[1]) == true) {
                int[] neighborCoord = new int[2];
                neighborCoord[0] = emptyRow + dir[0];
                neighborCoord[1] = emptyCol + dir[1];
                neighbors[i] = neighborCoord;
            }
        }

        return neighbors;
    }

   
    /**
     * Optional function to print the board as a 3x3 board
     */
    public void printClean() {
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++) {
                System.out.print(tiles[row * 3 + col]);
            }
            System.out.println();
        }
    }

    public String toString() {
        String result = "";

        for (int i = 0; i < tiles.length; i++) {
            result += tiles[i];
        }

        return result;
    }

    
    /**
     * Calculates the number of misplaced tiles of a given board
     * 
     * @return  Number of misplaced tiles
     */
    public int calculateh1() {
        if (isSolved() == true) {
            return 0;
        }

        int numMisplaced = 0;
        int expectedValue = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int tileValue = tiles[row * 3 + col];
                if (tileValue != expectedValue && tileValue != 0) {
                    numMisplaced = numMisplaced + 1;
                }
                expectedValue++;
            }
        }
        return numMisplaced;
    }

  
    /**
     * Calculates the total manhattan distance of each misplaced tile
     * 
     * @return  The manhattan distance
     */
    public int calculateh2() {
        if (isSolved() == true) {
            return 0;   
        }

        int manhattanVal = 0;
        int expectedValue = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int tileValue = tiles[row * 3 + col];
                if (tileValue != expectedValue && tileValue != 0) {
                    int expectedRow = tileValue/3;
                    int expectedCol = tileValue%3;
                    manhattanVal = manhattanVal + (Math.abs(row - expectedRow) + Math.abs(col - expectedCol));
                }
                expectedValue++;
            }
        }
        return manhattanVal;
    }

    
    /**
     * A helper function for calculateh3() that finds the first misplaced tile in the board
     * 
     * @return  the coordinates [row, col] of the miplaced tile
     */
    public int[] findMisplaced() {
        int[] location = {-1, -1};

        for( int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (tiles[row * 3 + col] != row * 3 + col) {
                    location[0] = row;
                    location[1] = col;
                    return location;
                }
            }
        }

        return location;
    }

    
    /**
     * Implement relaxed agency function in paper.
     * 
     * @return  the number of steps it takes to solve the board using relaxed agency
     */
    public int calculateh3() {
        Board copy = new Board(tiles);
        int count = 0;

        while(copy.isSolved() != true) {
            if(copy.emptyRow == 0 && copy.emptyCol == 0) { // If the blank is in its own goal
                int[] location = copy.findMisplaced(); // Find the first closest misplaced tile
                int value = copy.tiles[location[0] * 3 + location[1]]; // Get the value of the misplaced tile
                copy.tiles[0] = value; // Swap the tiles
                copy.tiles[location[0] * 3 + location[1]] = 0;
                copy.emptyRow = location[0]; // reinitalize empty tile location
                copy.emptyCol = location[1];
                count++;
            }

            else {
                int value = copy.emptyRow * 3 + copy.emptyCol; // Get the value that should be in blank's position
                int[] location = copy.getTile(value); // Get the position of the value
                copy.tiles[copy.emptyRow * 3 + copy.emptyCol] = value; // Swap the tiles
                copy.tiles[location[0] * 3 + location[1]] = 0;
                copy.emptyRow = location[0]; // reinitalize empty tile location
                copy.emptyCol = location[1];
                count++;
            }
        }

        return count;
    }
}
