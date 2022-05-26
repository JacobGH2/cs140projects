package assignment6;
interface WeightedGraphFunctions {
	boolean hasPath(int fromVertex, int toVertex);
	double getMinimumWeight(int fromVertex, int toVertex);
	EdgeWithWeight[] getPath(int fromVertex, int toVertex);
	boolean addVertex(int v);
	boolean addWeightedEdge(int from, int to, double weight);
	String toString();
}
