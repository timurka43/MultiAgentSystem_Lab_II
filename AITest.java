/* CSC-207-01 Spring 2023
 * Names: Connor Durkin, Timur Kasimov
 * AI Lab 2
 * Acknowledgements: previous labs, textbook, prof. Eliott
 */

/* Purpose: Creates 2 agents that navigate a grid-like environment with doors. 
 * Agents start in their respective initial states and must navigate to the finalState of the environment
 * 
 * Grid/Environment: Generated based on rows, columns
 * 
 * Movement: Based on the environment, agents have different probabilities for movement that are based on their strategies.
 * Agents can move up, down, left, right. They cannot move outside the bounds of the grid.
 * 
 * Returns the average number and optimal number of steps these agents take in the environment to reach final state.
 * Returns the win counter for each agent as well as for how many times both agents won together
 */

public class AITest {
    public static void main(String[] args) {

        // create an array of agents
        Agent[] agentsArrayA = new Agent[1000]; // allocating array for 1000 agents A
        Agent[] agentsArrayB = new Agent[1000]; // allocating array for 1000 agents B

        // initialize 2 distinct environments
        Environment environment1 = new Environment(3, 3);
        Environment environment2 = new Environment(3, 3);

        // put environments in an array
        Environment[] environmentArray = { environment1, environment2 };

        Agent agentA; // variables for iterations through arrays of agents
        Agent agentB;

        int moveOfA; // to record potential moves of each agent before updating currentState
        int moveOfB;

        int winCounterA = 0; // to keep track of victories by each of both agents
        int winCounterB = 0;
        int winCounterAB = 0;

        // for-each loop to run and print the tests of each environment
        for (Environment e : environmentArray) {
            System.out.println(
                    "Test " + e.getEnvironmentID());

            // assigning strategies
            if (e.getEnvironmentID() == 1) { // strategy distributions for test 1
                for (int i = 0; i < 1000; i++) { // creating 1000 agents of each A and B
                    agentsArrayA[i] = new Agent(7, 25, 25, 25, 25, 0); // 7 is the initial state of Agents A
                    agentsArrayB[i] = new Agent(9, 25, 25, 25, 25, 0); // 9 is the initial state of Agents B
                }
            } else { // strategy distributions for test 2
                for (int i = 0; i < 1000; i++) { // creating 1000 agents of each A and B
                    agentsArrayA[i] = new Agent(7, 40, 20, 20, 20, 0); // 7 is the initial state of Agents A
                    agentsArrayB[i] = new Agent(9, 30, 20, 30, 20, 0); // 9 is the initial state of Agents B
                }
            }

            for (int i = 0; i < 1000; i++) { // index i to iterate through agents A and B

                agentA = agentsArrayA[i];
                agentB = agentsArrayB[i];

                // while both agents are not in environment's final state...
                while (agentA.getCurrentState() != e.getFinalState() && agentB.getCurrentState() != e.getFinalState()) {

                    moveOfA = agentA.potentialMove(e); // ... play random action and record the potential move for each
                                                       // agent
                    moveOfB = agentB.potentialMove(e);
                    if ((moveOfA != moveOfB) || (moveOfA == e.getFinalState() && moveOfB == e.getFinalState())) {
                        // if moveOfA == moveOfB, don't move unless both are finalState
                        agentA.setCurrentState(moveOfA);
                        agentB.setCurrentState(moveOfB);
                    }
                }

                // keep track of who's winning
                if (agentA.getCurrentState() == e.getFinalState()) {
                    winCounterA++;
                }
                if (agentB.getCurrentState() == e.getFinalState()) {
                    winCounterB++;
                }
                if (agentA.getCurrentState() == e.getFinalState() && agentB.getCurrentState() == e.getFinalState()) {
                    winCounterAB++;
                }

            }

            // calculating and printing average number of steps for at least one agent to
            // reach final state:
            double averageSteps = 0.0;

            for (Agent a : agentsArrayA) {
                averageSteps += a.getNumberOfSteps(); // add together steps of each agent
            }

            averageSteps /= agentsArrayA.length; // divide by the number of agents to get the average #steps
            System.out.println("Average number of steps taken for at least one of the agents to reach the finalState? "
                    + (int) averageSteps);

            // calculating and printing minimal number of steps taken to reach the final
            // state from the initial state:
            int minimalSteps = agentsArrayA[0].getNumberOfSteps();
            for (Agent a : agentsArrayA) {
                if (minimalSteps > a.getNumberOfSteps()) {
                    minimalSteps = a.getNumberOfSteps();
                }
            }
            System.out.println(
                    "Minimal number of steps taken to reach the final state: " + minimalSteps);

            // printing how many times each agent and both agents won the game
            System.out.println("Both agents won " + winCounterAB + " times.");
            System.out.println("Agent A won " + winCounterA + " times.");
            System.out.println("Agent B won " + winCounterB + " times.");

            System.out.println(); // extra line for readibility

            // reset agents
            Agent.resetAgents(agentsArrayA, 7);
            Agent.resetAgents(agentsArrayB, 9);
            // reset win counters
            winCounterA = 0;
            winCounterB = 0;
            winCounterAB = 0;

        } // end for-each loop for each environment
    }
}