package assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class WeightedGraph implements WeightedGraphFunctions{
	ArrayList<Integer> vertices = new ArrayList<>();
	ArrayList<EdgeWithWeight> edges = new ArrayList<>();
	private enum Solution {HAS_PATH, MIN_WEIGHT, GET_PATH};
	
	@Override
	public boolean hasPath(int fromVertex, int toVertex) {
		Object obj = dijkstra(fromVertex, toVertex, Solution.HAS_PATH);
		if (obj != null) return (boolean)obj;
		return false;
	}
	@Override
	public double getMinimumWeight(int fromVertex, int toVertex) {
		Object obj = dijkstra(fromVertex, toVertex, Solution.MIN_WEIGHT);
		if (obj != null) return (double)obj;
		return Double.NaN;
	}
	@Override
	public EdgeWithWeight[] getPath(int fromVertex, int toVertex) {
		Object obj = dijkstra(fromVertex, toVertex, Solution.GET_PATH);
		if (obj != null) return (EdgeWithWeight[])obj;
		return null;
	}
	@Override
	public boolean addVertex(int v) {
		if (!vertices.contains(v)) {
			vertices.add(v);
			return true;
		} 
		return false;
	}
	@Override
	public boolean addWeightedEdge(int from, int to, double weight) {
		if (vertices.contains(from) && vertices.contains(to)) {
			for (EdgeWithWeight ed : edges) {
				if (ed.fromVertex() == from && ed.toVertex() == to) {
					return false;
				}
				
			}
			EdgeWithWeight newEdge = new EdgeWithWeight(from, to, weight);
			edges.add(newEdge);
			return true;
		}
		return false;
	}
	
	public String toString() {
		String returnString;
		
		String tempString = "G = (V, E)\nV = {" + vertices.get(0);
		StringBuilder sb = new StringBuilder(tempString);
		// add list of vertices with commas
		for (int i = 2; i <= vertices.size(); i++) {
			sb.append(",");
			sb.append(i);
		}
	    
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
	
	private Object dijkstra(int sourceVertex, int destVertex, Solution type) {
		Map<Integer, VertexWithWeight> vtxTOvtxWithWgt = new TreeMap<>();
		Comparator<VertexWithWeight> comp = new VWWComparator();
		PriorityQueue<VertexWithWeight> minPriorityQueueByWeight = 
				new PriorityQueue<>(vertices.size(), comp);
		Map<VertexWithWeight, Integer> parent = new TreeMap<>(comp);
		
		VertexWithWeight vtxWithWgt = new VertexWithWeight(sourceVertex, 0.0);
		vtxTOvtxWithWgt.put(sourceVertex, vtxWithWgt);
		parent.put(vtxWithWgt, sourceVertex);
		minPriorityQueueByWeight.add(vtxWithWgt);
		
		for(Integer vtx : vertices) {
			if (!vtx.equals(sourceVertex)) {
				vtxWithWgt = new VertexWithWeight(vtx, Double.POSITIVE_INFINITY);
				vtxTOvtxWithWgt.put(vtx, vtxWithWgt);
				parent.put(vtxWithWgt, -1);
				minPriorityQueueByWeight.add(vtxWithWgt);
			}
			
		}
		
		while (minPriorityQueueByWeight.size() > 0) {
			VertexWithWeight vtxWithWgtFrom = minPriorityQueueByWeight.poll();
			if (parent.get(vtxWithWgtFrom) == -1) break;
			if (vtxWithWgtFrom.getVertex() == destVertex) break;
			for (EdgeWithWeight curEdge : edges) {
				if (vtxWithWgtFrom.getVertex() == curEdge.fromVertex()) {
					Integer u = curEdge.toVertex();
					vtxWithWgt = vtxTOvtxWithWgt.get(u);
					double temp = vtxWithWgtFrom.getWeight() + curEdge.weight();
					if (temp < vtxWithWgt.getWeight()) {
						minPriorityQueueByWeight.remove(vtxWithWgt);
						parent.remove(vtxWithWgt);
						vtxWithWgt.setWeight(temp);
						minPriorityQueueByWeight.add(vtxWithWgt);
						parent.put(vtxWithWgt, vtxWithWgtFrom.getVertex());
					}
				}
			}
		}
		
		if (type == Solution.HAS_PATH) {
			return parent.get(vtxTOvtxWithWgt.get(destVertex)) >= 0;
		}
		if (type == Solution.MIN_WEIGHT) {
			if (parent.get(vtxTOvtxWithWgt.get(destVertex)) >= 0) {
				return vtxTOvtxWithWgt.get(destVertex).getWeight();
			}
		}
		if (type == Solution.GET_PATH) {
			if (parent.get(vtxTOvtxWithWgt.get(destVertex)) >= 0) {
				ArrayList<Integer> reversePath = new ArrayList<>();
				VertexWithWeight p = vtxTOvtxWithWgt.get(destVertex);
				while (p.getVertex() != sourceVertex) {
					reversePath.add(p.getVertex());
					p = vtxTOvtxWithWgt.get(parent.get(p));
				}
				reversePath.add(sourceVertex);
				Collections.reverse(reversePath);

				EdgeWithWeight[] array = new EdgeWithWeight[reversePath.size() - 1];
				for (int i = 0; i < reversePath.size() - 1; i++) {
					//.out.println(reversePath.get(i));
					array[i] = getEdgeWithWeight(reversePath.get(i), reversePath.get(i + 1));

				}
				return array;
				
			}
			
			
		}
		return null;
	}
	
	private EdgeWithWeight getEdgeWithWeight(int fromVertex, int toVertex) {
		for (var e: edges) {
			if (e.fromVertex() == fromVertex && e.toVertex() == toVertex) {
				return e;
			}
		}
		return null;
	}
	
}
