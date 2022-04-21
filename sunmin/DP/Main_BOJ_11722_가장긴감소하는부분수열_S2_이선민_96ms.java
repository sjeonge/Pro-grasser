package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_11722_가장긴감소하는부분수열_S2_이선민_96ms {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 수열의 크기
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N]; // 최장 감소 수열의 길이 저장
		Arrays.fill(dp, 1); // 자기 자신만 있는 경우가 최소
		for (int i = 1; i < N; i++) {
			int temp = 0;
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[i] && temp < dp[j]) { // 이전 숫자들 중 최장 감소 수열을 찾아서 길이를 temp에 저장
					temp = dp[j];
				}
			}
			dp[i] += temp;
		}

		int max = 0;
		for (int i = 0; i < N; i++) {
			if (max < dp[i]) {
				max = dp[i];
			}
		}

		System.out.println(max);

	} // end of main

}
