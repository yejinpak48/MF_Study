package testProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NewLottoList3 {
	
	public static void main(String[] args) {
		NewLottoList3 lotto = new NewLottoList3();
		
		//생성 번호의 최대값과 최소값
		final int MAXNUM = 45;
		final int MINNUM = 1;

		// 입력 받은 argument를 변수에 담는다.
		String lottoNumArgs = args[0];
		String rankAll = args[1];
		String totalPerson = args[2];
		boolean random = false;
		String[] rankAllSplit = null;

		// 당첨 번호를 담아줄 list
		List<Integer> lottoNum = new ArrayList<Integer>();

		// 각 등수 별로 출력할 인원 수를 담을 변수 선언 (1~5등, 미등수, 토탈)
		int first = 0;
		int second = 0;
		int third = 0;
		int fourth = 0;
		int fifth = 0;
		int notRank = 0;
		int total = 0;
		int lottoCount = 6;

		// 1등 번호 저장을 위해 split하여 String배열에 담고 list에 넣음
		String[] lottoSplit = lottoNumArgs.split(",");
		
		// 1등 번호가 지정된 갯수가 맞는지 확인
		if(lottoSplit.length != lottoCount) {
			System.out.println("당첨 번호는 " + lottoCount + "개여야 합니다.");
			return;
		}

		// 숫자와 기호 ,:만 포함되었을 시
		if (lottoNumArgs.matches("^[0-9,:]*$")) {
			for (int i = 0; i < lottoSplit.length; i++) {
				
				// int로 변환하여 list에 담기
				lottoNum.add(Integer.parseInt(lottoSplit[i]));

				//1등 번호가 지정된 최소값, 최대값 범위 내에 있는지 확인
				if (!(lottoNum.get(i) >= MINNUM && lottoNum.get(i) <= MAXNUM)) {
					System.out.println(MINNUM + "과 " + MAXNUM + "사이의 수를 입력해주세요.");
					return;
				}
			}
		} else {
			System.out.println("입력하신 문자가 올바르지 않습니다.");
			return;
		}

		// 각 등수 별 인원을 먼저 split 후, 인원 별 명수만 따로 int 변수에 담기
		rankAllSplit = rankAll.split(",");
		
		first = Integer.parseInt(rankAllSplit[0].substring(2));
		second = Integer.parseInt(rankAllSplit[1].substring(2));
		third = Integer.parseInt(rankAllSplit[2].substring(2));
		fourth = Integer.parseInt(rankAllSplit[3].substring(2));
		fifth = Integer.parseInt(rankAllSplit[4].substring(2));
		total = Integer.parseInt(totalPerson);
		notRank = Integer.parseInt(totalPerson) - first - second - third - fourth - fifth;

		// TRUE를 입력하지 않았을 시 boolean변수 안에 false값을 넣고 TRUE 입력 시 String변수로 받아 boolean값으로 변경
		if (args.length == 3) {
			random = false;
		} else if (args.length == 4) {
			String ran = args[3];
			for (int i = 0; i < ran.length(); i++) {
				if (ran.equals("TRUE")) {
					random = true;
				} else {
					System.out.println("입력하신 문자가 올바르지 않습니다.");
					return;
				}
			}
		} else {
			System.out.println("입력하신 문자가 올바르지 않습니다.");
			return;
		}

		// ㅏㅇ아아
		Random ran = new Random();
		int count = 0;
		int overlap_cnt = 0;
		int rankCount = 0;
		int condition = 0;


		// 각 등수별로 담아줄 list 선언
		List<List<Integer>> firstList = new ArrayList();
		List<List<Integer>> secondList = new ArrayList();
		List<List<Integer>> thirdList = new ArrayList();
		List<List<Integer>> fourthList = new ArrayList();
		List<List<Integer>> fifthList = new ArrayList();
		List<List<Integer>> notLottoList = new ArrayList();
		
		// list를 전부를 담을 list를 생성.
		List<List<Integer>> allRankList = new ArrayList();
//		List<List<Integer>> rankList = new ArrayList();

		//각 등수 별로 1등 번호와 같아야하는 갯수를 정해 함수 실행하고 list로 담기
		for (int i = 0; i < first; i++) {
			condition = lottoCount;
			firstList.add(lottoNum);
		}
		for (int i = 0; i < second; i++) {
			condition = lottoCount - 1;
			secondList.add(lotto.RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}
		for (int i = 0; i < third; i++) {
			condition = lottoCount - 2;
			thirdList.add(lotto.RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}
		for (int i = 0; i < fourth; i++) {
			condition = lottoCount - 3;
			fourthList.add(lotto.RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}
		for (int i = 0; i < fifth; i++) {
			condition = lottoCount - 4;
			fifthList.add(lotto.RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}
		for (int i = 0; i < notRank; i++) {
			//condition = (int) (Math.random() * 2) + lottoCount - 6;
			condition = (int) (Math.random() * 2);
			notLottoList.add(lotto.RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}

		// 등수 별 list 한 곳에 담기 
		allRankList.addAll(firstList);
		allRankList.addAll(secondList);
		allRankList.addAll(thirdList);
		allRankList.addAll(fourthList);
		allRankList.addAll(fifthList);
		allRankList.addAll(notLottoList);
		
//		// 등수 별 list 한 곳에 담기 
//		rankList.addAll(firstList);
//		rankList.addAll(secondList);
//		rankList.addAll(thirdList);
//		rankList.addAll(fourthList);
//		rankList.addAll(fifthList);
//		
//		Map<String, List<List<Integer>>> randomPrint = new HashMap<String, List<List<Integer>>>();
//		
//		for(int i = 0; i < allRankList.size(); i++) {
//			randomPrint.put("당첨", rankList);
//			randomPrint.put("미당첨", notLottoList);
//			
//		}
//		System.out.println("보자보자 : : : : " + randomPrint.get("당첨"));
//		System.out.println("map : : : : :" + randomPrint.get("미당첨"));
//		System.out.println("djEJgrp skdhsl : " + randomPrint);

		// 출력
		lotto.PrintLotto(allRankList, lottoNum, first, second, third, fourth, fifth, notRank, random, total);
		//lotto.RandomPrintLotto(randomPrint, lottoNum);
	
	
	}

	public void RandomPrintLotto(Map<String, List<List<Integer>>> randomPrint, List<Integer> lottoNum) {
		
		for(int i = 0; i < randomPrint.size(); i++) {
			System.out.println("일단 출력이나 해보시게" + randomPrint.get("당첨"));
			
			
		}
		
		
		
	}

	/**
	 * 2~5등, 미등수를 생성하는 함수
	 * 
	 * @param condition : 1등 번호와 중복되는 번호의 수
	 * @param lottoNum : 1등 번호
	 * @param MAXNUM : 랜덤 최대값
	 * @param MINNUM : 랜덤 최소값
	 * @return : 로또 번호를 생성하여 리턴
	 */
	public List<Integer> RankLotto(int condition, List<Integer> lottoNum, int MAXNUM, int MINNUM) {
		int lottoNumber = 0;
		Random ran = new Random();
		List<Integer> rankLotto = new ArrayList<Integer>(lottoNum);

		while (true) {
			int index = ran.nextInt(rankLotto.size());
			rankLotto.remove(index);

			if (rankLotto.size() == condition) {
				break;
			}
		}
		while (true) {
			lottoNumber = (int) (ran.nextInt(MAXNUM) + MINNUM);

			if (!lottoNum.contains(lottoNumber) && !rankLotto.contains(lottoNumber)) {
				rankLotto.add(lottoNumber);
			}
			if (rankLotto.size() == 6) {
				break;
			}
		}
		return rankLotto;

	}

	/**
	 * 
	 * 출력하는 함수
	 * 
	 * @param allRankList : 모든 등수를 담을 list
	 * @param lottoNum : 1등 번호를 담고 있는 list
	 * @param first : 1등 당첨자 수
	 * @param second : 2등 당첨자 수
	 * @param third : 3등 당첨자 수
	 * @param fourth : 4등 당첨자 수
	 * @param fifth : 5등 당첨자 수
	 * @param notRank : 미등수
	 * @param random : 출력 랜덤 여부
	 * @param total : 총 출력 수
	 */
	public void PrintLotto(List<List<Integer>> allRankList, List<Integer> lottoNum, int first, int second, int third,
			int fourth, int fifth, int notRank, boolean random, int total) {

		System.out.print(
				"1:" + first + ",2:" + second + ",3:" + third + ",4:" + fourth + ",5:" + fifth + " " + total + "\n");

		int count = 0;
		int j = 0;

//		List<Integer> inputs = new ArrayList<Integer>(Arrays.asList(1, 3, 6, 10, 20, 60));
//		int max = 100;
//		for (Integer input : inputs){
//			max -= input;
//		}
//		if(max != 0) {
//			System.out.println("invalid params");
//			return;
//		}
//		inputs.add(0, 0);
//		int rand = (int) (Math.random() * 100) + 1;
//		int result = 0;
//		int start = 0;
//		int end = 0;
//		for(int i = 0; inputs.size() - 1; i++) {
//			start += inputs.get(i);
//			end += inputs.get(i + 1);
//			if(rand >= start && rand <= end) {
//				result = i;
//				break;
//			}
//		}
		
		
		
//		float result = 1.0f;
//		int displayedLotto = total / 5;
		System.out.println("=========== 결과 ===========");

		// random의 값이 true일 때만 list를 셔플
//		if (random == true) {
//			for(int i = 0; i < displayedLotto; i++) {
//				result = result * (total - displayedLotto - i) / (total - i);
//				Collections.shuffle(allRankList);
//			}
//			System.out.println("이게 대체 뭘까" + result);
//		}
		
		//랜덤으로 출력 (단 제어가 가능하게)
		if (random == true) {
			Collections.shuffle(allRankList);
//			count = 0;
//			for (Integer r : allRankList.get(j)) {
//				// 1~5등까지 다 넣은 리스트
//				for (Integer c : lottoNum) {
//					// 로또 당첨번호
//					if (r == c) {
//						count++;
//						break;
//					}
//				}
//			}
//			
//			if(count > 1) {
//				for(int i = 0; i < 10; i++) {
//			}

		}
		// 당첨번호와 비교하여 count
		while (j < total) {
			count = 0;
			for (Integer r : allRankList.get(j)) {
				// 1~5등까지 다 넣은 리스트
				for (Integer c : lottoNum) {
					// 로또 당첨번호
					if (r == c) {
						count++;
						break;
					}
				}
			}

			// count의 값대로 출력
			switch (count) {
			case 0:System.out.println((j + 1) + ". " + allRankList.get(j));break;
			case 1:System.out.println((j + 1) + ". " + allRankList.get(j));break;
			case 2:System.out.println((j + 1) + ". " + allRankList.get(j) + " -> 5등");break;
			case 3:System.out.println((j + 1) + ". " + allRankList.get(j) + " -> 4등");break;
			case 4:System.out.println((j + 1) + ". " + allRankList.get(j) + " -> 3등");break;
			case 5:System.out.println((j + 1) + ". " + allRankList.get(j) + " -> 2등");break;
			case 6:System.out.println((j + 1) + ". " + allRankList.get(j) + " -> 1등");break;
			default:System.out.println("정상적으로 계산되지 않았습니다");return;

			}
			j++;
		}
	}

}
