package dp0428;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//dp[i][0] = dp[i-1][0] + dp[i-1][1] +dp[i-1][2]; -> 이번 방에서는 넣지 않을 것이므로, 이전방에서 사자가 어디 방에 들어있든 상관 없음
//dp[i][1] = dp[i-1][0] + dp[i-1][2]; -> 이번방에는 왼쪽에 넣을거니까 이전방에는 오른쪽 혹은 없는 경우만 됨.
//dp[i][2] = dp[i-1][0] + dp[i-1][1]; -> 이번방에는 오른쪽에 넣을거니까 이전방에는 왼쪽 혹은 없는 경우만 됨. 


public class Main_BOJ_1309_동물원_S1양소정_100ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int [][]dp = new int[N+1][3];
	
		for (int i = 0; i <3; i++) {
			dp[1][i] = 1;
		}
		
		for(int i=2; i<=N; i++){
		dp[i][0] = (dp[i-1][0] + dp[i-1][1] +dp[i-1][2])%9901; ;
		dp[i][1] = (dp[i-1][0] + dp[i-1][2])%9901;
		dp[i][2] = (dp[i-1][0] + dp[i-1][1])%9901;
		}
		int sum = 0;
		for(int i =0; i<3; i++){
			sum+=dp[N][i];
		}
		System.out.println(sum%9901);
	}
}
