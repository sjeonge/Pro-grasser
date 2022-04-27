import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1699_제곱수의합_S3_이승연_116ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] result = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			result[i] = i; // 1 + ... + 1 : 1만 N개 
			for(int j=1; i>=j*j; j++) {
				if(result[i-j*j]+1 < result[i]) { // 1제곱, 2제곱 3제곱... 가장 작은걸로 업데이트 
					result[i] = result[i-j*j]+1;					
				}
			}
		}
		
		System.out.println(result[N]);
	}
}
