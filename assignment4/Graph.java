package project4;

import java.util.ArrayList;

public class Graph implements ConnectedGraphFunctions {
	private final ArrayList<Integer> vertices;
	private final ArrayList<Edge> edges;
	private final boolean isDirected;
	private final ArrayList<Edge> revEdges;
	
	public Graph(boolean directed) {
		isDirected = directed;
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
		revEdges = new ArrayList<>();
		
	}
	
	public Graph() {
		this(false);
	}
	

	public int getNumberOfVertices() {
		
		return vertices.size();
	}

	
	public int getNumberOfEdges() {
		
		return edges.size();
	}

	
	public boolean isDirected() {
		
		return isDirected;
	}
	
	public String toString() {
		String returnString;
		// check concatenation later
		String tempString = "G = (V, E)\nV = {" + vertices.get(0);
		StringBuilder sb = new StringBuilder(tempString);
		// add list of vertices with commas
		for (int i = 1; i < vertices.size(); i++) {
			sb.append(",");
			sb.append(i);
		}
	    System.out.println(edges.size());
		sb.append("}\nE = {" + edges.get(0).toString());
		// add list of edges with commas
		for (int j = 1; j < edges.size(); j++) {
			sb.append(",");
			sb.append(edges.get(j).toString());
		}
		sb.append("}");
		returnString = sb.toString();
		return returnString;
	}

	
	public void addVertex(int v) throws GraphException {
		if (vertices.contains(v)) {
			throw new GraphException("Vertex Already In List");
		} else {
			vertices.add(v);
		}
	}

	
	public void addEdge(int from, int to) throws GraphException {
		// check only once for both cases
		if (!(vertices.contains(from)) || 
				!(vertices.contains(to))) {
			throw new GraphException("Contains Vertex Not In List");
		}
		Integer fromInt = Integer.valueOf(from);
		Integer toInt = Integer.valueOf(to);


		for (int i = 0; i < edges.size(); i++) {
			Edge currentEdge = edges.get(i);
			Integer currentFrom = currentEdge.fromVertex();
			Integer currentTo = currentEdge.toVertex();

			// check for both multiple times
			if (currentFrom.equals(fromInt) && 
					currentTo.equals(toInt)) {
				throw new GraphException("Duplicate Vertex");
			}
			// check for only undirected multiple times
			// are current edge from and to reverse of parameter from and to
			if (!isDirected) {
				if (currentFrom.equals(toInt) && currentTo.equals(fromInt)) {
					throw new GraphException("Duplicate Vertex in Undirected"
							+ " Graph");
				}
			}
			
		}
		Edge newEdge = new Edge(from, to);
		edges.add(newEdge);
	}
		// FINISH ADDING DIRECTED PARTS 
	private boolean isConnected1(ArrayList<Edge> edges) {
		ArrayList<Integer> connectedSubsetList = new ArrayList<>();
		connectedSubsetList.add(vertices.get(0));
		boolean connected;
		// loop over all elements is connected list
		for (int i = 0; i < connectedSubsetList.size(); i++) {
			Integer vertexToExpand = connectedSubsetList.get(i);
			// loop over all edges
			for (Edge e: edges) {
				if (!isDirected) {
					if (vertexToExpand.equals(e.fromVertex()) && 
							!connectedSubsetList.contains(e.toVertex())) {
						connectedSubsetList.add(e.toVertex());
					}
					if (vertexToExpand.equals(e.toVertex()) && 
							!connectedSubsetList.contains(e.fromVertex())) {
						connectedSubsetList.add(e.fromVertex());
					}
				}
				if (isDirected) {
					if (vertexToExpand.equals(e.fromVertex()) && 
							!connectedSubsetList.contains(e.toVertex())) {
						connectedSubsetList.add(e.toVertex());
					}
				}
			}
		}
		// check if sizes are equal, storing result in return value
		if (connectedSubsetList.size() == vertices.size()) {
		    connected = true;
		} else {
			connected = false;
		}
		return connected;
	}
	
	public boolean isConnected() {
		
		boolean connected = false;
		if (!isDirected) {
			connected = this.isConnected1(this.edges);
		}
		else if (isDirected) {
			for (Edge e: edges) {
				Edge revEdge = new Edge(e.fromVertex(), e.toVertex());
				revEdges.add(revEdge);
			}
			if ((this.isConnected1(edges)) == true && 
					(this.isConnected1(revEdges)) == true) {
				connected = true;
			} 
			
		}
		return connected;
	}
}
