import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Car {
	/* 자동차 
	 * 조건 : 자동차는 4명이 탑승 가능하며, 현재 속도를 가지고 있음, 최고 속도는 150까지이다.
	 * 
	 * 좌석별로 타는 기능 과 내리는 기능 엑셀 과 브레이크로 속도를조절한다. 속도는 10씩 증가 및 감소 가능하다.
	 * 
	 * 운전자가 없을 경우 엑셀 사용 안됨, 속도가 0이 아니면 탑승자가 내릴 수 없다.
	 * 
	 * 주행 중 탑승 불가능
	 */
		Scanner sc = new Scanner(System.in);
//		List<HashMap<String, Integer>> car = new ArrayList<HashMap<String, Integer>>();
		int[] car = {-1,-1,-1,-1};
		int now =0; //현재속도
		int cnt =4;  //빈자리
		boolean flag = true;
		
		public void run() {
			
		
		while(flag) {
			System.out.println("자동차 입니다.  (현재속도 "+now+")");
			System.out.println("1.타기\t2.내리기\t3.운전하기\t4.차량확인\t5.종료");
			String menu = sc.nextLine();
			
			switch(menu) {
			
			case "1": ride();
						break;
				
			case "2": off();
						break;
				
			case "3": drive();
						break;
					
//			case "4": check();
//						break;
			case "5": flag= false;
					System.out.println("종료합니다");
					break;
					default :
						System.out.println("잘못입력하셨습니다.");
				}
			}
		}
	
		public void ride() {
			
			if(now==0) {
				
					if(cnt>0) {
						System.out.println("탑승가능 합니다. 어디에 탑승하시겠습니까 ?");
						System.out.println("1.운전석\t2.조수석\t3.뒷자석1\t4.뒷자석2");
						String seat= sc.nextLine();
						
						int seatnum= Integer.parseInt(seat) -1; 
						car[seatnum]= 1; //탑승
						cnt--;
						
					}else {
						System.out.println("탑승자리가 없습니다.");
					}
			}
			else {
				System.out.println("먼저 브레이크를 밟아주세요");
			}
			
			if(car[0]==1) {
				System.out.println("현재 운전석에 사람이 타있습니다. 운전 가능합니다.");
			} else {
				System.out.println("현재 운전석에 사람이 없습니다.");
			}
		}
		
		public void off() {
			if(car[0]==1) {
				System.out.println("하차가능 합니다. 누구부터하차하시겠습니까 ?");
				if(now==0) {
					System.out.println("1.운전석\t2.조수석\t3.뒷자석1\t4.뒷자석2");
					String seat= sc.nextLine();
					
					int seatnum= Integer.parseInt(seat) -1; 
					car[seatnum]= -1; //탑승
					cnt++;
					
				}else {
					System.out.println("먼저 브레이크를 밟아주세요");
					
				}
		}
		else {
			System.out.println("운전자가 없습니다. 하차 불가능합니다.");
		}
	}//off메소드	
		
		
		public void drive() {//엑셀 브레이크, 10씩증가 감소
			if(car[0]==1) {
				System.out.println("운전가능합니다. 1.엑셀\t2.브레이크");
				String choice= sc.nextLine();
				switch(choice) {
				
				case "1": 
					if(now<150) {
						System.out.println("엑셀 밟습니다.");
						now+=10;
					}else {
						System.out.println("최고속도 150입니다. 브레이크를 밟아주세요");
					}
					break;
					
				case "2":
					if(now>0) {
						System.out.println("브레이크 밟습니다.");
						now-=10;
					}else {
							System.out.println("현재속도 "+now+"입니다.");
							System.out.println("탑승 또는 하차 가능합니다.");
					}
					break;
				}
			}else {
				System.out.println("운전자가 없습니다.");
			}
		}//drive 메소드
}