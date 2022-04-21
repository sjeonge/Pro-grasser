package boj_0419;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// idea : 시간초과 , 메모리 초과가 나지 않기 위해 bfs말고 dfs + dp를 사용
// dp 사용 시 경로 저장 배열은 -1로 초기화(0으로 내비두면 시간초과걸림..?)
public class Main_BOJ_1520_내리막길_G4_신민아_300ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// 입력을 받기 위한 BufferedReader

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int rSize = Integer.parseInt(st.nextToken());
		int cSize = Integer.parseInt(st.nextToken());

		int[][] map = new int[rSize + 2][cSize + 2]; // 맵의 크기를 이용하여 map 2차원 배열 생성
		int[][] memo = new int[rSize + 2][cSize + 2];		
		
		// initialization
		for (int i = 1; i <= rSize; i++) {
			st = new StringTokenizer(br.readLine(), " "); // 각 행마다 map의 정보를 저장하기 위해 공백으로 나눔
			for (int j = 1; j <= cSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 각 좌표마다 map의 정보를 저장
				memo[i][j] = -1; // -1로 초기화 --> 0으로 초기화 x : 경우의 수가 0인것과 탐색이 안된 것을 구분을 못하므로 -1로 초기화
			}
		}
		
		// 사방 탐색용 배열
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };

		System.out.print(getPathCount(1, 1, rSize, cSize, map, memo, dr, dc)); // 출력
	}

	// 돼지가 내려가는 경우의 수를 찾는 메소드 by dfs
	private static int getPathCount(int r, int c, int finishR, int finishC, int[][] map, int[][] memo, int[] dr, int[] dc) {
		if (memo[r][c] != -1) return memo[r][c]; // 이미 해당 경로에 왔다간 흔적이 있다면 해당 위치까지 갈 수 있는 경우의 수 리턴
		if (r == finishR && c == finishC) return 1; // 마지막 점에 도착했다면 1 리턴

		int current = map[r][c]; // 현재 위치
		memo[r][c] = 0; // 경우의 수를 세기 위해 0으로 초기화
		for (int d = 0; d < 4; d++) { // 4방 탐색
			int nr = r + dr[d]; // 다음 위치
			int nc = c + dc[d];

			if (map[nr][nc] > 0 && current > map[nr][nc]) // 범위 안이거나 현재 위치가 다음 위치보다 높다면
				memo[r][c] += getPathCount(nr, nc, finishR, finishC, map, memo, dr, dc); // 경우의 수 저장용 배열에 증가
		}

		return memo[r][c]; // 리턴
	}
}
