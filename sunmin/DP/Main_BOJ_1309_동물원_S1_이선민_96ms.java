package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1309_동물원_S1_이선민_96ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력값 읽어오기
		int N = Integer.parseInt(br.readLine()); // 우리의 크기 N(1≤N≤100,000)

		final int MOD = 9901; // 문제에서 주어진 값
		int[][] dp = new int[N + 1][3]; // (i, j)에 사자를 배치했을 때 가능한 경우의 수
		dp[1][0] = dp[1][1] = dp[1][2] = 1; // N=1일 때 가능한 경우의 수

		for (int i = 2; i < dp.length; i++) {
			// i행에 사자를 한마리도 배치하지 않은 경우
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
			// (i,1)에 사자를 배치한 경우
			dp[i][1] = dp[i - 1][0] + dp[i - 1][2] % MOD;
			// (i,2)에 사자를 배치한 경우
			dp[i][2] = dp[i - 1][0] + dp[i - 1][1] % MOD;
		}

		System.out.println((dp[N][0] + dp[N][1] + dp[N][2]) % MOD);
	}

}
