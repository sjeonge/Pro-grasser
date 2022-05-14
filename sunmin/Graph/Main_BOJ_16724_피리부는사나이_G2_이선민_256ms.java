package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_16724_피리부는사나이_G2_이선민_256ms {
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int row = Integer.parseInt(st.nextToken()); // 행의 수(1 ≤ row ≤ 1,000)
		int col = Integer.parseInt(st.nextToken()); // 열의 수(1 ≤ col ≤ 1,000)
		char[][] map = new char[row][];
		for (int i = 0; i < map.length; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int[][] visited = new int[row][col]; // 0: 미방문 칸, 1: 방문중인 칸, 2: safe zone을 찾은 칸
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (visited[i][j] == 0) { // 미방문한 칸에 대해 dfs 실행
					dfs(i, j, map, visited);
				}
			}
		}
		System.out.println(ans);
	} // end of main

	// 상,하,좌,우 순서
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static void dfs(int r, int c, char[][] map, int[][] visited) {
		visited[r][c] = 1;
		int nr = r + dr[way(map[r][c])];
		int nc = c + dc[way(map[r][c])];

		if (visited[nr][nc] == 0) { // 미방문 칸
			dfs(nr, nc, map, visited);
		} else if (visited[nr][nc] == 1) { // 지나온 칸 -> 사이클이 형성됨
			ans++;
		}

		visited[r][c] = 2; // 사이클을 찾았다면 safe zone을 찾은 칸으로 값을 바꿔줌

	} // end of dfs

	/**
	 * 지도 정보에 따라 가야할 방향을 알려줌
	 */
	private static int way(char dir) {
		switch (dir) {
		case 'U':
			return 0;
		case 'D':
			return 1;
		case 'L':
			return 2;
		case 'R':
			return 3;
		}
		return -1;
	} // end of way
} // end of class
