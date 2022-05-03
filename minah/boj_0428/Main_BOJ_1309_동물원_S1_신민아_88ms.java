package boj_0428;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// idea : 현재 마지막으로 사자를 넣으려는 칸의 개수는 바로 이전 칸이 비어있는지 아닌지에 따라 달라지므로 따로 관리
// 식 : f(n) = f(n-1) + f(n-1).filled + f(n-1).notFilled * 2
public class Main_BOJ_1309_동물원_S1_신민아_88ms {
	static class DP {
		int lastFilled; // 바로 이전 칸이 차있는 경우의 수
		int notLastFilled; // 바로 이전 칸이 차있지 않은 경우의 수
		int sum; // 둘의 합
		
		public DP(int lastFilled, int notLastFilled, int sum) {
			this.lastFilled = lastFilled;
			this.notLastFilled = notLastFilled;
			this.sum = sum;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int mod = 9901; // 나누는 수
		int answer = Integer.parseInt(br.readLine());
		DP[] dp = new DP[answer + 1];
		
		dp[1] = new DP(2, 1, 3); // basis
		
		for(int i=2;i<dp.length;i++) {
			
			// 현재 칸에 사자를 채우기 위해서는 바로 이전 칸에 사자가 있으면 1칸만 넣을 수 있으므로 이전 칸 중 끝까지 차있는 칸을 그대로 더해주고 이전 칸에 사자가 없으면 양 옆에 채울 수 있으므로 * 2를 해서 더해줌
			int filled = (dp[i-1].lastFilled + (dp[i-1].notLastFilled * 2)) % mod;
			
			int notFilled = dp[i-1].sum % mod; // 현재 사자를 넣으려는 칸 중 비어있는 칸의 개수는 f(n-1)을 그대로 갖다 쓰고 칸만 추가하므로 그대로 넣어줌
			
			dp[i] = new DP(filled, notFilled, (filled + notFilled) % mod);
		}
		
		System.out.print(dp[answer].sum);
	}

}
