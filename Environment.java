/* CSC-207-01 Spring 2023
 * Names: Connor Durkin, Timur Kasimov
 * AI Lab 2
 * Acknowledgements: previous labs, textbook, prof. eliott
 */

// Creates environment for agent(s) to navigate.

// System.out.println statement is used for testing purposes in this class. Uncomment if needed.

import java.util.*; //for Random

public class Environment {

    // instance fields

    private static int nextID = 1;
    private static Random rand = new Random(System.nanoTime()); // needs to be outside of the method that is
                                                                // called repeatedly very quickly

    private int environmentID;
    private int finalState;
    private int rows;
    private int columns;
    private int[][] grid;

    private boolean doorLeft;
    private boolean doorRight;

    // constructor for environment
    Environment(int rows, int columns) {

        this.environmentID = nextID; // assigning ID
        nextID++; // incrementing nextID

        this.rows = rows;
        this.columns = columns;
        grid = new int[rows][columns]; // crating the grid as a 2d array
        // filling in the grid with consecutive numbers
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                grid[r][c] = (c + 1) + columns * r;
            }
        }

        finalState = 2; // defining final state based on the grid

    }

    // GENERAL INSTANCE FIELD METHODS
    public int getEnvironmentID() {
        return environmentID;
    }

    public int getFinalState() {
        return finalState;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    // OTHER METHODS

    // finds the nextState of agent
    public int nextState(int currentState, int number, int[] strategyDistribution) {

        doorLeft = rand.nextBoolean(); // setting up doors
        doorRight = rand.nextBoolean();

        if (number <= strategyDistribution[0]) { // up?
            currentState = movingUp(currentState);

        } else if (number <= strategyDistribution[1]) { // down?
            currentState = movingDown(currentState);

        } else if (number <= strategyDistribution[2]) { // left?
            currentState = movingLeft(currentState);

        } else if (number <= strategyDistribution[3]) { // right?
            currentState = movingRight(currentState);

        } else { // still
            // System.out.println("Stayed Still");
        }

        return currentState;
    }

    // agent attempts to move up
    public int movingUp(int currentState) {

        if (currentState > getColumns()) { // if not in the first row
            if ((currentState == 7 && doorLeft) || (currentState == 9 && doorRight)) { // if (in 7 AND door above) OR
                                                                                       // (if in 9 AND door above)
                // System.out.println("HIT DOOR!"); //do nothing
            } else {
                currentState -= getColumns(); // moving up one row
                // System.out.println("Moved up");
            }
        } else {
            // System.out.println("Failed to move up");
        }
        return currentState;
    }

    // agent attempts to move down
    public int movingDown(int currentState) {

        if (currentState < grid[getRows() - 1][0]) { // if not in the last row
            if ((currentState == 4 && doorLeft) || (currentState == 6 && doorRight)) { // if (in 4 AND door below) OR
                                                                                       // (if in 6 AND door below)
                // System.out.println("HIT DOOR!");
            } else {
                currentState += getColumns(); // moving down one row
                // System.out.println("Moved down");
            }
        } else {
            // System.out.println("Failed to move down");
        }
        return currentState;
    }

    // agent attempts to move left
    public int movingLeft(int currentState) {

        if (0 != (currentState - 1) % getColumns()) { // while not in the first column
            currentState -= 1; // moving left one column
            // System.out.println("Moved left");
        } else {
            // System.out.println("Failed to move left");
        }
        return currentState;
    }

    // agent attempts to move right
    public int movingRight(int currentState) {

        if (0 != currentState % getColumns()) { // while not in the last column
            currentState += 1; // moving right one column
            // System.out.println("Moved right");
        } else {
            // System.out.println("Failed to move right");
        }
        return currentState;
    }

}