package assignment6;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;



public class TestWeightedGraph {
	public static void main(String[] args) {
		if(args.length != 3) {
			System.out.println("format: testWeightedGraph \"input file\" \"from vertex\" \"to vertex\"");
			System.exit(0);
		}
		
		WeightedGraph g = new WeightedGraph();
		int fromVertex = Integer.parseInt(args[1]);
		int toVertex = Integer.parseInt(args[2]);
		
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
			String inn;
			
			// get rid of the first line
			inn = input.readLine();
			
			// get the list of vertices
			inn = input.readLine();
			inn = inn.substring(4);
			java.util.StringTokenizer st = new java.util.StringTokenizer(inn, "{},");
			while(st.hasMoreTokens()) {
				int newVertex = Integer.parseInt(st.nextToken());
				g.addVertex(newVertex);
			}
			
			// get the list of edges
			inn = input.readLine();
			inn = inn.substring(4);
			st = new java.util.StringTokenizer(inn, "{}");
			inn = st.nextToken();
			st = new java.util.StringTokenizer(inn, "(),");
			while(st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				double w = Double.parseDouble(st.nextToken());
				g.addWeightedEdge(from, to, w);
			}
			input.close();
		} catch(Exception e) {
			System.out.println(e.toString());
			System.exit(0);
		}
		
		boolean hasPathResult = g.hasPath(fromVertex, toVertex);
		System.out.println("hasPathResult = " + hasPathResult);
		double minimumWeightResult = g.getMinimumWeight(fromVertex, toVertex);
		System.out.println("minimumWeightResult = " + minimumWeightResult);
		EdgeWithWeight[] minimumWeightPathResult = g.getPath(fromVertex, toVertex);
		System.out.print("minimumWeightPathResult = ");
		
		if (hasPathResult) {
			for(int i = 0; i < minimumWeightPathResult.length; i++) {
				System.out.print(minimumWeightPathResult[i]);
				if(i < minimumWeightPathResult.length-1) {
					System.out.print(",");
				}
			}
		}
		
		System.out.println();
		
		System.out.println(g.toString());
	}
}
