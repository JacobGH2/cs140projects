package assignment6;

public record EdgeWithWeight(int fromVertex, int toVertex, double weight) {
	public String toString() {
		return "(" + fromVertex + "," + toVertex + "," + weight + ")";
	}
}
