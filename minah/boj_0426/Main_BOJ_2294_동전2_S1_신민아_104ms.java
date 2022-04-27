package boj_0426;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2294_동전2_S1_신민아_104ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int coinCount = Integer.parseInt(st.nextToken());
		int answer = Integer.parseInt(st.nextToken());
		
		int[] coins = new int[coinCount];
		int[] dp = new int[answer + 1];
		
		Arrays.fill(dp, -1); // 동전으로 금액을 만들 수 없는 경우는 -1로 출력해야 하므로 미리 -1로 초기화
		
		for(int i=0;i<coinCount;i++) { // 동전에 대한 정보 넣기
			coins[i] = Integer.parseInt(br.readLine()); // 새로 들어온 값을 동전 정보로 넣기
			
			if(coins[i] < dp.length) // 동전의 금액이 목표 금액보다 작은 경우에만 1로 초기화
				dp[coins[i]] = 1;
		}
		
		Arrays.sort(coins); // 크기 순으로 정렬
		
		for(int i=coins[0]+1;i<dp.length;i++) {
			if(dp[i] == -1) { // 목표 금액을 채우는 최소 동전의 개수가 정해지지 않은 경우에
				dp[i] = 100001; // Math.min을 사용하기 위해 100001로 초기화
				for(int j=0;j<coins.length;j++) {
					if(i <= coins[j]) break; // 동전의 개수를 검사하려는 금액보다 현재 쓰려고 하는 동전이 더 크면 빠져나옴(오름차순 정렬을 했기 때문에 가능)
					
					if(dp[i - coins[j]] != -1) // 현재 검사하려는 금액에서 동전을 사용한다고 가정할 때, (검사하는 금액 - 현재 동전)의 금액이 만들어 질 수 있다면
						dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1); // 더 작은 값으로 변경
				}
			}
			
			if(dp[i] == 100001) // 만약 바뀐적이 없다면
				dp[i] = -1; // 만들 수 없으므로 -1로 초기화
		}
		
		System.out.print(dp[answer]);
	}

}
