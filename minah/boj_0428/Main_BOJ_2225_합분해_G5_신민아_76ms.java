package boj_0428;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// idea : 현재 계산하려는 값(n)에서 k개의 원소를 사용하는 경우의 수는 n-1에서 k개를 사용한 원소의 개수와 n에서 k-1을 사용한 원소의 개수를 더해주면 됨
// f(n) = f(n-1)에 숫자를 하나씩 더한다고 생각
public class Main_BOJ_2225_합분해_G5_신민아_76ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int mod = 1000000000; // 나머지 연산을 위한 수
		
		int answer = Integer.parseInt(st.nextToken()); // 경우의 수를 구할 수
		int elements = Integer.parseInt(st.nextToken()); // 더하는 항의 개수
		
		int[][] dp = new int[answer+1][elements]; // 정답 * 항의 개수 만큼 2차원 배열 생성
		
		Arrays.fill(dp[1], 1); // basis : 1의 경우에는 모두 1가지의 경우말고는 없으므로 1로 채워줌
		
		for(int i=2;i<dp.length;i++) { // 정답이 2인 경우부터 계산 시작
			dp[i][0] = 1;
			for(int j=1;j<dp[i].length;j++)
				dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % mod; // dp[i][j] = dp[i-1][j] + dp[i][j-1]
		}
		
		// 모든 경우의 수를 합치면서 % mod로 나머지 연산
		int sum = 0;
		for(int current : dp[answer]) {
			sum = (sum + current) % mod;
		}
		
		System.out.print(sum);
	}

}
