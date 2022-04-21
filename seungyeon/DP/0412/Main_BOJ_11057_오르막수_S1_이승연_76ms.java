import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BOJ_11057_오르막수_S1_이승연_76ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		int[] result = new int[10]; // 0~9(index)로 끝나는 수의 개수 저장 
		Arrays.fill(result, 1);
		
		for(int i=0; i<N; i++) {
			for(int j=9; j>=0; j--) {
				for(int k=0; k<j; k++) {
					result[j] += result[k];
					if(result[j] >= 10007) result[j] %= 10007;
				}
			}
		}
		
		System.out.println(result[9]);
	}
}
