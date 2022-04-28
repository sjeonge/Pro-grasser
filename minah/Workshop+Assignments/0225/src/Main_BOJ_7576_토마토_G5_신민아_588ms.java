import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_7576_토마토_G5_신민아_588ms {
	static class Tomato { // 익은 토마토 좌표 저장용 클래스
		int r;
		int c;

		public Tomato(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int cSize = Integer.parseInt(st.nextToken());
		int rSize = Integer.parseInt(st.nextToken());

		boolean[][] tomatoBox = new boolean[rSize + 2][cSize + 2]; // 토마토가 익었는지 익지 않았는지 체크

		for (int i = 0; i < tomatoBox.length; i++) {
			Arrays.fill(tomatoBox[i], true); // 우선 가장자리에는 접근할 수 없으니 true로 모두 채움
		}

		List<Tomato> ripenTomatoes = new ArrayList<Tomato>(); // 이미 익은 토마토의 리스트
		int tomatoCount = 0; // 익혀야 하는 토마토의 개수
		for (int i = 1; i <= rSize; i++) { // 토마토 정보를 바로 boolean 배열에 받기
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 1; j <= cSize; j++) {
				int tomatoStatus = Integer.parseInt(st.nextToken()); // 입력으로 받은 토마토 정보
				
				if (tomatoStatus == 0) { // 익지 않은 토마토라면
					tomatoBox[i][j] = false; // 아직 익힐 수 있으므로 false처리
					tomatoCount++; // 익혀야 하는 토마토 개수 증가
				}
				else if (tomatoStatus == 1) // 이미 익은 토마토라면
					ripenTomatoes.add(new Tomato(i, j)); // 처음으로 익은 상태의 토마토 배열에 삽입
			}
		}
		
		if(ripenTomatoes.size() < 1) { // testcase에는 없지만 이미 익은 토마토가 없을 시 다른 토마토를 익힐 수 없으므로
			System.out.print(-1); // -1을 출력하고 끝냄
			return;
		}
		
		System.out.print(getDays(tomatoBox, ripenTomatoes, tomatoCount)); // 이미 익은 토마토가 있다면 토마토가 익는 시간을 계산하는 메소드 수행

	}
	
	// 오히려 totalTomatoCount를 세니까 더 걸리는..
	// 토마토가 익는 시간을 계산하는 메소드(BFS이용)
	// tomatoBox : 익은 토마토의 현황, firstRipen : 초기에 익은 토마토 정보들, totalTomatoCount : 익혀야하는 전체 토마토의 개수
	private static int getDays(boolean[][] tomatoBox, List<Tomato> firstRipen, int totalTomatoCount) {
		int answer = 0; // 전체를 익히기 위해 필요한 최소의 시간
		int curTomatoCount = 0; // 현재 익힌 토마토의 개수
		
		Queue<Tomato> tomato = new LinkedList<Tomato>(); // 익힌 토마토가 차례로 들어갈 queue

		for (int i = 0; i < firstRipen.size(); i++) { // 익힌 토마토는 여러 개 일 수 있으므로 그만큼 queue에 차례로 넣어줌
			tomato.offer(firstRipen.get(i));
		}

		int[] dr = { -1, 0, 1, 0 }; // 4방 탐색용 배열
		int[] dc = { 0, -1, 0, 1 };

		while (!tomato.isEmpty()) { // 토마토 큐가 비기 전까지

			int size = tomato.size(); // 토마토 큐의 현재 사이즈 : depth 계산용
			
			for (int s = 0; s < size; s++) { // 토마토 큐의 사이즈만큼 반복
				Tomato current = tomato.poll(); // 현재 선택한 토마토

				for (int i = 0; i < 4; i++) { // 델타 탐색
					if (!tomatoBox[current.r + dr[i]][current.c + dc[i]]) { // 델타로 이동한 다음 토마토가 익을 수 있는 경우
						tomato.offer(new Tomato(current.r + dr[i], current.c + dc[i])); // 토마토 큐에 다음 토마토를 넣어줌
						tomatoBox[current.r + dr[i]][current.c + dc[i]] = true; // 토마토가 익었다고 변경
						curTomatoCount++; // 익힌 토마토 개수 증가
					}
				}
			}
			answer++; // 하루가 지났으므로 1일 증가

		}

		if(curTomatoCount < totalTomatoCount) { // 모든 토마토가 다 익지 못한 경우에는 -1 출력
			return -1;
		}

		return answer - 1; // 마지막에 1이 더해지므로
	}

}
