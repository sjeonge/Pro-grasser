import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_11726_2n타일링_S3_이승연_76ms {
	 public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] result = new int[N+2];
		result[1] = 1;
		result[2] = 2;
		
		for(int n=3; n<=N; n++) {
			result[n] = (result[n-1] + result[n-2])%10007;
		}
		
		System.out.println(result[N]);
	 }
}
