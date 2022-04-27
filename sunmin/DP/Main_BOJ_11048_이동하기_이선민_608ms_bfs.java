package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_11048_이동하기_이선민_608ms_bfs {
	static class Pos {
		int r, c; // 위치 좌표

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	} // end of Pos class

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int[][] maze = new int[row][col];
		for (int i = 0; i < maze.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < col; j++) {
				maze[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[row][col]; // 현재 위치까지 모을 수 있는 사탕의 최대 개수
		dp[0][0] = maze[0][0];

		// 하, 우, 대각선 순서
		int[] dr = { 1, 0, 1 };
		int[] dc = { 0, 1, 1 };
		
		// bfs로 완전탐색
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(0, 0)); // 시작위치 입력
		boolean[][] visited = new boolean[row][col]; // 방문체크를 위한 배열
		while (!q.isEmpty()) {
			Pos curr = q.poll();
			if (visited[curr.r][curr.c]) { // 이미 방문한 지점이면
				continue;
			}
			visited[curr.r][curr.c] = true;

			for (int i = 0; i < dc.length; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				if (nr < 0 || nr >= row || nc < 0 || nc >= col) {
					continue;
				}

				if (dp[nr][nc] < dp[curr.r][curr.c] + maze[nr][nc]) {	// 모을 수 있는 최대 사탕개수 갱신
					dp[nr][nc] = dp[curr.r][curr.c] + maze[nr][nc];
				}
				q.offer(new Pos(nr, nc));
			}

		} // end of while

		System.out.println(dp[row - 1][col - 1]);

	} // end of main


} // end of class
