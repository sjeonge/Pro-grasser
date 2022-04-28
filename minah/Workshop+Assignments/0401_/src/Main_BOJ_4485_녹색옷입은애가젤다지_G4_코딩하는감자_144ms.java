import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

// idea : 클래스를 만들어서 관리하기
public class Main_BOJ_4485_녹색옷입은애가젤다지_G4_코딩하는감자_144ms {
	static class Node implements Comparable<Node>{ // 각 위치에서의 링크의 상태
		int r, c, amount;

		public Node(int r, int c, int amount) {
			this.r = r;
			this.c = c;
			this.amount = amount;
		}

		@Override
		public int compareTo(Node o) {
			return amount - o.amount;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] map;
		StringBuilder sb = new StringBuilder();
		
		int t = 0;
		int size = 0;
		
		while((size = Integer.parseInt(br.readLine())) != 0 ) { // 사이즈가 0일때까지 값을 받음
			t++;
			map = new int[size][size];
			
			for(int i=0;i<size;i++) { // 입력
				String line = br.readLine();
				for(int j=0, index=0;j<size;j++, index+=2) {
					map[i][j] = line.charAt(index) - '0';
				}
			}
			
			int answer = dijkstra(map); // 다익스트라로 값 가져오기
			
			sb.append("Problem ").append(t).append(": ").append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	private static int dijkstra(int[][] map) {
		int[] dr = {-1, 1, 0, 0}; // 4방탐색 델타배열
		int[] dc = {0, 0, -1, 1};
		int[][] dMap = new int[map.length][map.length]; // 4방 탐색으로 진행하기 때문에 다익스트라를 2차원 배열로 둠(?)
		PriorityQueue<Node> queue = new PriorityQueue<Node>(); // PQueue 생성
		
		for(int i=0;i<map.length;i++) { // 갱신 조건이 이전 값이 갱신될 값보다 커야하기 때문에 큰 값으로 초기화
			Arrays.fill(dMap[i], Integer.MAX_VALUE);
		}
		
		dMap[0][0] = map[0][0]; // 출발점은 0, 0 고정이므로 초기화
		queue.offer(new Node(0, 0, dMap[0][0])); // 출발
		
		while(queue.size() > 0) {
			Node current = queue.poll(); // 현재 위치
			
			// 4방 탐색을 하며 queue에 넣어줌
			for(int i=0;i<4;i++) {
				int nr = current.r + dr[i]; // 4방 탐색을 통한 다음 위치
				int nc = current.c + dc[i];
				
				if(isInvalidIndex(nr, nc, map.length)) continue; // 배열 밖을 나가면 그만 둠
				
				int nextAmount = dMap[current.r][current.c] + map[nr][nc]; // 갱신 될 수도 있는 값 저장
				if(dMap[nr][nc] > nextAmount) { // 새로운 값이 더 작으면
					dMap[nr][nc] = nextAmount; // 갱신 후
					queue.offer(new Node(nr, nc, dMap[nr][nc])); // offer
				}
				
			}
		}
		
		return dMap[map.length-1][map.length-1]; // 도착점 값 꺼내기
	}
	
	// nr, nc가 배열 밖을 나갔는지 확인하는 메소드
	private static boolean isInvalidIndex(int nr, int nc, int size) {
		return (nr < 0 || nc < 0 || nr >= size || nc >= size);
	}

}
