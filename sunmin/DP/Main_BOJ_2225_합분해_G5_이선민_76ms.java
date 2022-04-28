package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2225_합분해_G5_이선민_76ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력값 읽어오기
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 1 ≤ N,K ≤ 200
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		final int MOD = 1000000000; // 문제에서 주어진 값
		int[][] dp = new int[K + 1][N + 1]; // i개의 정수로 j를 만드는 경우의 수
		// 기저조건
		Arrays.fill(dp[1], 1);
		for (int k = 1; k < dp.length; k++) {
			dp[k][0] = 1;
		}

		for (int k = 2; k < dp.length; k++) {
			for (int n = 1; n <= N; n++) {
				dp[k][n] = (dp[k][n - 1] + dp[k - 1][n]) % MOD;
			}
		}

		System.out.println(dp[K][N]);
	}

}
