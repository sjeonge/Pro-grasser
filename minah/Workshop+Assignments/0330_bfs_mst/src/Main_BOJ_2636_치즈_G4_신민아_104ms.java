import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 처음에 치즈가 몇개인지 확인 후 한 시간씩 빠질때마다 녹는 치즈를 세줌 BFS 이용!!
 */
public class Main_BOJ_2636_치즈_G4_신민아_104ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] cheeseMap = new int[R + 2][C + 2];
		int cheeseTotal = 0;

		for (int i = 1; i <= R; i++) {
			String s = br.readLine();
			for (int j = 1, index = 0; j <= C; j++, index += 2) {
				cheeseMap[i][j] = s.charAt(index);
				if (s.charAt(index) == '1') {
					cheeseTotal++;
				}
			}
		}

		int time = 0; // 총 소요시간
		int lastMeltCount = 0;
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		ArrayDeque<int[]> queue = new ArrayDeque<int[]>();

		while (cheeseTotal > 0) {
			time++;
			lastMeltCount = 0;

			boolean[][] visited = new boolean[R + 2][C + 2];
			int sr = 1;
			int sc = 1;

			queue.offer(new int[] { sr, sc });
			visited[sr][sc] = true;

			while (queue.size() > 0) {
				int[] cur = queue.poll();
				sr = cur[0];
				sc = cur[1];
				for (int i = 0; i < dr.length; i++) {
					int nr = sr + dr[i];
					int nc = sc + dc[i];

					if (cheeseMap[nr][nc] != 0 && !visited[nr][nc]) {
						if (cheeseMap[nr][nc] == '0') { // 공기인 경우에는 queue에다가 넣기
							queue.offer(new int[] { nr, nc });
						} else { // 치즈인 경우에는 공기로 바꾸고 치즈 녹인 개수 정하기
							cheeseMap[nr][nc] = '0';
							lastMeltCount++;
						}
						visited[nr][nc] = true;
					}
				}
			}
			cheeseTotal -= lastMeltCount;
		}

		System.out.print(time + "\n" + lastMeltCount);
	}

}
