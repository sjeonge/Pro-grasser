package boj_0421;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// idea : 어떤 방법으로든 (2k+1)*3을 채우는 경우의 수는 없음(짝수의 경우만 고민)
public class Main_BOJ_2133_타일채우기_G5_신민아_84ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.parseInt(br.readLine());
		
		int[] dp = new int[31]; // number+1로 하면 number가 1일때 오류 생김
		dp[2] = 3; // basis : 1*2, 2*1 블럭으로 2*3을 구성하는 경우의 수 
		
		// 4*3부터 짝수인 경우에만 계산
		for(int i=4;i<dp.length;i+=2) {
			dp[i] = dp[i-2] * dp[2] + 2; // f(n-2)의 "오른쪽"에 f(2)의 블럭을 놓는다고 가정(f(n-2) * 2 * f(2)는 중복이 많음) + 각 짝수마다 둘 수 있는 특수블록이 2개씩 있음
			for(int j=i-4;j>1;j--) { // 각 특수블록의 "왼쪽"에 f(2), f(4), ... 등의 블럭을 중복없이 두기 위해 추가 계산
				dp[i] += dp[j] * 2;
			}
		}
		
		System.out.print(dp[number]);
	}

}
