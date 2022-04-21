package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_17070_파이프옮기기1_G5_이선민_212ms {
	static class Pipe {
		int r, c; // 파이프 끝쪽 좌표
		int dir; // 파이프 방향(0: 가로, 1: 세로, 2: 대각선)

		public Pipe(int r, int c, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	private static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 수열의 크기
		char[][] house = new char[N][];
		for (int i = 0; i < house.length; i++) {
			house[i] = br.readLine().replace(" ", "").toCharArray();
		}

		if (house[N - 1][N - 1] == '1') {
			System.out.println(0);
			return;
		}
		ans = 0;
		dfs(new Pipe(0, 1, 0), N, house);
		System.out.println(ans);
	} // end of main

	private static void dfs(Pipe pipe, int N, char[][] house) {
		if (pipe.r == N - 1 && pipe.c == N - 1) {
			ans++;
			return;
		}

		switch (pipe.dir) {
		case 0: // 가로방향
			if (inbound(pipe.r, pipe.c + 1, N) && house[pipe.r][pipe.c + 1] == '0') {
				dfs(new Pipe(pipe.r, pipe.c + 1, 0), N, house);
			}

			break;
		case 1: // 세로방향
			if (inbound(pipe.r + 1, pipe.c, N) && house[pipe.r + 1][pipe.c] == '0') {
				dfs(new Pipe(pipe.r + 1, pipe.c, 1), N, house);
			}

			break;
		case 2: // 대각선방향
			if (inbound(pipe.r, pipe.c + 1, N) && house[pipe.r][pipe.c + 1] == '0') {
				dfs(new Pipe(pipe.r, pipe.c + 1, 0), N, house);
			}

			if (inbound(pipe.r + 1, pipe.c, N) && house[pipe.r + 1][pipe.c] == '0') {
				dfs(new Pipe(pipe.r + 1, pipe.c, 1), N, house);
			}
			break;
		} // end of switch

		// 대각선 이동
		if (inbound(pipe.r + 1, pipe.c + 1, N) && house[pipe.r][pipe.c + 1] == '0' && house[pipe.r + 1][pipe.c] == '0'
				&& house[pipe.r + 1][pipe.c + 1] == '0') {
			dfs(new Pipe(pipe.r + 1, pipe.c + 1, 2), N, house);
		}
	} // end of dfs

	/**
	 * 배열 범위 안이면 true, 밖이면 false
	 */
	private static boolean inbound(int r, int c, int N) {
		if (r < 0 || r >= N || c < 0 || c >= N) {
			return false;
		}

		return true;
	} // end of inbound

} // end of class
