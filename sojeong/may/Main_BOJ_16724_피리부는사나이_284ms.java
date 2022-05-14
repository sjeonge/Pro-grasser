
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_16724_피리부는사나이_284ms{
	private static boolean[][] v;
	private static boolean[][] cycle;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int map[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				int c = str.charAt(j);
				if(c == 'U') map[i][j] = 0;
				else if(c == 'D') map[i][j] = 1;
				else if(c == 'L') map[i][j] = 2;
				else if(c == 'R') map[i][j] = 3;
			}
		}
		
		v= new boolean[N][M];
		cycle = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(!v[i][j]) dfs(i,j,map);
			}
		}
		System.out.println(cnt);
		
	}
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	private static int cnt;

	private static void dfs(int x, int y, int[][]map) {
		v[x][y] = true;
		int xx = x + dx[map[x][y]];
		int yy = y + dy[map[x][y]];
		
		if(!v[xx][yy]) {
			dfs(xx,yy,map);
		}else {
			
			if(!cycle[xx][yy]) cnt++;
		}
		
		cycle[x][y] = true;
		
	}
}
