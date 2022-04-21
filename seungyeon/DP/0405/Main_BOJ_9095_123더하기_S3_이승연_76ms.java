import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_9095_123더하기_S3_이승연_76ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
				
		int TC = Integer.parseInt(br.readLine());
		int[] result = new int[11];
		result[1] = 1;
		result[2] = 2;
		result[3] = 4;
		
		for(int testCase=1; testCase<=TC; testCase++) {
			int N = Integer.parseInt(br.readLine());
			
			for(int n=4; n<=N; n++) {
				result[n] = result[n-1]+result[n-2]+result[n-3];
			}
			
			sb.append(result[N]).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
