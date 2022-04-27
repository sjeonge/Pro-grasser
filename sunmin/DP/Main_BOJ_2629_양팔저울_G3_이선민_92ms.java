package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2629_양팔저울_G3_이선민_92ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력값 읽어오기
		StringBuilder sb = new StringBuilder(); // 출력을 한번에 하기위한 스트링빌더
		int chuNum = Integer.parseInt(br.readLine()); // 추의 개수
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 공백을 구분자로 하여 한줄 읽어오기
		int[] chus = new int[chuNum]; // 추를 저장할 배열
		for (int i = 0; i < chus.length; i++) {
			chus[i] = Integer.parseInt(st.nextToken()); // 추의 무게 저장
		}

		boolean[][] memo = new boolean[chuNum][30 * 500 + 1];
		memo[0][0] = true;
		memo[0][chus[0]] = true; // 1번째 추를 고려하는 것은 직접 입력
		for (int i = 1; i < memo.length; i++) {
			for (int j = 0; j < memo[i].length; j++) { // 확인할 무게
				if (!memo[i - 1][j]) // 만들 수 없는 무게
					continue;

				memo[i][j] = true; // 이전에 가능했던 무게는, 이번추를 고려해도 가능한 무게임
				int a = j + chus[i];
				int b = j - chus[i];
				if (b < 0) { // 음수면
					b = -b; // 부호 변경
				}
				memo[i][a] = memo[i][b] = true;
			}
		}

		int marbleNum = Integer.parseInt(br.readLine()); // 구슬의 개수
		st = new StringTokenizer(br.readLine(), " "); // 공백을 구분자로 하여 한줄 읽어오기
		for (int i = 0; i < marbleNum; i++) {
			int marble = Integer.parseInt(st.nextToken()); // 구슬의 무게
			if (marble <= 15000 && memo[chuNum - 1][marble]) { // 구슬의 무게 확인이 가능하면
				sb.append("Y "); // Y 출력
			} else { // 구슬의 무게 확인이 불가능하면
				sb.append("N "); // N 출력
			}
		}

		System.out.print(sb.toString()); // 스트링빌더에 쌓아둔 값 출력하기
	} // end of main

} // end of class
