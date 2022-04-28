import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// idea 1 : 벽부수고이동하기와 같이 몇번 점프를 뛰었는지 카운팅하기 위해 visited배열을 3차원으로 둔다
// idea 2 : BFS를 통해 이동하는 노드를 queue에 넣어준다
// idea 3 : 답을 구했다면 또 구할 필요는 없음! 쿨하게 while문으로 빠져나오면 됨(뒤에 나오는 node는 무조건 뛴 횟수가 더 많거나 같기 때문에)
public class Main_BOJ_1600_말이되고픈원숭이_G4_신민아_576ms {
	static class Node {
		int r; // r좌표
		int c; // c좌표
		int count; // 몇번 이동했는지
		int jump; // 점프를 몇번 뛰었는지

		public Node(int r, int c, int count, int jump) {
			this.r = r;
			this.c = c;
			this.count = count;
			this.jump = jump;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int maxJumpCount = Integer.parseInt(br.readLine()); // 최대로 뛸 수 있는 점프 수
		
		// 주의!! R과 C가 반대로 나오므로 조심
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int C = Integer.parseInt(st.nextToken()); // 세로 길이
		int R = Integer.parseInt(st.nextToken()); // 가로 길이
		
		char[][] map = new char[R][C]; // 맵
		for(int i=0;i<map.length;i++) { // 맵의 정보 넣어주기
			String str = br.readLine();
			for(int j=0, index=0;j<map[i].length;j++, index += 2) {
				map[i][j] = str.charAt(index);
			}
		}
		
		boolean[][][] visited = new boolean[R][C][maxJumpCount+1]; // 방문 배열 : 점프는 0번부터 maxJumpCount까지 뛸 수 있으므로 +1을 해줌
		
		ArrayDeque<Node> queue = new ArrayDeque<Node>();
		queue.offer(new Node(0, 0, 0, 0)); // 시작점 queue에 넣기
		visited[0][0][0] = true; // 시작점 방문처리
		
		int totalCount = -1; // 현재의 카운트(초기값 : 도착점에 도착이 불가능할 때의 값)
		
		int[] drJump = {-2, -2, -1, -1, 1, 1, 2, 2}; // 점프 시 r,c좌표 변경
		int[] dcJump = {-1, 1, -2, 2, -2, 2, -1, 1};
		int[] dr = {-1, 1, 0, 0}; // 그냥 걸을 때 r,c좌표 변경
		int[] dc = {0, 0, -1, 1};
		
		while(queue.size() > 0) { // !queue.isEmpty대신 사용
			Node node = queue.poll(); // 현재 위치 뽑기
			int curR = node.r;
			int curC = node.c;
			int curCount = node.count;
			int curJump = node.jump;
			
			if(curR == R-1 && curC == C-1) { // 현재 위치가 도착점이라면
				totalCount = curCount; // 값을 갱신하기
				break; // 이 이후에 나오는 노드는 반드시 뛰는 횟수가 지금보다 많으므로 쿨하게 나오기
			}
			
			// 점프 시에 체크
			if(curJump < maxJumpCount) {
				for(int i=0;i<drJump.length;i++) { // 점프할 때의 8방(?) 탐색
					int nr = curR + drJump[i]; // 점프 후 좌표
					int nc = curC + dcJump[i];
					if(isNotAvailable(nr, nc, curJump+1, visited, map)) { // 점프가 가능한지 여부
						continue; // 불가능 시 넘김
					}
					queue.offer(new Node(nr, nc, curCount+1, curJump+1)); // 가능하면 queue에 node넣기
					visited[nr][nc][curJump+1] = true; // 방문쳌
				}
			}
			
			// 그냥 4방 탐색 체크
			for(int i=0;i<dr.length;i++) {
				int nr = curR + dr[i];
				int nc = curC + dc[i];
				if(isNotAvailable(nr, nc, curJump, visited, map)) { // 근접한 곳으로 이동 가능 여부
					continue;
				}
				queue.offer(new Node(nr, nc, curCount+1, curJump)); // 가능할 시 queue에 노드 넣기
				visited[nr][nc][curJump] = true; // 방문쳌
			}
			
		}
		System.out.println(totalCount); // totalCount가 갱신 되었다면 갱신된 값, 갱신되지 않았다면 -1
	}
	
	private static boolean isNotAvailable(int nr, int nc, int jump, boolean[][][] visited, char[][] map) {
		// 1. 각 좌표가 배열 밖으로 나갔는지 체크(1 ~ 4)
		// 2. 이미 방문했는지
		// 3. 다음 이동할 좌표에 장애물이 있어서 이동이 불가능한지
		return (nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length || visited[nr][nc][jump] || map[nr][nc] == '1');
	}

}
