package boj_0331;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// idea : dfs로 풀면 풀림!
// idea : dp or memoization을 쓰고 싶다면 내리막길과 달리 3차원으로 memo를 두어야함 : 각 방향마다 따로 체크 -> 훨씬 빠르긴함
public class Main_BOJ_17070_파이프옮기기1_G5_신민아_272ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());
		char[][] map = new char[size][size];
		
		// map initialization
		for(int i=0;i<size;i++) {
			String line = br.readLine();
			for(int j=0,index=0;j<size;j++,index+=2) {
				map[i][j] = line.charAt(index);
			}
		}
		
		int[] answer = new int[1]; // 정답 저장용 배열(call by ref 이용)
		int[][] nextDir = {{0, 2}, {1, 2}, {0, 1, 2}}; // 현재 방향에서 다음 방향으로 변경할 수 있는 정보를 저장한 배열
		int[] dr = {0, 1, 1}; // 방향에 따라 위치 변경 값
		int[] dc = {1, 0, 1};
		
		getPathCountByDFS(0, 1, 0, answer, size-1, map, nextDir, dr, dc);
		System.out.print(answer[0]);
	}

	private static void getPathCountByDFS(int r, int c, int curDir, int[] answer, 
											int size, char[][] map, int[][] nextDir, int[] dr, int[] dc) {
		
		// for문에서 미리 안되는 조건을 검사하지 않으므로 안되는 조건 먼저 검사 후 도착점 검사 
		if(r > size || c > size) return; // 좌표가 배열 밖일 경우
		if(map[r][c] == '1') return; // 옮기려는 곳의 좌표에 벽이 있을 경우
		if(curDir == 2 && (map[r-1][c] == '1' || map[r][c-1] == '1')) return; // 현재 방향이 대각선이면서 범위 내에 벽이 있을 경우
		
		if(r == size && c == size) { // 위 조건들을 다 통과하고 도착점일 경우
			answer[0]++; // 경우의 수 증가
			return;
		}
		
		for(int i=0;i<nextDir[curDir].length;i++) {
			int nd = nextDir[curDir][i]; // 다음에 이동할 방향
			getPathCountByDFS(r + dr[nd], c + dc[nd], nd, answer, size, map, nextDir, dr, dc);
		}

	}

}
