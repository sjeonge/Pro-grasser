import java.io.BufferedReader;
import java.io.InputStreamReader;

// 1, 4, 6원 동전이 있을 때 최소 동전 개수를 구하는 프로그램
public class DP2_MinCoinChangeTest {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int money = Integer.parseInt(br.readLine());
		int[] dp = new int[money+1]; // money원을 구하기 위해 처음부터 계산
		dp[0] = 0; // 0원을 만드는 최소 동전 개수는 0개
		
		for(int i=1;i<=money;i++) {
			int min = Integer.MAX_VALUE;
			
			if(i >= 1 && dp[i-1] + 1 < min)
				min = dp[i-1] + 1;
			
			if(i >= 4 && dp[i-4] + 1 < min)
				min = dp[i-4] + 1;
			
			if(i >= 6 && dp[i-6] + 1 < min)
				min = dp[i-6] + 1;
			
			dp[i] = min;
		}
		
		System.out.println(dp[money]);
	}

}
