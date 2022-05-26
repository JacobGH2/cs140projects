package assignment6;

public class VertexWithWeight {
	
	private final Integer vertex;
	private double weight;
	
	public VertexWithWeight(int v, double w) {
		vertex = v;
		weight = w;
	}
	
	public Integer getVertex() {
		return vertex;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double nw) {
		weight = nw;
	}
	
	public String toString() {
		return "(" + vertex + "," + weight + ")";
	}
	
	public int hashCode() {
		return vertex.intValue();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (getClass() == o.getClass())  {
			VertexWithWeight other = (VertexWithWeight)o;
			if (vertex.equals(other.vertex)) {
				return true;
			}
		}
		return false;
	}
}
