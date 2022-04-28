import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_5643_키순서_D4_신민아_1723ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine()) + 1;
		int INF = 1000000;
		
		int[][] map;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int t=1;t<TC;t++) {
			int students = Integer.parseInt(br.readLine());
			map = new int[students + 1][students + 1];
			for(int i=1;i<map.length;i++) {
				for(int j=1;j<map.length;j++) {
					if(i != j) map[i][j] = INF; // 출발지 == 도착지가 아닌 경우는 INF로 초기화
				}
			}
			
			int edges = Integer.parseInt(br.readLine()); // 키의 대소비교를 아는 경우를 위한 초기화
			for(int i=0;i<edges;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
			
			searchByFloyd(map); // floyd로 모든 정점 간의 최소 이동 경로 찾기
			
			sb.append("#").append(t).append(" ").append(getAnswer(map, INF)).append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	// 정직한.. 플로이드..?
	private static void searchByFloyd(int[][] map) {
		for(int m=1;m<map.length;m++) { // 경유지 -> 출발지 -> 도착지를 기준으로 검색 : 경유지
			for(int start=1;start<map.length;start++) { // 출발지
				if(m == start) // 경유지와 출발지가 같으면 넘기기
					continue;
				
				for(int end=1;end<map.length;end++) { // 도착지
					if(m != end && start != end)// 경유지 or 출발점이 도착지와 같지 않다면
						map[start][end] = Math.min(map[start][end], map[start][m] + map[m][end]); // 더 작은 값 넣기
				}
			}
		}
	}
	
	// 검색 조건 : 단방향 그래프이므로 n -> m이 INF일 때 m -> n도 INF인지 검사
	private static int getAnswer(int[][] map, int INF) {
		int result = map.length - 1; // 학생 수만큼 세팅해줌 : INF 체크 시 맞지 않는다면 1씩 빼주고 다음 행 검사
		
		for(int i=1;i<map.length;i++) { // 1번 학생부터 차례로 검사
			for(int j=1;j<map.length;j++) {
				if(map[i][j] == INF && map[j][i] == INF ) { // 1번 학생부터 임의의 n번 학생까지 어떤 방향으로든 갈 수 없다면
					result--; // 이 학생은 순서를 모르므로 1빼주고
					break; // 다음 학생으로 넘어감
				}
			}
		}
		
		return result;
	}

}
