import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_JO_2577_회전초밥_신민아_508ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[] sushi = new int[Integer.parseInt(st.nextToken())];
		int kindCount = Integer.parseInt(st.nextToken());
		
		int[] kinds = new int[kindCount + 1];
		int k = Integer.parseInt(st.nextToken()); // 연속으로 먹을 때 할인되는 개수
		int coupon = Integer.parseInt(st.nextToken());
		int curCounts = 0;
		int answer = 0;
		
		for(int i=0;i<sushi.length;i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		// 첫번째 부터 먹을 때의 가짓수 계산(최초 1회에 한해 계산) : 0부터 k-1까지
		for(int i=0;i<k;i++) {
			if(kinds[sushi[i]]++ == 0) {
				curCounts++;
			}	
		}
		
		answer = curCounts;
		
		if(kinds[coupon] == 0) {
			answer++;
		}
		
		// 시작점이 1부터 sushi-k일때까지
		// 마지막 점을 기준으로는 k부터 배열의 마지막 스시까지
		for(int start=0,end=k;end<sushi.length;start++, end++) {
			boolean isCouponContained = false;
			
			if(kinds[sushi[start]]-- == 1)
				curCounts--;
			
			if(kinds[sushi[end]]++ == 0)
				curCounts++;
			
			if(kinds[coupon] == 0) {
				isCouponContained = true;
			}
			
			answer = Math.max(answer, isCouponContained ? curCounts + 1 : curCounts);
			
			if(answer == kindCount) {
				System.out.print(answer);
				return;
			}
		}
		
		// 마지막 점이 0부터 k-2까지
		// 시작점은 length-1까지
		// 코드 중복되는게 좀..약혐이지만..편두통으로 인해 여기까지
		int last = k-1;
		for(int start=sushi.length-k,end=0;end<last;start++, end++) {
			boolean isCouponContained = false;
			if(kinds[sushi[start]]-- == 1)
				curCounts--;
			
			if(kinds[sushi[end]]++ == 0)
				curCounts++;
			
			if(kinds[coupon] == 0)
				isCouponContained = true;
				
			answer = Math.max(answer, isCouponContained ? curCounts + 1 : curCounts);
			
			if(answer == kindCount) {
				System.out.print(answer);
				return;
			}
		}
		
		System.out.print(answer);
	}

}
