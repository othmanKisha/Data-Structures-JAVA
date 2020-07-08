public class Graph {
	
	private int adjMatrix[][];
	private int numVertices;
	private int[] visited;
	private int[] parent;

	public Graph(int numVertices) {
		this.numVertices = numVertices;
		adjMatrix = new int[numVertices][numVertices];
		parent = new int[numVertices];
	}

	public void addEdge(int i, int j) {
		adjMatrix[i][j] = 1;
		adjMatrix[j][i] = 1;
	}

	public void addDirectedEdge(int i, int j) {
		adjMatrix[i][j] = 1;
	}

	public void addWeightedEdge(int i, int j, int weight) {
		adjMatrix[i][j] = weight;
		adjMatrix[j][i] = weight;
	}

	public void setAdjMatrix(int[][] adjm) {
		for (int i = 0; i < adjm.length; i++)
			for (int j = 0; j < adjm[i].length; j++)
				adjMatrix[i][j] = adjm[i][j];
	}

	public void setDiffAdjMatrix(int[][] adjm, int numEdges) {
		adjMatrix = new int[numVertices][numEdges];
		for (int i = 0; i < adjm.length; i++)
			for (int j = 0; j < adjm[i].length; j++)
				adjMatrix[i][j] = adjm[i][j];
	}

	public int[] topologicalSort() {
		boolean[] visit = new boolean[numVertices];
		int[] order = new int[numVertices];
		int index = numVertices - 1;
		for (int u = 0; u < numVertices; u++)
			if (!visit[u])
				index = visit(visit, order, index, u);
		return order;
	}

	private int visit(boolean[] visit, int[] order, int index, int u) {
		if (visit[u])
			return index;
		visit[u] = true;
		for (int v = 0; v < adjMatrix.length; v++)
			if (adjMatrix[u][v] != 0 && !visit[v])
				index = visit(visit, order, index, v);
		order[index--] = u;
		return index;
	}

	public Queue<Integer> BFS() {
		visited = new int[numVertices];
		Queue<Integer> queue = new Queue<>();
		BFS(0, visited, queue);
		return queue;
	}

	private void BFS(int i, int[] visited, Queue<Integer> queue) {
		int j, element;
		visited[i] = 1;
		queue.enqueue(i);
		while (!queue.isEmpty()) {
			element = queue.firstEl();
			queue.dequeue();
			j = element;
			while (j < numVertices) {
				if (adjMatrix[element][j] == 1 && visited[j] == 0) {
					queue.enqueue(j);
					visited[j] = 1;
				}
				j++;
			}
		}
	}

	public LinkedList<Integer> DFS(int k) {
		visited = new int[numVertices];
		LinkedList<Integer> sorted = new LinkedList<>();
		DFS(0, visited, sorted, k);
		return sorted;
	}

	private void DFS(int i, int[] visited, LinkedList<Integer> sorted, int k) {
		if (visited[i] == 0 && i != k) {
			visited[i] = 1;
			sorted.addToTail(i);
			for (int j = 0; j < adjMatrix[i].length; j++)
				if (adjMatrix[i][j] == 1 && visited[j] == 0 && j != k)
					DFS(j, visited, sorted, k);
		}
	}

	public int isConnected() {
		Queue<Integer> queue = BFS();
		for (int v = 0; v < numVertices; v++)
			if (visited[v] != 1)
				return 0;
		return 1;
	}

	public int dijkstraShortestPath(int i, int k) {
		boolean[] spt = new boolean[numVertices];
		for (int j = 0; j < numVertices; j++)
			spt[j] = false;
		int[] distance = new int[numVertices];
		int INFINITY = Integer.MAX_VALUE;
		for (int j = 0; j < numVertices; j++)
			distance[j] = INFINITY;
		distance[i] = 0;
		for (int j = 0; j < numVertices; j++) {
			int u = getMinimumVertex(spt, distance);
			spt[u] = true;
			for (int v = 0; v < numVertices; v++)
				if (adjMatrix[u][v] > 0) {
					if (spt[v] == false && adjMatrix[u][v] != INFINITY) {
						int key = adjMatrix[u][v] + distance[u];
						if (key < distance[v])
							distance[v] = key;
					}
				}
		}
		return distance[k];
	}

	private int getMinimumVertex(boolean[] mst, int[] key) {
		int minKey = Integer.MAX_VALUE;
		int v = -1;
		for (int i = 0; i < numVertices; i++)
			if (!mst[i] && minKey >= key[i]) {
				minKey = key[i];
				v = i;
			}
		return v;
	}

	public int KruskalMaximumST(int totcost) {
		int maxcost = 0, edge_count = 0;
		for (int i = 0; i < numVertices; i++)
			parent[i] = i;
		while (edge_count < numVertices - 1) {
			int max = Integer.MIN_VALUE, a = -1, b = -1;
			for (int i = 0; i < numVertices; i++)
				for (int j = 0; j < numVertices; j++)
					if (find(i) != find(j) && adjMatrix[i][j] > max) {
						max = adjMatrix[i][j];
						a = i;
						b = j;
					}
			union(a, b);
			edge_count++;
			maxcost += max;
		}
		return totcost - maxcost;
	}

	private int find(int i) {
		while (parent[i] != i)
			i = parent[i];
		return i;
	}

	private void union(int i, int j) {
		int a = find(i);
		int b = find(j);
		parent[a] = b;
	}

	public int floodFill(int r, int c) {
		if (r < 0 || c < 0 || r >= adjMatrix.length || c >= adjMatrix[adjMatrix.length - 1].length)
			return 0;
		if (adjMatrix[r][c] != 1)
			return 0;
		adjMatrix[r][c] = 2;
		return 1 + floodFill(r - 1, c - 1) + floodFill(r - 1, c) + floodFill(r, c - 1) + floodFill(r + 1, c + 1)
				+ floodFill(r, c + 1) + floodFill(r + 1, c) + floodFill(r - 1, c + 1) + floodFill(r + 1, c - 1);
	}
}
