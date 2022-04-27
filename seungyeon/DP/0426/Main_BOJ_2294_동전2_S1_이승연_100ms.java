import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2294_동전2_S1_이승연_100ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()); // 동전 종류 (1<=n<=100)
		int k = Integer.parseInt(st.nextToken()); // 가치 합  (1<=k<=10000)
		
		int[] result = new int[k+1];
		int v;
		
		for(int i=0; i<n; i++) {
			v = Integer.parseInt(br.readLine());
			if(v > k) continue;
			result[v] = 1;
			for(int j=v+1; j<=k; j++) {
				int d = j-v;
				if(result[j] != 0) {
					if(result[d] != 0) result[j] = Math.min(result[j], result[d]+1);
				}
				else if(result[d] != 0) result[j] = result[d]+1;
			}
		}
		
		if(result[k] != 0) {
			System.out.println(result[k]);
		} else {
			System.out.println("-1");
		}
	}
}
