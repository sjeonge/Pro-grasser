import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1149_RGB거리_S1_신민아_88ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int houses = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int[][] index = {{1, 2}, {0, 2}, {0, 1}};
		int[] min = new int[3];
		int[] cur = new int[3];
				
		for(int i=0;i<houses;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j=0;j<3;j++) {
				cur[j] = Integer.parseInt(st.nextToken()) + Math.min(min[index[j][0]], min[index[j][1]]);
			}
						
			for(int j=0;j<3;j++) {
				min[j] = cur[j];
			}
		}
		
		System.out.print(Math.min(min[2], Math.min(min[0], min[1])));
	}

}
