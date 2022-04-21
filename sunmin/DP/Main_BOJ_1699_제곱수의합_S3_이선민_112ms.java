package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 가장 큰 제곱수를 뺀다고 항상 항의 개수가 최소가 되는것이 아님
 * ex) 41
 * = 36 + 4 + 1
 * = 25 + 16
 */
public class Main_BOJ_1699_제곱수의합_S3_이선민_112ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1]; // 해당 숫자를 만드는 제곱수 항의 최소 개수
		dp[1] = 1;
		for (int i = 2; i < dp.length; i++) {
			int min = i;
			for (int j = 1; j * j <= i; j++) { // i보다 작은 제곱수들을 i에서 빼보면서 최솟값을 구함
				if (min > dp[i - j * j] + 1) { // 처음에 뺀 제곱수도 더해줘야 하므로 +1을 해줌
					min = dp[i - j * j] + 1;
				}
			}
			dp[i] = min;
		}
		System.out.println(dp[N]);
	} // end of main

}
