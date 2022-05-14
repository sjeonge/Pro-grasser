package Mathematics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17387_선분교차2_G2_이선민_76ms {

	/** 점의 좌표 */
	static class Point {
		long x, y;

		public Point(long x, long y) {
			super();
			this.x = x;
			this.y = y;
		}

	} // end of Point class

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Point[] p = new Point[4]; // 선분의 각 끝점 좌표
		p[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		p[1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		st = new StringTokenizer(br.readLine(), " ");
		p[2] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		p[3] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		if (isIntersect(p[0], p[1], p[2], p[3])) {
			System.out.print(1);
		} else {
			System.out.print(0);
		}
	} // end of main

	/**
	 * 두 선분이 교차하면 true, 아니면 false 반환
	 */
	private static boolean isIntersect(Point point1, Point point2, Point point3, Point point4) {
		int v12 = ccw(point1, point2, point3) * ccw(point1, point2, point4);
		int v34 = ccw(point3, point4, point1) * ccw(point3, point4, point2);

		if (v12 < 0 && v34 < 0) { // 두 선분이 서로 교차
			return true;
		} else if ((v12 == 0 && v34 < 0) || (v12 < 0 && v34 == 0)) { // 세 점이 일직선상에 있고 어느 점도 같지 않음
			return true;
		} else if (v12 == 0 && v34 == 0) { // 세 점 이상이 일직선 상에 있음
			// 두 점의 좌표가 겹쳐있는지
			return Math.min(point1.x, point2.x) <= Math.max(point3.x, point4.x)
					&& Math.min(point3.x, point4.x) <= Math.max(point1.x, point2.x)
					&& Math.min(point1.y, point2.y) <= Math.max(point3.y, point4.y)
					&& Math.min(point3.y, point4.y) <= Math.max(point1.y, point2.y);
		}
		return false;
	} // end of isIntersect

	private static int ccw(Point point1, Point point2, Point point3) {
		long dir = (point1.x * point2.y + point2.x * point3.y + point3.x * point1.y)
				- (point2.x * point1.y + point3.x * point2.y + point1.x * point3.y);
		if (dir > 0) { // ccw
			return 1;
		} else if (dir < 0) { // cw
			return -1;
		} else { // 일직선
			return 0;
		}
	} // end of ccw

} // end of class
