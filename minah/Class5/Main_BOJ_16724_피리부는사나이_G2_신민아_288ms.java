package Class5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// idea : 2차원 배열은 생각하기 어려우므로 1차원 배열로 바꾸어서 FLAT하게 처리(i,j)는 (i*cSize + j)로 생각!
// DFS + 방문 체크는 사이클의 개수를 세기 힘드므로 disjoint만 이용하면 된다!
// 집합의 개수 = disjoint 먼저 생각해보기(+규칙에서 사이클이 발견된다면 더더욱 disjoint 사용)
public class Main_BOJ_16724_피리부는사나이_G2_신민아_288ms {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int rSize = Integer.parseInt(st.nextToken()); // 가로 크기
		int cSize = Integer.parseInt(st.nextToken()); // 세로 크기
		
		int[][] map = new int[rSize][cSize]; // map : DURL을 차례대로 0123로 변환하여 계산 예정(dr, dc 용이한 계산을 위해)
		int[] parents = new int[rSize * cSize]; // 부모를 찾을 수 있는 배열
		
		int[] dr = {1, -1, 0, 0}; // 이동용 배열
		int[] dc = {0, 0, 1, -1};
		
		String line = "";
		for(int i=0;i<rSize;i++) {
			line = br.readLine();
			for(int j=0;j<cSize;j++) {
				map[i][j] = switchChar(line.charAt(j)); // DURL을 0123로 변환하여 map에 저장
			}
		}
		
		// init parents
		for(int i=0;i<parents.length;i++) {
			parents[i] = i;
		}
		
		// disjoint는 union 될 때마다 1씩 빼주면 계산이 빠르므로 미리 전체 원소의 개수만큼 answer에 들고있는다
		int answer = rSize * cSize;
		
		// rSize * cSize 만큼 일일히 계산
		for(int i=0;i<rSize;i++) {
			for(int j=0;j<cSize;j++) {
				int current = i * cSize + j; // 현재 위치의 parents 인덱스 : i * cSize + j
				int next = (i + dr[map[i][j]]) * cSize + (j + dc[map[i][j]]); // 다음 위치의 parents 인덱스 : i와 j에 각각 dr[map], dc[map]만큼 더한 후 i에 현재 위치 인덱스처럼 cSize 곱
				if(union(current, next, parents)) // 현재 위치가 이전 위치랑 이어진 경우에는 집합이 1개가 되므로
					answer--; // 집합이 1개 빠지므로 --처리
			}
		}
		System.out.print(answer);
	}
	
	// 기존 union 코드
	private static boolean union(int a, int b, int[] parents) {
		int aRoot = findSet(a, parents);
		int bRoot = findSet(b, parents);
		
		if(aRoot == bRoot) {
			return false;
		}
		
		parents[bRoot] = aRoot; // 집합에 들어가기
		return true;
	}
	
	// 부모 찾기 코드
	private static int findSet(int a, int[] parents) {
		if(a == parents[a])
			return a;
		
		return parents[a] = findSet(parents[a], parents);
	}
	
	// DURL -> 0123
	private static int switchChar(char c) {
		switch(c) {
			case 'D' :
				return 0;
			case 'U' :
				return 1;
			case 'R' :
				return 2;
			default :
				return 3;
		}
	}

}
