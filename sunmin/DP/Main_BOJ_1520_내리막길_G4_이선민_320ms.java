package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1520_내리막길_G4_이선민_320ms {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력값 읽어오기
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 공백을 구분자로 하여 한줄 읽어오기
		int row = Integer.parseInt(st.nextToken()); // 산의 세로 크기
		int col = Integer.parseInt(st.nextToken()); // 산의 가로 크기
		int[][] map = new int[row][col]; // 산의 지형 정보
		for (int i = 0; i < row; i++) { // 산의 지형 정보 받아오기
			st = new StringTokenizer(br.readLine(), " "); // 공백을 구분자로 하여 한줄 읽어오기
			for (int j = 0; j < col; j++) { // 산의 지형 정보 받아오기
				map[i][j] = Integer.parseInt(st.nextToken()); // 산의 한 지점 높이 저장
			}
		}

		int[][] memoi = new int[row][col]; // 메모이제이션을 위한 배열
		for (int i = 0; i < memoi.length; i++) {
			Arrays.fill(memoi[i], -1);	// 0으로 둘 경우, 경로가 없는것과 아직 탐색하지 않은 지점을 구분하지 못함
		}
		System.out.println(go(0, 0, row, col, map, memoi));
	} // end of main

	private static int[] dr = { 0, 1, 0, -1 }; // 사방탐색 : 행
	private static int[] dc = { 1, 0, -1, 0 }; // 사방탐색 : 열

	/**
	 * dfs 탐색, 현 지점에서 내리막길 탐색 후 가능한 경로의 수 return
	 */
	private static int go(int r, int c, int row, int col, int[][] map, int[][] memoi) {
		if (r == row - 1 && c == col - 1) { // 목표지점 도착
			return 1;
		}

		if (memoi[r][c] != -1) { // 이미 탐색한 지점
			return memoi[r][c];
		}

		int cnt = 0;
		for (int i = 0; i < dc.length; i++) {
			int nr = r + dr[i]; // 확인할 방향의 r좌표
			int nc = c + dc[i]; // 확인할 방향의 c좌표
			if (nr < 0 || nr >= row || nc < 0 || nc >= col) { // 확인할 좌표가 배열 범위를 넘어서면
				continue; // 다음 방향 확인
			}

			if (map[r][c] > map[nr][nc]) { // 확인할 좌표가 이미 방문한 지점이거나 내리막길이 아닐 경우
				cnt += go(nr, nc, row, col, map, memoi);
			}
		}

		return memoi[r][c] = cnt;
	} // end of go

} // end of class
