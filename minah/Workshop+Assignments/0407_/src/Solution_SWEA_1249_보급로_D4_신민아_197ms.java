import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution_SWEA_1249_보급로_D4_신민아_197ms {
	static class Node{
		int r, c, weight;

		public Node(int r, int c, int weight) {
			this.r = r;
			this.c = c;
			this.weight = weight;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine()) + 1;
		
		StringBuilder sb = new StringBuilder();
		int[][] map;
		
		for(int t=1;t<TC;t++) {
			int size = Integer.parseInt(br.readLine());
			map = new int[size][size];
			
			for(int i=0;i<size;i++) {
				String line = br.readLine();
				for(int j=0;j<size;j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			
			sb.append("#").append(t).append(" ").append(searchByBFS(map)).append("\n");
		}
		
		System.out.print(sb.toString());
		
	}
	
	private static int searchByBFS(int[][] map) {
		PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> o1.weight - o2.weight);
		boolean[][] visited = new boolean[map.length][map.length];
		
		int[] dr = {1, 0, -1, 0};
		int[] dc = {0, 1, 0, -1};
		
		queue.offer(new Node(0, 0, 0));
		visited[0][0] = true;
		int arrival = map.length - 1;
		int result = Integer.MAX_VALUE;
		
		while(queue.size() > 0) {
			Node cur = queue.poll();
			if(cur.r == arrival && cur.c == arrival) {
				result = cur.weight;
				break;
			}
			
			for(int d=0;d<4;d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(isNotAvailable(nr, nc, visited, arrival)) continue;
				
				queue.offer(new Node(nr, nc, cur.weight + map[nr][nc]));
				visited[nr][nc] = true;
			}
		}
		
		return result;
	}
	
	private static boolean isNotAvailable(int nr, int nc, boolean[][] visited, int size) {
		return ((nr < 0) || (nr > size) || (nc < 0) || (nc > size) || visited[nr][nc]);
	}

}
