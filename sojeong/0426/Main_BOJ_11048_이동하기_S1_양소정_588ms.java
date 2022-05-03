package dp0426;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_11048_이동하기_S1_양소정_588ms {
	private static int []dx= {1,0,1};
	private static int []dy= {0,1,1};
	private static int N;
	private static int M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int [][]map = new int[N][M];
		int [][]memo = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				memo[i][j]=-1;
			}
		}
		
		bfs(map,memo);
		System.out.println(memo[N-1][M-1]);
	
	}

	private static void bfs(int[][] map, int[][]memo) {
		
		Queue<int[]> q =new ArrayDeque<int[]>();
		q.add(new int[]{0,0});
		memo[0][0]=map[0][0];
		while (!q.isEmpty()){
			int[] cur = q.poll();
			for (int i = 0; i < 3; i++) {
				int xx = cur[0]+dx[i];
				int yy = cur[1]+dy[i];
				if(xx<0 || xx>=N || yy<0 || yy>=M) continue;
				if(memo[xx][yy]==-1||map[xx][yy]+memo[cur[0]][cur[1]]>memo[xx][yy]) {
					memo[xx][yy]=map[xx][yy]+memo[cur[0]][cur[1]];
					q.add(new int[] {xx,yy});
				}
			}
		}
		
		
	
	}
}
