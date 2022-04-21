import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2133_타일채우기_G5_이승연_76ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] result = new int[N+1];
		result[0] = 1; 
		
		for(int i=2; i<=N; i++) {
			result[i] += result[i-2]*3; 
			
			int j = i-4;
			while(j>=0) {
				result[i] += result[j]*2;
				j -= 2;
			}
		}
		
		System.out.println(result[N]);
	}
}
