package Implementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_1755_숫자놀이_S4_이선민_80ms {
	static class Eng implements Comparable<Eng> {
		String s; // 영어로 바꾼 문자열
		int num; // 원래 숫자

		public Eng(String s, int i) {
			this.s = s; // 입력받은 문자열을 저장
			this.num = i; // 입력받은 숫자를 저장
		}

		@Override
		public int compareTo(Eng o) {
			return this.s.compareTo(o.s); // 사전순 정렬
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력값 읽어오기
		StringBuilder sb = new StringBuilder(); // 출력을 한번에 하기위한 스트링빌더
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 공백을 구분자로 하여 한줄 읽어오기
		int M = Integer.parseInt(st.nextToken()); // 시작 숫자
		int N = Integer.parseInt(st.nextToken()); // 끝나는 숫자

		List<Eng> eng = new ArrayList<Eng>(); // 영어로 변환한 문자열들을 담을 리스트
		for (int i = M; i <= N; i++) {
			String s = ""; // 십의자리와 일의자리 문자열을 합치기 위한 스트링
			if (i / 10 > 0) { // 두자리수라면
				s += trans(i / 10); // 십의 자리 변환
			}
			s += trans(i % 10); // 일의 자리 변환
			eng.add(new Eng(s, i)); // 리스트에 변환한 문자열과 원래 숫자 저장
		}

		Collections.sort(eng); // 사전순으로 정렬

		int index = 0; // 한줄에 10개씩 출력하기 위한 정수
		for (Eng eng2 : eng) {
			if (++index > 10) { // 10개를 출력했으면
				sb.append("\n").append(eng2.num).append(" "); // 다음줄로 넘어감
				index = 1; // 다음줄 출력 개수를 위해 초기화
			} else {
				sb.append(eng2.num).append(" "); // 숫자 하나 출력
			}
		}

		System.out.print(sb.toString()); // 스트링빌더에 쌓아둔 값 출력하기
	} // end of main

	private static String trans(int i) {
		switch (i) {
		case 1: // 숫자 1 변환
			return "one"; // one 반환
		case 2: // 숫자 2 변환
			return "two"; // two 반환
		case 3: // 숫자 3 변환
			return "three";	// three 반환
		case 4:
			return "four";
		case 5:
			return "five";
		case 6:
			return "six";
		case 7:
			return "seven";
		case 8:
			return "eight";
		case 9:
			return "nine";
		case 0:
			return "zero";
		}

		return null;
	} // end of trans

} // end of class
