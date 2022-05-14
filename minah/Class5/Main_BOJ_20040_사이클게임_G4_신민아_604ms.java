package Class5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// idea : disjoint set을 이용하여 union으로 합칠 수 없는 경우(부모가 같아지는 경우)에 사이클이 완성되는 것임
public class Main_BOJ_20040_사이클게임_G4_신민아_604ms {

	public static void main(String[] args) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		 
		 int[] parents = new int[Integer.parseInt(st.nextToken())]; // 부모가 누군지 찾는 배열
		 
		 for(int i=0;i<parents.length;i++) { // 자기 자신으로 초기화
			 parents[i] = i;
		 }
		 
		 int games = Integer.parseInt(st.nextToken()) + 1; // 실제 게임 판수(< 연산을 위해 +1을 미리 함)
		 
		 int endPoint = 0; // 사이클이 되는 게임의 수
		 
		 for(int i=1;i<games;i++) {
			 st = new StringTokenizer(br.readLine(), " ");
			 
			 if(union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), parents)) { // 시작점과 끝점을 연결할 수 없을 때
				 endPoint = i; // 게임의 수를 저장하고 나감
				 break;
			 }
			 
		 }
		 
		 System.out.print(endPoint);
		 
	}
	
	// 부모를 찾는 코드(by 재귀)
	private static int findParent(int current, int[] parents) {
		if(current == parents[current]) {
			return current;
		}
		
		return parents[current] = findParent(parents[current], parents);
	}
	
	// 점과 점이 선분으로 연결될 때 시작점을 집합의 대표로 두고 끝점을 시작점의 집합으로 편입
	private static boolean union(int start, int end, int[] parents) {
		int startRoot = findParent(start, parents);
		int endRoot = findParent(end, parents);
		
		if(startRoot == endRoot) // 부모가 같을 때 == 사이클이 만들어질 때
			return true; // 빠른 연산을 위해 true를 뱉음
		
		parents[endRoot] = startRoot;
		return false;
		
	}
	
}
