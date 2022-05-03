package may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20040_사이클게임_G4_양소정_616ms {
	//static int N;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		int ans=0;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 사이클이 형성되는지 확인
			if(!union(a, b)) {
				ans = i;
				break;
			}
		}
		System.out.println(ans);
		
	}
	
	//a의 집합 찾기 :a의 대표자 찾기
	public static int findSet(int a) {
		if(a==parents[a]) return a;
		return parents[a] = findSet(parents[a]); //path compression
	}
	//a, b 두 집합 합치기
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		
		parents[bRoot]= aRoot;
		return true;
	}

}
