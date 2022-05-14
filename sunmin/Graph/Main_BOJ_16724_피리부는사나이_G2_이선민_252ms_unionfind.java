package Graph;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 칸의 좌표를 (r,c)가 아닌 r*col+c로 변경해서 union-find 사용
 */
public class Main_BOJ_16724_피리부는사나이_G2_이선민_252ms_unionfind {

	private static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int row = Integer.parseInt(st.nextToken()); // 행의 수(1 ≤ row ≤ 1,000)
		int col = Integer.parseInt(st.nextToken()); // 열의 수(1 ≤ col ≤ 1,000)
		char[][] map = new char[row][];
		for (int i = 0; i < map.length; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int mapNum = row * col; // 지도의 칸 개수
		parents = new int[mapNum]; // 루트 번호
		// 초기화
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		int ans = 0; // 최소 safe zone 개수
		int[] drdc = { -col, col, -1, 1 }; // 위, 아래, 왼쪽, 오른쪽 순서

		// union
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int now = i * col + j; // 현재칸 좌표(i,j) -> i*col+j
				int next = now + drdc[way(map[i][j])]; // 다음칸
				if (union(now, next)) { // 사이클이 만들어진 경우
					ans++;
				}
			}
		}
		System.out.println(ans);
	} // end of main

	/**
	 * 같은 집합이었으면 true, 다른 집합이었으면 false 반환
	 */
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) {
			return true;
		}
		parents[bRoot] = aRoot;
		return false;
	} // end of union

	private static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	} // end of find

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
