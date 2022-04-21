package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2133_타일채우기_G5_이선민_72ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N % 2 != 0) { // N이 홀수일 경우, 타일을 딱맞게 채울 수 없음
			System.out.println(0);
			return;
		}

		int[] dp = new int[N + 1];
		dp[0] = 1; // 짝수인 N마다 특이한 모양의 타일채우는 방법 2가지가 생기는데 이를 안쓰는 배열에 담아줌
		dp[2] = 3;
		for (int i = 4; i < dp.length; i += 2) {
			dp[i] += dp[i - 2] * dp[2];
			for (int j = i - 4; j >= 0; j -= 2) {
				dp[i] += dp[j] * 2;
			}
		}

		System.out.println(dp[N]);

	} // end of main

}
