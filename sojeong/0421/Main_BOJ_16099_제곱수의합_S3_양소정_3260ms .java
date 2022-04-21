package dp0421;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_16099_제곱수의합_S3_양소정_3260ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int []dp = new int[100001];
		dp[1] = 1;
		
		for (long i = 2; i <= N; i++) {
			if(i*i<=100001) {
				dp[(int)(i*i)]=1;
				if(i*i==N) {
					System.out.println(1);
					System.exit(0);
				}
			}
			if(dp[(int)i]==1)	continue; //제곱수 넘어가기
			dp[(int)i]= 100002; 
			
			for (int j = 1; j <= i/2; j++) {
				dp[(int)i]=Math.min(dp[(int)(i-j)]+dp[j],dp[(int)i]);	
			}
		}
		System.out.println(dp[N]);
		
		
	}

}
//i=대충47000이상 일때 인트값넘어감  ->long형으로 
