import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class Stock {
/*
 * 모의 주식
 * 날짜 변경 시 등락률 random추출
 * -5% + 5%
 * 등락률에 따라 금액변동
 * 보유내역은 개수,현재가, 총자산가치제공
 * 구매시 수수료  1%  판매시 수수료 4% 100원 -> 101원(수수료 포함해 구매해야한다.)
 * 시작금액 500원
 * 삼성 100원, 카카오 80원 넷플릭스 150
 */
		List<HashMap<String,Double>> mystock = new ArrayList<HashMap<String,Double>>();
		HashMap<String, Double> a = new HashMap<String, Double>();
		String[] com= {"삼성","카카오","넷플릭스"};
		double[] price= {100.0, 80.0, 150.0};
		Double amount = 500.0;
		double low=80.8;
		int[] stockcnt= {0,0,0};
	
		double[] up_down =new double[3];
		Scanner sc = new Scanner(System.in);
		
		
	public void run() {
		boolean flag = true;

		while (flag) {
			System.out.println("메뉴를 선택하시오. (보유금액 :" + Math.floor(amount) + ")");
			System.out.println("1.주식구매\t2.주식판매\t3.다음날\t4.주식보유내역\t5.종료");
			String menu = sc.nextLine();
			switch (menu) {
			case "1":
					buy();
					break;
			case "2":
					sell();
					break;
			case "3": tmrw();
					break;
			case "4": status();
					break;
			case "5":
				System.out.println("종료합니다.");
				flag = false;
				break;
			default:
				System.out.println("잘못입력하셨습니다.");
			}
		}
	}
	public void buy() { // 구매/최소금액

		double fee = 0.01;
		int cnt = 0;
			if (amount < low) {
			System.out.println("금액이 부족합니다.");
			}else {
			System.out.println("무슨 종목을 구매하시겠습니까?" + "(보유금액 :" + Math.floor(amount) + ")");
				for (int i = 0; i < com.length; i++) {
					System.out.print((i + 1) + "." + com[i] + " 종목 금액 :" + price[i]);
					System.out.println();
			}
			String choice = sc.nextLine();
			
		
				if(amount > price[Integer.parseInt(choice)-1]) {
					a.put(com[Integer.parseInt(choice)-1], price[Integer.parseInt(choice)-1]);
					mystock.add(a);
					stockcnt[Integer.parseInt(choice)-1]++;
					amount -=(price[Integer.parseInt(choice)-1]+(Integer.parseInt(choice)*fee));
					System.out.println(com[Integer.parseInt(choice)-1]+"주주가 된 것을 축하드립니다.");
				}else {
					System.out.println("금액이 부족해 구매 할 수 없어요");
			
			}
				for (int i = 0; i < com.length; i++) {
					System.out.println("현재" + com[i] + "주식 개수 :" + stockcnt[i]);
			}
		}
	}//buy 메소드 종료	
		
	public void sell() {
		double sellfee = 0.04; // 판매수수료
		int cnt = 0;
		if (mystock.size() <= 0) {
			System.out.println("판매할 주식이 없습니다.");
		} else {
			System.out.println("무슨 종목을 판매하시겠습니까?" + "(보유금액 :" + Math.floor(amount) + ")");
			for (int i = 0; i < com.length; i++) {
				System.out.print((i + 1) + "." + com[i] + " 종목 금액 :" + price[i]);
				System.out.println();
			}
			String choice = sc.nextLine();

			for (int i = 0; i < mystock.size(); i++) {
				if (stockcnt[Integer.parseInt(choice) - 1] > 0) {
					if (mystock.get(i).get(com[Integer.parseInt(choice) - 1]) == price[Integer.parseInt(choice) - 1]) {
						mystock.remove(i);
						cnt++;
						stockcnt[Integer.parseInt(choice) - 1]--;
						i--;
						
					}else {
						System.out.println("이얏!");
					}
				} else {
					System.out.println("더이상 매도할 " + com[Integer.parseInt(choice) - 1] + "주식이 없습니다.");
				}

			}
			amount += (price[Integer.parseInt(choice) - 1] + (Integer.parseInt(choice) - 1) * sellfee) * cnt;
			System.out.println("매도를 축하드립니다.");

			for (int i = 0; i < com.length; i++) {
				System.out.println("현재" + com[i] + "주식 개수 :" + stockcnt[i]);
			}
		}
	}
	public void tmrw() {
		
		System.out.println("9시입니다. 장이 열립니다. 마음 편안히 가지세요");
		for( int i =0; i < price.length; i++) {
			up_down[i]+= (double)Math.floor(Math.random()*10)+(-5);
			price[i]+=up_down[i];
			if(low>price[i]) {
				low=price[i];
			}
		}
		for(int i = 0; i< com.length; i++) {
			System.out.println("금일 "+com[i]+"주식 등락률 :"+up_down[i]);
			up_down[i]=0;
		}
	}
	
	public void status() {
		for(int i =0; i<com.length; i++) {
			System.out.println("회사이름 :"+com[i]+"\t"+"가격 :"+price[i]+"\t"+"등락률 :"+up_down[i]
					+"\t"+"보유 개수 :"+stockcnt[i]);
		}
		System.out.println(); //띄어스기용
	}
}
