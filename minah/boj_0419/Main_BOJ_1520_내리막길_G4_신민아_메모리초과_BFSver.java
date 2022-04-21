package boj_0419;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1520_내리막길_G4_신민아_메모리초과_BFSver {
	static class Node { // 좌표 저장용 Class
		int r, c; // r : 행, c : 열

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// 입력을 받기 위한 BufferedReader

		StringTokenizer st; // 맴의 크기, 맵의 정보 값을 가져오기 위한 StringTokenizer
		int[][] map; // map 배열 미리 선언

		st = new StringTokenizer(br.readLine(), " "); // 맵의 크기를 받기 위해 공백으로 나눔
		int rSize = Integer.parseInt(st.nextToken()); // 맵의 행 크기
		int cSize = Integer.parseInt(st.nextToken()); // 맵의 열 크기
		map = new int[rSize + 2][cSize + 2]; // 맵의 크기를 이용하여 map 2차원 배열 생성

		// initialization
		for (int i = 1; i <= rSize; i++) {
			st = new StringTokenizer(br.readLine(), " "); // 각 행마다 map의 정보를 저장하기 위해 공백으로 나눔
			for (int j = 1; j <= cSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 각 좌표마다 map의 정보를 저장
			}
		}

		System.out.print(getPathCount(map)); // 출력
	}

	// 돼지가 내려가는 경우의 수를 찾는 메소드
	private static int getPathCount(int[][] map) {
		int count = 0; // 돼지가 내려가는 경우의 수

		int[] dr = { 1, 0, 0, -1 }; // 사방 탐색용 배열 : 행 좌표 이동
		int[] dc = { 0, 1, -1, 0 }; // 사방 탐색용 배열 : 열 좌표 이동

		int finishR = map.length - 2; // 도착 지점의 행 좌표
		int finishC = map[0].length - 2; // 도착 지점의 열 좌표

		Queue<Node> queue = new ArrayDeque<Node>(); // 좌표 이동용 queue

		queue.offer(new Node(1, 1)); // 시작 지점은 항상 1, 1이므로 우선 넣고 시작

		while (queue.size() > 0) {
			Node cur = queue.poll(); // 현재 위치를 꺼냄

			if (cur.r == finishR && cur.c == finishC) { // 현재 위치가 도착점이라면
				count++; // 경우의 수 증가
			}

			for (int d = 0; d < 4; d++) { // 현재 위치를 기준으로 사방 탐색
				int nr = cur.r + dr[d]; // 다음 위치의 행 좌표
				int nc = cur.c + dc[d]; // 다음 위치의 열 좌표

				// 다음 좌표로 이동이 가능한지 확인 : map의 범위밖으로 나갔거나 가려는 위치가 현재 위치보다 값이 크거나 같으면 돼지가 이동 불가
				// visit check는 현재 높이가 다음에 갈 높이보다 높다는 것에서 체크 가능 -> 별도의 visit check 필요 없음
				if (map[nr][nc] == 0 || map[cur.r][cur.c] <= map[nr][nc])
					continue;

				queue.offer(new Node(nr, nc)); // 다음 위치를 queue에 넣어줌
			}
		}

		return count; // 경우의 수 리턴
	}
}
