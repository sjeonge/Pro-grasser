import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11052_카드구매하기_S1_이승연_96ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 카드의 개수 (1<=N<=1000)
		
		int[] cost = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		 
		for(int i=1; i<N; i++) {
			for(int j=0, size=i/2+1; j<size; j++) {
				cost[i] = Math.max(cost[i], cost[j]+cost[i-j-1]);				
			}
		}
		
		System.out.println(cost[N-1]);
	}
}
