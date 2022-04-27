package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11048_이동하기_이선민_384ms_dp {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int[][] maze = new int[row + 1][col + 1];
		int max = -1;
		for (int i = 1; i <= row; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= col; j++) {
				maze[i][j] = Integer.parseInt(st.nextToken());
				// 대각선위, 위, 왼쪽 중 가장 큰 값을 더해줌
				max = Math.max(maze[i - 1][j - 1], Math.max(maze[i - 1][j], maze[i][j - 1]));
				maze[i][j] += max;
			}
		}

		System.out.println(maze[row][col]);

	} // end of main

} // end of class
