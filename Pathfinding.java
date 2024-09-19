/**
 *QS : On a euclidean plane there are some obstacle and find the minimum distance between x to y.
 */


import java.util.*;
// Node class representing a point in the grid
class Node implements Comparable<Node> {
    int x, y;  // Coordinates of the node
    double g, h;  // g: cost from start, h: heuristic (estimated cost to goal)
    Node parent;  // Parent node to track the path

    public Node(int x, int y, double g, double h, Node parent) {
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
        this.parent = parent;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.g + this.h, other.g + other.h);
    }
}

public class Pathfinding {
    private static final int[] ROW_DIRECTIONS = {-1, 1, 0, 0};
    private static final int[] COL_DIRECTIONS = {0, 0, -1, 1};

    // A* search algorithm to find the shortest path
    public static List<Node> aStarSearch(int[][] grid, int[] start, int[] goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<String> closedSet = new HashSet<>();
        int rows = grid.length, cols = grid[0].length;

        // Add the start node to the priority queue
        openSet.add(new Node(start[0], start[1], 0, heuristic(start[0], start[1], goal[0], goal[1]), null));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            // Check if the goal is reached
            if (current.x == goal[0] && current.y == goal[1]) {
                return reconstructPath(current);
            }

            closedSet.add(current.x + "," + current.y);

            // Explore neighbors
            for (int i = 0; i < 4; i++) {
                int newX = current.x + ROW_DIRECTIONS[i];
                int newY = current.y + COL_DIRECTIONS[i];

                if (isValid(newX, newY, grid) && !closedSet.contains(newX + "," + newY)) {
                    double newG = current.g + 1; // Distance from start to neighbor is 1
                    double newH = heuristic(newX, newY, goal[0], goal[1]);

                    openSet.add(new Node(newX, newY, newG, newH, current));
                }
            }
        }

        // If no path is found
        return null;
    }

    // Heuristic function: Euclidean distance
    private static double heuristic(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    // Check if the cell is within bounds and is not an obstacle
    private static boolean isValid(int x, int y, int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 0;
    }

    // Reconstruct the path by backtracking from the goal node to the start
    private static List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    // Print the path from start to goal
    public static void printPath(List<Node> path) {
        if (path == null) {
            System.out.println("No path found!");
            return;
        }
        for (Node node : path) {
            System.out.println("[" + node.x + "," + node.y + "]");
        }
    }

    public static void main(String[] args) {
        // 0 represents passable cells, 1 represents obstacles
        int[][] grid = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0}
        };

        int[] start = {0, 0};  // Starting point (x)
        int[] goal = {4, 4};   // Goal point (y)

        List<Node> path = aStarSearch(grid, start, goal);
        printPath(path);
    }
}
