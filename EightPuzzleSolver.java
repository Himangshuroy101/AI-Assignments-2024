
/**   
 * QS: Solving 8-Puzzle using Heuristic search algo / A* Algorithm.
 */


import java.util.*;

class PuzzleNode implements Comparable<PuzzleNode> {
    int[] state;
    int g; // Cost to reach this state
    int h; // Heuristic value (Manhattan distance)
    PuzzleNode parent; // Parent node

    public PuzzleNode(int[] state, int g, int h, PuzzleNode parent) {
        this.state = state;
        this.g = g;
        this.h = h;
        this.parent = parent;
    }

    // Compare nodes based on the f(n) = g(n) + h(n) value
    @Override
    public int compareTo(PuzzleNode other) {
        return (this.g + this.h) - (other.g + other.h);
    }
}

public class EightPuzzleSolver {

    // Goal state
    private static final int[] GOAL_STATE = {1, 2, 3, 4, 5, 6, 7, 8, 0};

    // Find the index of the blank tile (0)
    private static int findBlank(int[] state) {
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    // Calculate Manhattan distance heuristic
    private static int manhattanDistance(int[] state) {
        int distance = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] != 0) {
                int value = state[i];
                int goalRow = (value - 1) / 3;
                int goalCol = (value - 1) % 3;
                int currRow = i / 3;
                int currCol = i % 3;
                distance += Math.abs(currRow - goalRow) + Math.abs(currCol - goalCol);
            }
        }
        return distance;
    }

    // Generate possible neighbors by sliding the blank tile
    private static List<int[]> getNeighbors(int[] state) {
        List<int[]> neighbors = new ArrayList<>();
        int blankIndex = findBlank(state);
        int row = blankIndex / 3;
        int col = blankIndex % 3;

        // Possible moves (up, down, left, right)
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] move : moves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                int newIndex = newRow * 3 + newCol;
                int[] newState = state.clone();
                newState[blankIndex] = newState[newIndex];
                newState[newIndex] = 0;
                neighbors.add(newState);
            }
        }
        return neighbors;
    }

    // Check if the puzzle is solved
    private static boolean isGoalState(int[] state) {
        return Arrays.equals(state, GOAL_STATE);
    }

    // Solve the puzzle using A* algorithm
    public static List<int[]> aStar(int[] startState) {
        PriorityQueue<PuzzleNode> openSet = new PriorityQueue<>();
        Set<String> closedSet = new HashSet<>();
        
        // Add the starting state to the priority queue
        openSet.add(new PuzzleNode(startState, 0, manhattanDistance(startState), null));

        while (!openSet.isEmpty()) {
            PuzzleNode currentNode = openSet.poll();

            // If the goal state is reached, construct the solution path
            if (isGoalState(currentNode.state)) {
                List<int[]> path = new ArrayList<>();
                while (currentNode != null) {
                    path.add(currentNode.state);
                    currentNode = currentNode.parent;
                }
                Collections.reverse(path);
                return path;
            }

            closedSet.add(Arrays.toString(currentNode.state));

            // Generate neighbors and explore them
            for (int[] neighborState : getNeighbors(currentNode.state)) {
                if (!closedSet.contains(Arrays.toString(neighborState))) {
                    int g = currentNode.g + 1;
                    int h = manhattanDistance(neighborState);
                    openSet.add(new PuzzleNode(neighborState, g, h, currentNode));
                }
            }
        }

        return null; // If no solution is found
    }

    // Helper method to print the puzzle state
    private static void printPuzzle(int[] state) {
        for (int i = 0; i < state.length; i++) {
            if (i % 3 == 0) {
                System.out.println();
            }
            System.out.print(state[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Example start state
        int[] startState = {1, 2, 3, 4, 0, 5, 6, 7, 8}; // Initial configuration

        // Solve the puzzle
        List<int[]> solution = aStar(startState);

        // Print the solution
        if (solution != null) {
            System.out.println("Solution found in " + (solution.size() - 1) + " moves.");
            for (int[] state : solution) {
                printPuzzle(state);
                System.out.println();
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}
