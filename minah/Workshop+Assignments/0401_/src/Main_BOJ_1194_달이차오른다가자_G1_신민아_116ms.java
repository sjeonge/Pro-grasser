import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// idea : 벽부수기와 비슷하게 3차원배열을 두어 열쇠를 얻은 정보 별로 visit 체크 필요
public class Main_BOJ_1194_달이차오른다가자_G1_신민아_116ms {
	static class Node {
		int r, c, count, key;
		
		public Node() { }
		
		public Node(int r, int c, int count, int key) {
			this.r = r;
			this.c = c;
			this.count = count;
			this.key = key;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int rSize = Integer.parseInt(st.nextToken());
		int cSize = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[rSize][cSize];
		
		Node cur = new Node();
		for(int i=0;i<rSize;i++) {
			String line = br.readLine();
			for(int j=0,index=0;j<cSize;j++,index++) {
				map[i][j] = line.charAt(index); // 맵 정보 넣기
				if(map[i][j] == '0') { // 만약 사용자 위치면
					cur = new Node(i, j, 0, 0); // 현재 위치를 노드로 생성
				}
			}
		}
		
		boolean[][][] visited = new boolean[64][rSize][cSize]; // 얻은 열쇠 별로 visit 체크하기위해 3차원배열 생성(열쇠는 a~f까지이므로 총 64가지의 경우의 수 존재)
		Queue<Node> queue = new ArrayDeque<Node>(); // 탐색용 큐 생성
		queue.offer(cur); // 시작점 삽입
		visited[0][cur.r][cur.c] = true; // 열쇠가 없는 채로 시작하므로 해당 지점 방문 체크
		
		int minCount = -1; // 가장 빨리갈 수 있는 방법
		int[] dr = {-1, 1, 0, 0}; // 탐색용 델타 배열
		int[] dc = {0, 0, -1, 1};
		
		while(queue.size() > 0) { // 큐가 사라질 때까지
			cur = queue.poll(); // 현재 위치 뽑기
			
			if(map[cur.r][cur.c] == '1') { // 출구일 경우
				minCount = cur.count; // 카운트 갱신 후 종료
				break;
			}
			
			for(int i=0;i<4;i++) { // 델타마다 탐색
				int nr = cur.r + dr[i]; // 다음 위치
				int nc = cur.c + dc[i];
				
				if(notAvailablePath(nr, nc, map, cur.key, visited)) { // 갈 수 없는 경로인 경우
					continue; // 다음 델타로 넘김
				}
				
				int newKey = 0; // 이번 위치에서의 열쇠 정보
				if(map[nr][nc] >= 'a' && map[nr][nc] <'g') { // 현재 자리에 열쇠가 있다면
					newKey = 1 << (map[nr][nc] - 'a'); // 열쇠 정보 업뎃
				}
				
				newKey = newKey | cur.key; // 기존의 키와 연산해 줌
				queue.offer(new Node(nr, nc, cur.count+1, newKey)); // 큐에 위치정보 삽입
				visited[newKey][nr][nc] = true; // 방문 체크
			}
		}
		
		System.out.print(minCount);
	}
	
	// 갈 수 있는 곳인지 체크
	private static boolean notAvailablePath(int nr, int nc, char[][] map, int key, boolean[][][] visited) {
		return (nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length // 배열의 범위 안인지 
					|| map[nr][nc] == '#' || visited[key][nr][nc]  // 가려는 곳이 벽이거나 이미 열쇠 소유 상태가 같을 때 방문했거나
					|| hasNotProperKey(map[nr][nc], key)); // 갖고있는 열쇠로 문을 열 수 없을 경우 못감
	}
	
	private static boolean hasNotProperKey(char curChar, int key) { // 열쇠로 문을 열수있는지 확인
		return (curChar >= 'A' && curChar < 'G') && ((1 << (curChar - 'A') & key) == 0); // 현재 위치가 문이고 열쇠를 가지고 있어야함
	}

}
