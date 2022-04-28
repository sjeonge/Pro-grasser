import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// idea : 간선 및 정점의 개수가 많으므로 result를 long으로 두기
public class Solution_SWEA_3214_최소스패닝트리_D4_신민아_1805ms {
	static class Edge implements Comparable<Edge> {
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		Edge[] edgeList;
		int[] parents;
		for(int t=1;t<=TC;t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int vertex = Integer.parseInt(st.nextToken());
			int edge = Integer.parseInt(st.nextToken());
			
			edgeList = new Edge[edge];
			parents = new int[vertex + 1];
			
			for(int i=1;i<parents.length;i++) {
				parents[i] = i;
			}
			
			for(int i=0;i<edge;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				edgeList[i] = new Edge(start, end, weight);
			}
			
			long result = 0;
			int count = 0;
			Arrays.sort(edgeList);
			
			for(Edge curEdge : edgeList) {
				if(union(parents, curEdge.start, curEdge.end)) {
					result += curEdge.weight;
					if(++count == vertex) break;
				}
			}
			
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	private static boolean union(int[] parents, int a, int b) {
		int aRoot = findRoot(parents, a);
		int bRoot = findRoot(parents, b);
		
		if(aRoot == bRoot) return false;
		
		if(aRoot < bRoot) {
			parents[bRoot] = aRoot;
		} else {
			parents[aRoot] = bRoot;
		}
		
		return true;
	}
	
	private static int findRoot(int[] parents, int a) {
		if(a == parents[a])
			return a;
		
		return parents[a] = findRoot(parents, parents[a]);
	}

}
