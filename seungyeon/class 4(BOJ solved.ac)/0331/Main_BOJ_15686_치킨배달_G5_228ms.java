import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BOJ_15686_치킨배달_G5_228ms {
	private static int total_distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 도시 한변 크기 (2<=N<=50)
		int M = Integer.parseInt(st.nextToken()); // 최대 수익의 치킨집의 개수
		
		int[][] town = new int[N][N];

		
		ArrayList<int[]> house_pos = new ArrayList<int[]>();
		ArrayList<int[]> chicken_pos = new ArrayList<int[]>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				town[i][j] = Integer.parseInt(st.nextToken());
				if(town[i][j] == 1) {
					house_pos.add(new int[] {i, j});					
				} else if(town[i][j] == 2) {
					chicken_pos.add(new int[] {i, j});
				}
			}
		}
		
		total_distance = 10000;
		int c_size = chicken_pos.size();
		
		combination(0, 0, c_size-M, house_pos, chicken_pos, new boolean[c_size]);

		System.out.println(total_distance);
	}
	
	public static void combination(int start, int cnt, int n, ArrayList<int[]> house_pos, ArrayList<int[]> chicken_pos, boolean[] close) {
		if(cnt == n) {
			int distance = 0;
			
			for(int h=0, h_size=house_pos.size(); h<h_size; h++) {
				int temp_distance = 101;
				for(int c=0, c_size=chicken_pos.size(); c<c_size; c++) {
					if(!close[c]) {
						int[] h_pos = house_pos.get(h);
						int[] c_pos = chicken_pos.get(c);
						temp_distance = Math.min(temp_distance, Math.abs(h_pos[0]-c_pos[0]) + Math.abs(h_pos[1]-c_pos[1]));
					}
				}
				distance += temp_distance;
				if(total_distance<=distance) return; 
			}
			total_distance = Math.min(total_distance, distance);
		}
		
		for(int i=start, size=chicken_pos.size(); i<size; i++) { // 닫아야 되는 개수 
			close[i] = true; 
			combination(i+1, cnt+1, n, house_pos, chicken_pos, close);
			close[i] = false; 			
		}
	}
}
