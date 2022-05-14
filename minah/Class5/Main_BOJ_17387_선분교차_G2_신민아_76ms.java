package Class5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// idea : ccw 알고리즘을 이용!(3점을 이용하여 만든 2개의 선분이 각각 시계방향, 반시계방향인지 체크)
// 		  두 선분이 만나려면 방향이 달라야함!! -> key Point!
// another idea : GCD 알고리즘을 이용하여 선분 L1의 1차 방정식을 세우고 L2의 1차 방정식을 세워서 교차점을 구한 후 교차점이 L1과 L2 에 모두 속해있는지 본다
// 				   만약 두 선분의 방정식이 같다면, 겹치는 지점이 있는지 확인한다(isOutRange 메소드 그대로 사용)
public class Main_BOJ_17387_선분교차_G2_신민아_76ms {

	public static void main(String[] args) throws Exception	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long[][] points = new long[4][2]; // ccw를 계산하는 과정에서 overflow가 날수 있기 때문에 자료형도 미리 long으로 준비
		StringTokenizer st;
		for(int i=0;i<=2;i+=2) { // 차례로 좌표 입력
			st = new StringTokenizer(br.readLine(), " ");
			
			points[i][0] = Long.parseLong(st.nextToken());
			points[i][1] = Long.parseLong(st.nextToken());
			points[i+1][0] = Long.parseLong(st.nextToken());
			points[i+1][1] = Long.parseLong(st.nextToken());			
		}
		
		System.out.print(isMeet(points));
		
	}
	
	private static int isMeet(long[][] points) {		
		int ccw012 = ccw(points[0], points[1], points[2]); // 0번 지점 -> 1번 지점 -> 2번 지점으로 가는 직선의 방향
		int ccw013 = ccw(points[0], points[1], points[3]); // 0 -> 1 -> 3번 지점으로 가는 직선의 방향
		int ccw230 = ccw(points[2], points[3], points[0]); // 2 -> 3 -> 0번 지점으로 가는 직선의 방향
		int ccw231 = ccw(points[2], points[3], points[1]); // 2 -> 3 -> 1번 지점으로 가는 직선의 방향
		
		if(ccw012 * ccw013 > 0 || ccw230 * ccw231 >  0 || isOutRange(points)) // 만나지 않은 경우 + 서로 범위 밖에 있는 경우
			return 0;
		
		return 1;
		
	}
	
	// ccw 알고리즘
	private static int ccw(long[] p0, long[] p1, long[] p2) {
		long result = (p0[0] * p1[1]) + (p1[0] * p2[1]) + (p2[0] * p0[1])
						- ((p1[0] * p0[1]) + (p2[0] * p1[1]) + (p0[0] * p2[1]));
		
		if(result < 0)
			return -1;
		
		else if(result > 0)
			return 1;
		
		return 0;
	}
	
	// 일직선 상에 있을 때 두 점이 겹치는지 확인하는 메소드
	private static boolean isOutRange(long[][] points) {
		return Math.min(points[0][0], points[1][0]) > Math.max(points[2][0], points[3][0])
					|| Math.min(points[2][0], points[3][0]) > Math.max(points[0][0], points[1][0])
					|| Math.min(points[0][1], points[1][1]) > Math.max(points[2][1], points[3][1])
					|| Math.min(points[2][1], points[3][1]) > Math.max(points[0][1], points[1][1]);
	}
}
