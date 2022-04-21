import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11055_가장큰증가부분수열_S2_이승연_100ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 수열 A의 크기 (1<=N<=1000)
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] result = new int[N];
		
		for(int i=0; i<N; i++) {
			result[i] = arr[i]; // 앞에 올 수 있는 대상이 아무도 없는 경우, 나를 끝으로 하는 최장길이가 가질 수 있는 가장 최솟값
			for(int j=0; j<i; j++) {
				if(arr[j]<arr[i] && result[i]<result[j]+arr[i]) {
					result[i] = result[j]+arr[i];
				}
			}
		}
		
		int max_result = 0;
		for(int i=0; i<N; i++) {
			max_result = Math.max(max_result, result[i]);
		}
		
		System.out.println(max_result);
	}
}
