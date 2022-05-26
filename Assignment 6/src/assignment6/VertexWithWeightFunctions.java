package assignment6;
interface VertexWithWeightFunctions {
	double getWeight();
	int getVertex();
	void setWeight(double w);
	boolean equals(Object o);
	int hashCode();
	String toString();
}