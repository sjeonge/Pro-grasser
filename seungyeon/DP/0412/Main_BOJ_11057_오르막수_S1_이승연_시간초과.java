import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_11057_오르막수_S1_이승연_시간초과 {
	private static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		calc(N, 0, new int[N]);
		
		System.out.println(result);
	}
	
	private static void calc(int N, int digit, int[] num) {
		if(digit == N) {
			result++;
			if(result>=10007) result%=10007;
			return;
		}
		
		for(int i=(digit==0 ? 0 : num[digit-1]); i<10; i++) {
			num[digit] = i;
			calc(N, digit+1, num);
		}
	}
}
