import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_1953_탈주범검거_신민아_125ms {
	static class Node { // r,c좌표와 해당 좌표에 도달하는 가장 빠른 시간을 기록하는 class
		int r, c, time;

		public Node(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine()) + 1;

		StringTokenizer st;
		char[][] map;
		Map<Character, boolean[]> pipe = getPipeInfo(); // 파이프 별로 이동할 수 있는 좌표의 값을 저장

		for (int t=1;t<TC;t++) {
			st = new StringTokenizer(br.readLine(), " ");

			int rSize = Integer.parseInt(st.nextToken()); // 세로 길이
			int cSize = Integer.parseInt(st.nextToken()); // 가로 길이
			map = new char[rSize][cSize]; // 맵 초기화

			int startR = Integer.parseInt(st.nextToken()); // 시작 지점
			int startC = Integer.parseInt(st.nextToken());
			int finalTime = Integer.parseInt(st.nextToken()); // 관찰 시간

			// map information initialization
			for (int i = 0; i < rSize; i++) {
				String line = br.readLine();
				for (int j = 0, index = 0; j < cSize; j++, index += 2) {
					map[i][j] = line.charAt(index);
				}
			}

			// output
			sb.append("#").append(t).append(" ").append(getAvailAreaByBFS(startR, startC, finalTime, map, pipe))
					.append("\n");
		}

		System.out.print(sb.toString());
	}

	// BFS를 통해 특정 시간까지 있을 수 있는 위치의 카운트틀 세는 메소드
	private static int getAvailAreaByBFS(int startR, int startC, int finalTime, char[][] map, Map<Character, boolean[]> pipe) {
		
		Queue<Node> queue = new ArrayDeque<Node>();
		boolean[][] visited = new boolean[map.length][map[0].length]; // visit check
		
		queue.offer(new Node(startR, startC, 1)); // 맨홀 아래로 내려가는 시간이 1부터 시작
		visited[startR][startC] = true; // 맨홀로 내려가는 부분 체크
		int areaCount = 1; // 맨홀로 내려간 곳도 있을 수 있는 공간이므로 1로 시작
		
		int[] dr = {-1, 1, 0, 0}; // 4방 탐색용 델타 배열
		int[] dc = {0, 0, -1, 1};
		
		// 더 이상 갈 수 없는 곳까지 도달하면 나가기
		while(queue.size() > 0) {
			Node cur = queue.poll();
			
			// BFS이므로 현재 시각이 최종 시각보다 크다면 그 이후에 오는 위치들도 시각이 커지므로 나가기
			if(cur.time > finalTime) break;
			
			// 4방 탐색
			for(int d=0;d<4;d++) {
				if(pipe.get(map[cur.r][cur.c])[d]) { // 현재 위치에서 다음 델타로 이동 가능하다면
					int nr = cur.r + dr[d]; // 다음 위치의 r,c좌표 먼저 세팅
					int nc = cur.c + dc[d];
					
					// 배열 밖이거나 이미 방문했거나 파이프가 없는 곳이라면 다음 델타 탐색
					if(isNotAvailable(nr, nc, map, visited)) continue;
						
					int nextDelta = getInteractionPipeIndex(d); // 다음에 이동할 곳이랑 파이프가 연결 되어있는지 확인하는 다음 델타
					
					if(pipe.get(map[nr][nc])[nextDelta]) { // 연결 되어있다면
						queue.offer(new Node(nr, nc, cur.time+1)); // queue에 offer
						visited[nr][nc] = true; // visit 체크
						
						if(cur.time < finalTime) areaCount++; // cur.time이 최종 시간 전이라면 갈 수 있는 지역 개수 증가
					}
				}
			}	
		}
		
		return areaCount;
	}

	// 파이프가 현재 위치랑 다음 위치랑 연결되어있는지 보기 위해 델타값을 이용하여 확인
	// 현재 위치 -> 다음 위치
	// 상 -> 하 : 0 -> 1 / 하 -> 상 : 1 -> 0 / 좌 -> 우 : 2 -> 3 / 우 -> 좌 : 3 -> 2
	private static int getInteractionPipeIndex(int delta) {
		return delta % 2 == 0 ? delta + 1 : delta - 1;
	}
	
	// 배열 밖을 나갔거나 이미 방문했거나 갈수 없는 곳(파이프가 없어 이동이 풀가능 한 곳)인지 체크
	private static boolean isNotAvailable(int nr, int nc, char[][] map, boolean[][] visited) {
		return ((nr < 0) || (nr >= map.length) || (nc < 0) || (nc >= map[0].length) || visited[nr][nc]
				|| map[nr][nc] == '0');
	}

	// 파이프 별로 어디를 이동할 수 있는지 정보를 담는 map을 리턴
	// boolean 내의 순서 : 상 하 좌 우
	private static Map<Character, boolean[]> getPipeInfo() {
		Map<Character, boolean[]> pipe = new HashMap<Character, boolean[]>();

		pipe.put('0', new boolean[4]);
		pipe.put('1', new boolean[] { true, true, true, true });
		pipe.put('2', new boolean[] { true, true, false, false });
		pipe.put('3', new boolean[] { false, false, true, true });
		pipe.put('4', new boolean[] { true, false, false, true });
		pipe.put('5', new boolean[] { false, true, false, true });
		pipe.put('6', new boolean[] { false, true, true, false });
		pipe.put('7', new boolean[] { true, false, true, false });

		return pipe;
	}

}
