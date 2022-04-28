import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * xy[0] = A / xy[1] = B / xy[2] = C
 * xy[3] = D / xy[4] = E / xy[5] = F
 * xy[6] = G / xy[7] = H
 */
public class Main_BOJ_4105_유클리드_S2_신민아 {
	static class Point {
		double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String finishLine = "0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0";

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Point[] xy = new Point[8];
		String line;

		while (!finishLine.equals(line = br.readLine())) {
			st = new StringTokenizer(line, " ");
			for (int i = 0; i < 6; i++) {
				xy[i] = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			}
			
			double triArea = getTriArea(xy[3], xy[4], xy[5]);
			double abLength = getLineLength2Square(xy[0], xy[1]);
			double ahLength = triArea*triArea / abLength;
			
			
			sb.append(xy[6].x).append(" ").append(xy[6].y).append(" ") // GX, GY 출력
					.append(xy[7].x).append(" ").append(xy[7].y).append("\n"); // HX, HY 출력

		}

		System.out.print(sb.toString());
	}

	private static double getTriArea(Point d, Point e, Point f) {
		return 0.5 * Math.abs((d.x*e.y + e.x*f.y + f.x*d.y) - (e.x*d.y + f.x*e.y + d.x*f.y));
	}
	
	private static double getLineLength2Square(Point a, Point b) {
		return Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2);
	}
	
	private static double[] get1DimensionFunction(Point a, Point b) {
		// func[0] = 기울기 , func[1] = y절편
		double[] func = new double[2];
		
		if(a.x == b.x) {
			
			return func;
		}
		
		return func;
	}

}
