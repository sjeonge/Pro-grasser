package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20040_사이클게임_이선민_712ms {

	private static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()); // 점의 개수(3 ≤ n ≤ 500,000)
		int m = Integer.parseInt(st.nextToken()); // 진행된 차례의 수( 3 ≤ m ≤ 1,000,000)

		parents = new int[n]; // 루트번호
		// 초기 설정 : 모든 노드가 서로소집합이며 루트는 자기자신
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			if (union(v1, v2)) { // 사이클이 만들어졌으면 true, 아니면 false
				System.out.println(i);
				return;
			}
		}

		// 사이클이 아직 형성되지 않았다면 0 출력
		System.out.println(0);
	} // end of main

	/**
	 * 이미 같은 집합이면 true, 아니면 false 반환
	 */
	private static boolean union(int v1, int v2) {
		int r1 = find(v1);
		int r2 = find(v2);
		if (r1 == r2) {
			return true;
		}
		parents[r1] = r2;
		return false;
	} // end of union

	private static int find(int v1) {
		if (v1 == parents[v1]) {
			return v1;
		}
		return parents[v1] = find(parents[v1]);
	} // end of find

} // end of class
