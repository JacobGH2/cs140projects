package assignment6;

import java.util.Comparator;

public class VWWComparator implements Comparator<VertexWithWeight>{

	@Override
	public int compare(VertexWithWeight o1, VertexWithWeight o2) {
		int doubleComp = Double.compare(o1.getWeight(), o2.getWeight());
		if (doubleComp != 0) return doubleComp;
		return o1.getVertex() - o2.getVertex();
	}
	
	
	
}



