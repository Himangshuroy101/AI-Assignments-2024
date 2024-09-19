## How DFS and Cost-based search is related to each other.

Depth-First Search (DFS) and Cost-Based Search are both strategies used in artificial intelligence and computer science for navigating search spaces, but they differ significantly in how they explore nodes and assess the optimality of a path.

1. **Depth-First Search (DFS)**
    - Strategy: DFS explores as far as possible along each branch before backtracking. It uses a stack data - - - - structure (either explicitly or via recursion) to explore the nodes.
    - Mechanism: It picks a path and follows it to its deepest node (farthest from the root), and only when it      reaches a dead-end does it backtrack to explore other branches.
    - Efficiency: DFS does not take costs into account. It just explores based on depth and can find solutions quickly if they exist in deep levels. However, it may not find the optimal (least-cost) solution.
    - Application: DFS is often used when the goal is to explore all nodes or when solutions are deep in the search space.

2. **Cost-Based Search**
    - Strategy: Cost-based search takes into account the cost of traversing from one node to another and is designed to find the path with the least cost (optimal solution). Examples of cost-based searches include:
    Uniform-Cost Search: Expands the least-cost node, ensuring that the first solution found is the optimal one.
    - A* Search: Combines the cost to reach the node and an estimate of the cost to reach the goal (using a heuristic).
    - Mechanism: Nodes are selected based on the cumulative cost, often using a priority queue (or min-heap).
    - Efficiency: Cost-based searches are guaranteed to find the optimal solution if implemented correctly, but they can be computationally expensive because they explore more nodes compared to DFS.

3. **Relationship Between DFS and Cost-Based Search**
    - Exploration: DFS explores the depth of the tree without concern for cost, whereas cost-based searches expand nodes based on the lowest cumulative path cost.
    - Optimality: DFS is not guaranteed to find the optimal solution, while cost-based searches (like Uniform-Cost or A*) are explicitly designed to find the path with the lowest cost.
    - Memory Usage: DFS typically uses less memory since it only needs to store the current path, while cost-based searches need to store the frontier (nodes to be expanded), which can grow significantly depending on the search space.

In summary, while both are search techniques, DFS focuses on depth (ignoring costs), whereas cost-based searches focus on finding the least expensive path through the space.