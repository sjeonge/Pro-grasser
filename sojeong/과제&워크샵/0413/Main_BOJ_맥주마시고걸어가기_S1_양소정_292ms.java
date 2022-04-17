import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_맥주마시고걸어가기_S1_양소정_292ms {
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T =Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int N =Integer.parseInt(br.readLine());//편의점 수 
			int[][] dist = new int[N + 2][N + 2];
			int[][] ch = new int[N + 2][N + 2];
			List<int[]> point = new ArrayList<>();
			for (int j = 0; j < N+2; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				point.add(new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
				
			}
			for (int j = 0; j < N+2; j++) {
				for (int j2 = 0; j2 < N+2; j2++) {
					int[] p1 = point.get(j),p2 = point.get(j2);
					dist[j][j2] = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
					if (dist[j][j2] <= 50 * 20)
	                       ch[j][j2] = 1;
					
				}
			}
			for (int j = 0; j < N+2; j++) {
				for (int j2 = 0; j2 < N+2; j2++) {
					for (int k = 0; k < N+2; k++) {
						if(ch[j2][j]==1 &ch[j][k]==1) {
							ch[j2][k]=1;
						}
					}
				}
			}
			System.out.println(ch[0][N+1]==1?"happy":"sad");
			
		}
	}
}
