package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2293_동전1_G5_이승연_88ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken()); // 동전의 종류 (1<=n<=100)
		int k = Integer.parseInt(st.nextToken()); // 목표 가치 (1<=k<=10000)
		
		int[] value = new int[n]; // 동전의 가치 
		
		for(int i=0; i<n; i++) {
			value[i] = Integer.parseInt(br.readLine());
		}
		
		int[] result = new int[k+1];
		result[0] = 1; 
		
		for(int i=0; i<n; i++) {
			int idx = value[i];
//			if(idx > k) continue;
//			result[idx]++; 
			while(idx <= k) {
				result[idx] += result[idx-value[i]];	
				idx++;
			}
		}
		
		System.out.println(result[k]);
	}
}	
