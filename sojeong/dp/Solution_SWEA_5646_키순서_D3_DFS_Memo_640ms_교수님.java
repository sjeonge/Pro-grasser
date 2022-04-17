
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_5646_키순서_D3_DFS_Memo_640ms{
	private static int N;
	private static int[][] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <=T; tc++) {
			N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			//인접행렬 : 0으로 자동 초기화(0: 키 관계를 모름, 1:자기보다 키가 큰 학생과의 관계를 표현)
			adj = new int[N+1][N+1]; //학생번호 1번부터 시작하도록..
			
			for (int i = 1; i <= N; i++) adj[i][0] = -1 ;//탐색 전을 나타내는 의미로 초기화
			StringTokenizer st = null;
			int a,b;
			for (int i = 0; i < M; i++) { //간선 정보 입력방아 인접행렬에 표현
				st = new StringTokenizer(br.readLine()," "); // a b :a보다 b가 키가 크다
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				adj[a][b] = 1;
			}
			for (int i = 1; i <= N; i++) {
				//탐색 전인 학생들만 탐색
				if(adj[i][0]==-1) gtDFS(i);
			}
			//자신보다 작은 학생수 카운트
			for (int j = 1; j <= N; j++) {
				for (int i = 1; i <= N; i++) {
					adj[0][j] += adj[i][j];
				}
			}
			int ans = 0; //자신의 키를 알 수 있는 학생 수 
			for (int i = 1; i <= N; i++) {
				if(adj[i][0]+adj[0][i] == N-1) ++ans;
			}
			
			System.out.println("#"+tc+" "+ans);
		}
	}

	private static void gtDFS(int cur) {
		for (int i = 1; i <=N; i++) {
			if(adj[cur][i] != 0) { //나보다 큰 학생이면
				if(adj[i][0] == -1) gtDFS(i); //탐색 전이면 탐색하러 가기
				
				if(adj[i][0] > 0) { //i보다 큰 학생이 있다면
					//나보다 큰 학생이 알고있는 다른 학생관의 키 관계를 나와의 직접 관계로 매핑
					//cur < i < j  ==> cur < j
					for (int j = 1; j <= N; j++) {
						if(adj[i][j] == 1) {
							adj[cur][j] = 1;
						}
					}	
				}
			}
		}
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			cnt += adj[cur][i];
		}
		adj[cur][0]= cnt;
		
	}

}
