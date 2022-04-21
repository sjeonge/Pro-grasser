
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_17070_파이프옮기기1_G5_양소정_272ms{
	private static int N;
	private static int ans;		
	private static int [][]ch= {{1,1,0},{1,1,1},{0,1,1}};	// 가로,대각선,세로 가능여부 (1:가능 , 0:불가)
														
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		char [][] map =new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0,k=0; j < N; j++,k+=2) {
				map[i][j] = str.charAt(k);
			}
		}
		System.out.println(dfs(0,1,0,map));
	}

	private static int dfs(int x, int y,int d,char[][] map) {
		if(x == N-1 && y == N-1) {
			ans++;
		}

		for (int i = 0; i < 3; i++) {
			if(ch[d][i]==0) continue; 
			
			if(i==0) {//가로
				if(y+1<N && map[x][y+1]=='0')
					dfs(x,y+1,0,map);
				
			}else if(i==1) {//대각선
				if (y+1<N && x+1< N && map[x][y+1] == '0' && map[x+1][y] == '0' && map[x + 1][y + 1] == '0') 
		            dfs(x+1, y+1,1,map);
				
			}else {//세로
				if(x+1<N && map[x+1][y]=='0')
					dfs(x+1,y,2,map);
			}	
		}
		return ans;
	}

}
