package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2294_동전2_S1_이선민_100ms {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()); // 동전의 종류
		int k = Integer.parseInt(st.nextToken()); // 만들려는 금액
		int[] dp = new int[k + 1]; // 현재 금액을 만드는데 사용한 동전의 최소 개수
		Arrays.fill(dp, 10001); // 동전의 최소가치가 1이고 k가 10000일 경우가 최대이므로
		dp[0] = 0;
		for (int i = 0; i < n; i++) {
			int money = Integer.parseInt(br.readLine()); // 동전 금액
			for (int j = money; j < dp.length; j++) {
				if (dp[j] > dp[j - money] + 1) {
					dp[j] = dp[j - money] + 1;
				}
			}
		}

		if (dp[k] == 10001) { // 원하는 금액을 만들 수 없는 경우
			System.out.print(-1);
		} else {
			System.out.print(dp[k]);
		}
	} // end of main

} // end of class
