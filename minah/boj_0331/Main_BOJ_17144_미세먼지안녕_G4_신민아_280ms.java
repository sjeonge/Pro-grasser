package boj_0331;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// idea : 공기청정기의 바람이 바로 나오는 곳과 모서리라서 꺾이는 먼지는 옮겨지는 위치를 배열로 두어서 옮김
/*
 * 값을 나중에 일괄적으로 바꿔주는 포인트
 * 1) 공기 청정기의 바람이 나오는 곳은 = 0
 * 2) 모서리에 있어서 방향이 바뀌는 곳은 위치를 배열로 두어서 옮김
 * 
 * 결론) 1, 2의 위치에 해당하는 부분은 purify의 각 방향별로 옮겨주는 for문에서 옮기지 않고 나중에 일괄적으로 값을 대입
 * 
 */
public class Main_BOJ_17144_미세먼지안녕_G4_신민아_280ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int rSize = Integer.parseInt(st.nextToken());
		int cSize = Integer.parseInt(st.nextToken());
		int time = Integer.parseInt(st.nextToken());

		int[][] map = new int[rSize][cSize];
		int[][] tempMap = new int[rSize][cSize]; // 확산 시 값을 합치는 임시 배열
		int airPurifier = 0; // 공청기 위치
		
		// initialization
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				tempMap[i][j] = map[i][j];
								
				if (map[i][j] == -1) { // 공청기 위치 주입
					airPurifier = i;
				}
			}
		}

		int[] purifier = { airPurifier - 1, airPurifier}; // 공청기의 두 좌표
		int[] dr = { -1, 0, 1, 0 }; // 4방 탐색용 배열
		int[] dc = { 0, -1, 0, 1 };
		
		int[][] tempRC = {{ purifier[0], cSize-1 }, { 0, cSize-1 }, { 0, 0 }, // 모서리의 값을 저장하기 위해 모서리의 좌표 저장
							{ purifier[1], cSize-1 }, { rSize-1, cSize-1 }, { rSize-1, 0 } };
		int[][] nextRC = { { purifier[0] - 1, cSize - 1 }, { 0, cSize - 2 }, { 1, 0 }, // 모서리의 값이 이동하는 좌표
							{ purifier[1] + 1, cSize - 1 }, { rSize - 1, cSize - 2 }, { rSize - 2, 0 } };

		while (time-- > 0) { // 주어진 시간동안 열심히 공청기 가동
			spread(map, tempMap, purifier, dr, dc); // 확산
			purify(tempMap, purifier, tempRC, nextRC); // 공청기
			copy(map, tempMap); // 다음 시간에도 돌리기 위해 tempMap의 값을 map으로 복사
		}
		
		// 남은 먼지의 양 계산
		int sum = 2; // if문 쓰기 귀찮아서 공청기의 값(-2)까지 계산되므로 2로 초기화
		for(int i=0;i<rSize;i++) {
			for(int j=0;j<cSize;j++) {
				sum += map[i][j];
			}
		}
		
		System.out.print(sum);
	}
	
	
	// 확산 코드 : 원래의 map, 확산용 map, 공청기 위치, 4방 탐색용 배열
	private static void spread(int[][] map, int[][] tempMap, int[] purifier, int[] dr, int[] dc) {
		
		for (int i = 0; i < map.length; i++) { // 칸 by 칸 검색
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] < 1) // 먼지가 없거나 공청기면 확산할 먼지가 없으므로 다음으로 넘어감
					continue;

				int spreadAmount = map[i][j] / 5; // 확산되는 먼지의 양

				for (int d = 0; d < 4; d++) { // 4방 탐색을 통해 먼지 확산
					int nr = i + dr[d]; // 다음 위치
					int nc = j + dc[d];
					
					// 범위 안에 있거나 확산될 위치가 공청기가 아니라면 확산
					if (nr > -1 && nc > -1 && nr < map.length && nc < map[i].length && map[nr][nc] != -1) {
						tempMap[nr][nc] += spreadAmount; // 확산되는 먼지의 양을 다음에도 정확하게 계산하기 위해 tempMap에 확산
						tempMap[i][j] -= spreadAmount; // 그 후 진원지에 확산량을 빼줌
					}
				}

			}
		}
	}

	// 공기 정화 : 윗 공청기는 반시계방향, 아래 공청기는 시계방향 / 임시맵(복사를 한꺼번에 하기 위해 tempMap 사용), 공청기 위치, 모서리 위치 저장하는 배열, 모서리가 다음에 이동하는 위치를 저장한 배열
	private static void purify(int[][] tempMap, int[] purifier, int[][] tempRC, int[][] nextRC) {
		
		// 계산을 빨리 하기 위해 r과 c의 끝 좌표 미리 저장(lastR, lastC)
		int lc = tempMap[0].length - 1;
		int lr = tempMap.length - 1;
		
		// 모서리의 값 임시 저장 -> 모두 옮기고 난 후에 다시 값을 저장해줄 예정
		int[] tempAmount = new int[6];
		for(int i=0;i<6;i++) {
			tempAmount[i] = tempMap[tempRC[i][0]][tempRC[i][1]];
		}
		
		// 편의상 윗 공청기의 r좌표는 rA, 아래 공청기의 r좌표는 rB라고 지칭
		
		// → : 위, 아래 공청기로부터 1 ~ lc까지 오른쪽으로 이동(공청기에서 나오는 새로운 바람은 계산 x -> 추후에 0으로 바꿔줄 예정)
		for(int i=lc;i>1;i--) {
			tempMap[purifier[0]][i] = tempMap[purifier[0]][i-1];
			tempMap[purifier[1]][i] = tempMap[purifier[1]][i-1];
		}
			
		// ↑ : 위 공청기로 부터 rA - 2 좌표 ~ 0까지 위로 이동(rA - 1좌표는 → 이동할 때의 모서리(rA, lc)가 이동할 예정이라 변환 x) 
		for(int i=0;i<purifier[0]-1;i++) {
			tempMap[i][lc] = tempMap[i+1][lc];
		}
		
		// ↓ : 아래 공청기로부터 rB + 2 좌표 ~ lr까지 아래로 이동(rB + 1좌표도 → 이동할 때의 모서리(rB, lc)가 이동할 예정이라 변환 x) 
		for(int i=lr;i>purifier[1]+1;i--) {
			tempMap[i][lc] = tempMap[i-1][lc];
		}
		
		// ← : 위 아래 공청기로부터 lc - 2 ~ 0까지 왼쪽으로 이동(lc - 1 좌표는 이동할 때의 모서리(0, lc), (lr, lc)가 이동할 예정)
		for(int i=0;i<lc-1;i++) {
			tempMap[0][i] = tempMap[0][i+1];
			tempMap[lr][i] = tempMap[lr][i+1];
		}
		
		// ↓ : 위 공청기로부터 2 ~ rA - 2좌표까지 아래로 이동(1좌표는 모서리(0, 0)이 이동 + rA - 1은 공청기로 흡수)
		for(int i=purifier[0]-1;i>1;i--) {
			tempMap[i][0] = tempMap[i-1][0];
		}
		
		// ↑ : 아래 공청기로부터 rl - 2 ~ rB + 1좌표까지 위로 이동(lr - 1좌표는 모서리(lr, 0)가 이동 + rB + 1은 공청기로 흡수)
		for(int i=purifier[1]+1;i<lr-1;i++) {
			tempMap[i][0] = tempMap[i+1][0];
		}

		// 특수한 위치의 값 setting
		tempMap[purifier[0]][1] = 0; // 공기청정기로부터 깨끗한 공기가 나옴
		tempMap[purifier[1]][1] = 0;
		
		// 각 모서리에 있는 값을 방향전환 시켜줌
		for(int i=0;i<6;i++) {
			tempMap[nextRC[i][0]][nextRC[i][1]] = tempAmount[i];
		}
	}
	
	// temp값을 map으로 복사
	private static void copy(int[][] map, int[][] tempMap) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = tempMap[i][j];
			}
		}
	}

}
