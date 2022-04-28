import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * A) 경사로는 높이가 항상 1이며, 길이는 L이다. 또, 개수는 매우 많아 부족할 일이 없다. 경사로는 낮은 칸과 높은 칸을 연결하며, 아래와 같은 조건을 만족해야한다.
 * 1. 경사로는 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접해야 한다.
 * 2. 낮은 칸과 높은 칸의 높이 차이는 1이어야 한다.
 * 3. 경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
 * 
 * B) 아래와 같은 경우에는 경사로를 놓을 수 없다.
 * 1. 경사로를 놓은 곳에 또 경사로를 놓는 경우
 * 2. 낮은 칸과 높은 칸의 높이 차이가 1이 아닌 경우
 * 3. 낮은 지점의 칸의 높이가 모두 같지 않거나, L개가 연속되지 않은 경우
 * 4. 경사로를 놓다가 범위를 벗어나는 경우
 */
public class Main_BOJ_14890_경사로_G3_신민아_104ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int size = Integer.parseInt(st.nextToken());
		int roadWidth = Integer.parseInt(st.nextToken());
		int[][] map = new int[size][size]; // 각 열을 검사할 수 있도록 저장하는 맵
		int[][] mapC = new int[size][size]; // 각 행을 검사할 수 있도록 저장하는 맵
		
		for(int i=0;i<size;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<size;j++) {
				map[i][j] = mapC[j][i] = Integer.parseInt(st.nextToken()); // 행과 열을 검색하기 위해 i,j를 바꿔서 두 맵에 한번에 저장
			}
		}
		
		System.out.print(getAvailableRoad(roadWidth, map, mapC));
	}
	
	// 맵에 경사로를 몇개 설치할 수 있는지 확인하는 메소드
	private static int getAvailableRoad(int roadWidth, int[][] map, int[][] mapC) {
		// 가로, 세로 길이 있으므로 *2
		int result = 2 * map.length;
		
		for(int i=0;i<map.length;i++) {
			if(!putRoad(roadWidth, map[i]))
				result--;
		
			if(!putRoad(roadWidth, mapC[i]))
				result--;
		}
		
		return result;
	}
	
	// 각 길에 경사로를 둘 수 있는지 확인하는 메소드
	private static boolean putRoad(int roadWidth, int[] map) {
		int beforeHeight = map[0]; // 이전의 높이
		
		boolean[] roadStatus = new boolean[map.length]; // 경사로를 뒀는지 체크하는 배열
		
		for(int i=1;i<map.length;i++) {
			if(map[i] == beforeHeight) continue; // 이전의 높이와 같으면 넘어감
			
			// 이전의 높이와 다르면
			if(Math.abs(map[i-1] - map[i]) > 1) return false; // 높이가 1 이상 차이나는 경우 경사로 설치 불가능
			
			if(beforeHeight < map[i]) { // 오르막 길인 경우
				int startPoint = i - roadWidth; // 오르막 길을 설치하는 시작점
				
				if(startPoint < 0) // 시작점이 0보다 작은 경우
					return false; 
				
				for(int j=startPoint;j<=i-1;j++) { // 실제로 경사로를 세우기
					if(map[startPoint] != map[j] || roadStatus[j]) // 경사로를 세우면서 높이가 달라지거나 이미 경사로를 세웠다면
						return false; // 세울수 없으므로 종료
					roadStatus[j] = true; // 세울 수 있다면 true처리
				}
			} 
			else { // 내리막 길을 설치해야 하는 경우
				if(i + roadWidth > map.length) // 범위 밖으로 벗어나면 설치 불가
					return false;
				
				for(int j=i;j<i+roadWidth;j++) { // 실제로 내리막길 경사로를 세우기
					if(map[i] != map[j] || roadStatus[j]) // 경사로를 세우면서 높이가 달라지거나 이미 경사로를 세운 경우
						return false;
					roadStatus[j] = true;
				}
			}			
			
			beforeHeight = map[i]; // 높이 변경 전 마지막 높이		
		}
		
		return true;
	}
	
}
