package dp0421;
//https://yabmoons.tistory.com/536
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_2133_타일채우기_G5_양소정_88ms{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int dp[]=new int[N+1];
		dp[0]=1;
		
		for(int i=2;i<N+1;i=i+2) {
			dp[i]=dp[i-2]*3;
			for(int j=0;j<i-2;j=j+2) {
				dp[i]+=dp[j]*2;
			}
		}
		System.out.println(dp[N]);
	}

}
//dp[i]=dp[i-2]*3+(dp[i-4]+dp[i-6]+...)*2

