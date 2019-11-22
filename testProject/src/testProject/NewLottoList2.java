//package testProject;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//
//public class NewLottoList2 {
//	// List<List<Integer>> allRankList = new ArrayList();
//
//	public static void main(String[] args) {
//		final int MAXNUM = 45;
//		final int MINNUM = 1;
//		NewLottoList2 lotto = new NewLottoList2();
//
//		// 입력 받은 argument를 변수에 담는다.
//		String lottoNumArgs = args[0];
//		String rankAll = args[1];
//		String totalPerson = args[2];
//		boolean random = false;
//		String[] rankAllSplit = null;
//
//		// 당첨 번호를 담아줄 list
//		List<Integer> lottoNum = new ArrayList<Integer>();
//
//		// 각 등수 별로 출력할 인원 수를 담을 변수 선언 (1~5등, 미등수, 토탈)
//		int first = 0;
//		int second = 0;
//		int third = 0;
//		int fourth = 0;
//		int fifth = 0;
//		int notRank = 0;
//		int total = 0;
//
//		// 로또 당첨 번호 저장을 위해 split하여 String배열에 담고 list에 넣어준다.
//		String[] lottoSplit = lottoNumArgs.split(",");
//
//		// 숫자와 ,:만 포함되었을 시
//		if (lottoNumArgs.matches("^[0-9,:]*$")) {
//			for (int i = 0; i < lottoSplit.length; i++) {
//				// int로 변환하여 list에 담기
//				lottoNum.add(Integer.parseInt(lottoSplit[i]));
//				// 각 등수 별 인원을 먼저 split 후, 인원 별 명수만 따로 int 변수에 담기
//				rankAllSplit = rankAll.split(",");
//
//				first = Integer.parseInt(rankAllSplit[0].substring(2));
//				second = Integer.parseInt(rankAllSplit[1].substring(2));
//				third = Integer.parseInt(rankAllSplit[2].substring(2));
//				fourth = Integer.parseInt(rankAllSplit[3].substring(2));
//				fifth = Integer.parseInt(rankAllSplit[4].substring(2));
//				total = Integer.parseInt(totalPerson);
//				notRank = Integer.parseInt(totalPerson) - first - second - third - fourth - fifth;
//			}
//		} else {
//			System.out.println("입력하신 문자가 올바르지 않습니다.");
//			return;
//		}
//
//		// TRUE를 입력하지 않았을 시 boolean변수 안에 false값을 넣고 TRUE 입력 시 String변수로 받아 boolean값으로 변경
//		if (args.length == 3) {
//			random = false;
//		} else if (args.length == 4) {
//			String ran = args[3];
//			for (int i = 0; i < ran.length(); i++) {
//				if (ran.equalsIgnoreCase("TRUE")) {
//					random = true;
//				} else {
//					System.out.println("입력하신 문자가 올바르지 않습니다.");
//					return;
//				}
//			}
//		} else {
//			System.out.println("입력하신 문자가 올바르지 않습니다.");
//			return;
//		}
//
//		int count = 0;
//		int overlap_cnt = 0;
//		int rankCount = 0;
//		int condition = 0;
//
//		int count5 = 0;
//		int fifthNum = 0;
//		Random ran = new Random();
//
//		// 각 등수별로 담아줄 list 선언
//		List<List<Integer>> firstList = new ArrayList();
//		List<List<Integer>> secondList = new ArrayList();
//		List<List<Integer>> thirdList = new ArrayList();
//		List<List<Integer>> fourthList = new ArrayList();
//		List<List<Integer>> fifthList = new ArrayList();
//		List<List<Integer>> notLottoList = new ArrayList();
//		// 출력을 위해 list를 전부 담아줄 list를 생성.
//		List<List<Integer>> allRankList = new ArrayList();
//
//		List<Integer> rankLotto = new ArrayList<Integer>();
//		List<Integer> lottoMachine = new ArrayList<Integer>();
//
//		for (int i = 0; i < first; i++) {
//			// 1등이랑 같은 수
//			condition = 6;
//			firstList.add(lotto.RankLotto(condition, lottoNum, MAXNUM, MINNUM));
//			System.out.println("퍼스트가 뭐" + firstList);
//			allRankList.addAll(firstList);
//		}
//		for (int i = 0; i < second; i++) {
//			condition = 5;
//			secondList.add(lotto.RankLotto(condition, lottoNum, MAXNUM, MINNUM));
//			System.out.println("두번째가 뭐" + firstList);
//			allRankList.addAll(secondList);
//		}
//		for (int i = 0; i < third; i++) {
//			condition = 4;
//			thirdList.add(lotto.RankLotto(condition, lottoNum, MAXNUM, MINNUM));
//			allRankList.addAll(thirdList);
//		}
////		for (int i = 0; i < fourth; i++) {
////			condition = 3;
////			secondList.add(lotto.RankLotto(condition, lottoNum, MAXNUM, MINNUM));
////			allRankList.addAll(fourList);
////		}
////		for (int i = 0; i < fifth; i++) {
////			condition = 2;
////			secondList.add(lotto.RankLotto(condition, lottoNum, MAXNUM, MINNUM));
////			allRankList.addAll(secondList);
////		}
////		for (int i = 0; i < notRank; i++) {
////			condition = (int) (Math.random() * 2);
////			secondList.add(lotto.RankLotto(condition, lottoNum, MAXNUM, MINNUM));
////			allRankList.addAll(secondList);
////		}
//
//		System.out.println("짠" + allRankList);
//
//		// lotto.PrintLotto(lottoNum, first, second, third, fourth, fifthNum, notRank,
//		// random, rankAllSplit, total);
//	}
//
//	public List<Integer> RankLotto(int condition, List<Integer> lottoNum, int MAXNUM, int MINNUM) {
//		List<Integer> rankLotto = new ArrayList<Integer>();
//
//		while (true) {
//			System.out.println("여기 오긴 옴?");
//				int LottoNumber = (int) (Math.random() * MAXNUM + MINNUM);
//
//				// 1등에 포함이 되는지, 서로 간에 중복이 없는지
//				if(lottoNum.contains(LottoNumber) && !rankLotto.contains(LottoNumber)) {
//					rankLotto.add(LottoNumber);
//				}
//				
//				if(rankLotto.size() == condition) {
//					break;
//				}
//			}
//		
//		while(rankLotto.size() < 6) {
//			int rankNum = (int) (Math.random() * MAXNUM + MINNUM);
//			
//			if(!lottoNum.contains(rankNum) && !rankLotto.contains(rankNum)) {
//				rankLotto.add(rankNum);
//			}
//		}
//	return rankLotto;
//	
//}
//
////	public void PrintLotto(List<Integer> lottoNum, int first, int second, int third, int fourth, int fifth, int notRank,
////			boolean random, String[] rankAllSplit, int total) {
////
////		System.out.print(
////				"1:" + first + ",2:" + second + ",3:" + third + ",4:" + fourth + ",5:" + fifth + " " + total + "\n");
////
////		int count = 0;
////		int j = 0;
////		System.out.println(allRankList);
////		System.out.println("=========== 결과 ===========");
////
////		// random의 값이 true일 때만 list를 셔플
////		if (random == true) {
////			Collections.shuffle(allRankList);
////		}
////
////		// 당첨번호와 비교하여 count++
////		while (j < total) {
////			count = 0;
////			for (Integer r : allRankList.get(j)) {
////				// 1~5등까지 다 넣은 리스트
////				for (Integer c : lottoNum) {
////					// 로또 당첨번호
////					if (r == c) {
////						count++;
////						break;
////					}
////				}
////			}
////			// count의 값대로 출력
////			switch (count) {
////			case 0:
////				System.out.println((j + 1) + ". " + allRankList.get(j));
////				break;
////			case 1:
////				System.out.println((j + 1) + ". " + allRankList.get(j));
////				break;
////			case 2:
////				System.out.println((j + 1) + ". " + allRankList.get(j) + " -> 5등");
////				break;
////			case 3:
////				System.out.println((j + 1) + ". " + allRankList.get(j) + " -> 4등");
////				break;
////			case 4:
////				System.out.println((j + 1) + ". " + allRankList.get(j) + " -> 3등");
////				break;
////			case 5:
////				System.out.println((j + 1) + ". " + allRankList.get(j) + " -> 2등");
////				break;
////			case 6:
////				System.out.println((j + 1) + ". " + allRankList.get(j) + " -> 1등");
////				break;
////			default:
////				System.out.println("정상적으로 계산되지 않았습니다");
////				return;
////
////			}
////			j++;
////		}
////	}
//
//}
