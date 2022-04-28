import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_1463_1로만들기_S3_신민아_108ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.parseInt(br.readLine());
		int[] opCounts = new int[number+1];
		
		for(int i=2;i<opCounts.length;i++) {
			int min = Integer.MAX_VALUE;
			
			if(i % 3 == 0)
				min = opCounts[i / 3];
			if(i % 2 == 0)
				min = Math.min(min, opCounts[i / 2]);
			
			min = Math.min(min, opCounts[i - 1]);
			
			opCounts[i] = min + 1;
		}
		
		System.out.print(opCounts[number]);
	}

}
