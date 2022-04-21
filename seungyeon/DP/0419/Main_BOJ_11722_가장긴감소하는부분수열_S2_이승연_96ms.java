import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11722_가장긴감소하는부분수열_S2_이승연_96ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 수열 A의 크기 (1<=N<=1000)
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int[] result = new int[N]; 
		
		for(int i=0; i<N; i++) {
			result[i] = 1;  // 앞에 올 수 있는 대상이 아무도 없는 경우. 나를 끝으로 하는 최장 길이가 가질 수 있는 최솟값 
			for(int j=0; j<i; j++) {
				if(arr[i]<arr[j] && result[i]<result[j]+1) { // 앞의 수 중 현재 인덱스의 수보다 크고, 결과가 최장이 될 수 있으면 업데이트 
					result[i] = result[j]+1;
				}
			}
		}
		
		int max = 0; 
		for(int i=0; i<N; i++) max = Math.max(max, result[i]);
		
		System.out.println(max);
	}
}
