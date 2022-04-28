import java.util.Scanner;

/**
 * 
 * testcase
6
3 2 6 4 5 1
==>3

10
8 2 4 3 6 11 7 10 14 5
==>6
 *
 */
public class DP1_LIStest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr = new int[N];
		int[] LIS = new int[N]; // 자신을 끝으로 하는 LIS 길이
		
		for(int i=0;i<N;i++) {
			arr[i] = sc.nextInt();
		}
		
		int max = 0; // 해당 수열의 LIS 최장 길이
		for(int i=0;i<N;i++) { // 모든 원소에 대해 자신을 끝으로 하는 LIS 길이 계산
			LIS[i] = 1; // 자신 혼자 LIS 구성 시 길이 1로 초기화
			for(int j=0;j<i;j++) { // 0부터 현재 원소 직전까지 검사
				if(arr[j] < arr[i] && LIS[i] < LIS[j]+1) {
					LIS[i] = LIS[j]+1;
				}
			}
			
			if(max < LIS[i]) max = LIS[i];
		}
		
		System.out.print(max);
		
	}

}
