package boj_0421;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BOJ_1699_제곱수의합_S3_신민아_128ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.parseInt(br.readLine());
		
		int[] arr = new int[number + 1]; // dp 저장용 배열
		int[] sqrtInfo = new int[(int) Math.sqrt(number) + 1]; // 제곱수 저장용 배열
		
		Arrays.fill(arr, 100000); // Math.min 사용을 위해 초기 배열을 100000으로 초기화
		arr[0] = 0; // 0은 0으로 초기화
		
		// 제곱수 저장용 배열 초기화
		for(int i=1;i<sqrtInfo.length;i++) {
			sqrtInfo[i] = i * i;
		}

		// 제곱수 배열을 이용하여 현재 검사하려는 숫자에서 제곱수를 차례로 빼서 제일 적은 항이 얼마인지 검사
		for(int i=1;i<arr.length;i++) {
			int sqIndex = (int) Math.sqrt(i) + 1; // 현재 검사하려는 수 중 제일 큰 제곱수의 root(<= 연산을 피하기 위해 +1)
			
			if(sqrtInfo[(int) Math.sqrt(i)] == i) { // 가지치기용(의미 없음..) : 현재 수가 이미 제곱수라면
				arr[i] = 1; // arr[i]은 1로 초기화하고
				continue; // 다음 수 검색
			}
			
			for(int j=1;j<sqIndex;j++) { // 현재 수에서 제곱수를 하나씩 빼며 최소 항의 개수를 구함
				arr[i] = Math.min(arr[i], 1 + arr[i - sqrtInfo[j]]);
				if(arr[i] == 2) break; // 2보다 작은 경우는 없으므로(위에서 가지치기 함) 2가 나오면 빠져나옴
			}
				
		}		
		
		System.out.print(arr[number]);
	}

}
