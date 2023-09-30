/* CSC-207-01 Spring 2023
 * Names: Connor Durkin, Timur Kasimov
 * AI Lab 2
 * Acknowledgements: previous labs, textbook, prof. eliott
 */

// Creates Agent class to navigate the grid/environment. 
// Agents store an ID, currentState, the number of steps they took to reach finalState, and their strategy Distribution

import java.util.*; //for Random

public class Agent {

    // instance fields
    private static int nextID = 1;
    private static Random rand = new Random(System.nanoTime());

    private int agentID;
    private int currentState;
    private int numberOfSteps;

    private int[] strategyDistribution;

    // constructor

    Agent(int state, int up, int down, int left, int right, int still) { // strategies must add up to 100

        this.agentID = nextID;
        nextID++;
        this.currentState = state;
        this.numberOfSteps = 0;

        strategyDistribution = new int[5]; // used later for checking what direction the agent is going to move
        strategyDistribution[0] = up;
        strategyDistribution[1] = up + down;
        strategyDistribution[2] = up + down + left;
        strategyDistribution[3] = 100 - still;
        strategyDistribution[4] = 100;
    }

    // methods
    public int getAgentID() {
        return agentID;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int newState) {
        this.currentState = newState;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    // process an agent's move
    public int potentialMove(Environment environment) {
        int number = rand.nextInt(100) + 1; // random integer between 1 and 100 (both inclusive) that defines agent's
                                            // strategy

        this.numberOfSteps++; // increment the number of steps
        return environment.nextState(getCurrentState(), number, strategyDistribution); // get the environment to process
                                                                                       // the move

        // TESTING OUTPUT BELOW
        // System.out.println("Agent ID: " + getID());
        // System.out.println("Current State: " + getCurrentState());
        // System.out.println("Number of Steps: " + getNumberOfSteps());
        // System.out.println();
    }

    // resets the agent in the array to be used in another test
    public static void resetAgents(Agent[] agentsArray, int state) {
        for (Agent a : agentsArray) {
            a.currentState = state;
            a.numberOfSteps = 0;
        }
    }

}