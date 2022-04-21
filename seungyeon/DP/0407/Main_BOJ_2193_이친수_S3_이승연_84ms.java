import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2193_이친수_S3_이승연_84ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 1<=N<=90
		long[] result = new long[N+1];
		
		result[0] = 0; 
		result[1] = 1; 
		
		for(int i=2; i<=N; i++) {
			result[i] = result[i-2] + result[i-1];
		}
		
		System.out.println(result[N]);
	}
}
