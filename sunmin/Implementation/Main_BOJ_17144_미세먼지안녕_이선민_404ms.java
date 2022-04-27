package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_17144_미세먼지안녕_이선민_404ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int time = Integer.parseInt(st.nextToken());
		int cleaner = -1; // 공기청정기 위쪽의 행
		int[][] room = new int[row][col];
		for (int i = 0, index = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < col; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				// 배열을 위에서부터 읽어오므로 먼저나오는 -1의 행이 cleaner에 들어가야함
				if (room[i][j] == -1 && cleaner == -1) {
					cleaner = i;
				}
			}
		}

		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };

		int rEnd = row - 1;
		int cEnd = col - 1;
		int[][] room2 = new int[row][col]; // 미세먼지 확산 후 방의 상태
		while (time-- > 0) {
			// 미세먼지를 새로 계산하기 위해 초기화
			for (int i = 0; i < row; i++) {
				Arrays.fill(room2[i], 0);
			}

			// 미세먼지 확산
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (room[i][j] != 0) { // 미세먼지가 있는 칸
						int tiny = room[i][j] / 5; // 확산되는 양
						int cnt = 0; // 확산된 방향의 개수
						for (int k = 0; k < dr.length; k++) { // 사방으로 확산
							int nr = i + dr[k];
							int nc = j + dc[k];
							// 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다
							if (nr < 0 || nr >= row || nc < 0 || nc >= col || room[nr][nc] == -1) {
								continue;
							}

							room2[nr][nc] += tiny;
							cnt++;
						}

						room2[i][j] += room[i][j] - tiny * cnt;
					}
				}
			} // 미세먼지 확산 완료

			// 공기청정기 작동
			int top = cleaner;
			int bottom = cleaner + 1;

			// 위쪽 : 반시계방향
			for (int i = top - 1; i > 0; i--) { // 아래로 당기기
				room2[i][0] = room2[i - 1][0];
			}

			for (int i = 0; i < cEnd; i++) { // 왼쪽으로 당기기
				room2[0][i] = room2[0][i + 1];
			}

			for (int i = 0; i < top; i++) { // 위로 당기기
				room2[i][cEnd] = room2[i + 1][cEnd];
			}

			for (int i = cEnd; i > 1; i--) { // 오른쪽으로 당기기
				room2[top][i] = room2[top][i - 1];
			}

			room2[top][1] = 0; // 공기청정기에서 부는 바람은 정화됨

			// 아래쪽 : 시계방향
			for (int i = bottom + 1; i < rEnd; i++) { // 위로 당기기
				room2[i][0] = room2[i + 1][0];
			}

			for (int i = 0; i < col - 1; i++) { // 왼쪽으로 당기기
				room2[rEnd][i] = room2[rEnd][i + 1];
			}

			for (int i = rEnd; i > bottom; i--) { // 아래로 당기기
				room2[i][cEnd] = room2[i - 1][cEnd];
			}

			for (int i = col - 1; i > 1; i--) { // 오른쪽으로 당기기
				room2[bottom][i] = room2[bottom][i - 1];
			}

			room2[bottom][1] = 0; // 공기청정기에서 부는 바람은 정화됨

			// 공기청정기까지 돌린 이후의 방 상태(room2)를 원본배열(room)에 깊은 복사
			for (int i = 0; i < room2.length; i++) {
				System.arraycopy(room2[i], 0, room[i], 0, room2[0].length);
			}

		} // end of while

		int ans = 0; // 남아있는 미세먼지의 양
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (room[i][j] > 0) {
					ans += room[i][j];
				}
			}
		}

		System.out.println(ans);

	} // end of main

}
