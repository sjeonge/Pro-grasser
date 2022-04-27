package boj_0426;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// idea : 현재 위치를 기준으로 (i-1, j), (i, j-1), (i-1, j-1)의 값들을 비교
public class Main_BOJ_11048_이동하기_S1_신민아_436ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int rSize = Integer.parseInt(st.nextToken()); // 행의 크기
		int cSize = Integer.parseInt(st.nextToken()); // 열의 크기
		
		int[][] dp = new int[++rSize][++cSize]; // dp 저장용 배열(별다른 map 저장이 필요 x), 범위 체크를 피하기 위해 padding을 왼쪽, 위에 1씩 둠
		
		for(int i=1;i<rSize;i++) { // padding이 있기 때문에 1부터 시작
			st = new StringTokenizer(br.readLine(), " "); // 현재 들어오는 행의 값을 쪼갬
			for(int j=1;j<cSize;j++) { // padding이 있기 때문에 1부터 시작
				
				// 현재 위치의 최대 값은 현재 위치를 기준으로 (i-1, j), (i, j-1), (i-1, j-1)의 값 중 가장 큰 값을 구하고 현재 들어오는 값을 더해주면 됨
				dp[i][j] = Math.max(dp[i-1][j-1], Math.max(dp[i-1][j], dp[i][j-1])) + Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.print(dp[rSize-1][cSize-1]);
	}

}
