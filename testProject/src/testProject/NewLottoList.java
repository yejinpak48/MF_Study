package testProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewLottoList {
	// 출력을 위해 list를 전부 담아줄 list를 생성.
	List<List<Integer>> allRankList = new ArrayList();

	public static void main(String[] args) {
		NewLottoList lotto = new NewLottoList();
		
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
		
		// 로또 당첨 번호 저장을 위해 split하여 String배열에 담고 list에 넣어준다.
		String[] lottoSplit = lottoNumArgs.split(",");

		// 숫자와 ,:만 포함되었을 시
		if (lottoNumArgs.matches("^[0-9,:]*$")) {
			for (int i = 0; i < lottoSplit.length; i++) {
				// int로 변환하여 list에 담기
				lottoNum.add(Integer.parseInt(lottoSplit[i]));
				// 각 등수 별 인원을 먼저 split 후, 인원 별 명수만 따로 int 변수에 담기
				rankAllSplit = rankAll.split(",");

				first = Integer.parseInt(rankAllSplit[0].substring(2));
				second = Integer.parseInt(rankAllSplit[1].substring(2));
				third = Integer.parseInt(rankAllSplit[2].substring(2));
				fourth = Integer.parseInt(rankAllSplit[3].substring(2));
				fifth = Integer.parseInt(rankAllSplit[4].substring(2));
				total = Integer.parseInt(totalPerson);
				notRank = Integer.parseInt(totalPerson) - first - second - third - fourth - fifth;
			}
		} else {
			System.out.println("입력하신 문자가 올바르지 않습니다.");
			return;
		}
		
		// TRUE를 입력하지 않았을 시 boolean변수 안에 false값을 넣고 TRUE 입력 시 String변수로 받아 boolean값으로 변경
		if (args.length == 3) {
			random = false;
		} else if(args.length == 4){
			String ran = args[3];
			for(int i = 0; i < ran.length(); i++) {
				if(ran.equalsIgnoreCase("TRUE")) {
					random = true;
				} else {
					System.out.println("입력하신 문자가 올바르지 않습니다.");
					return;
				}
			}
		}else {
			System.out.println("입력하신 문자가 올바르지 않습니다.");return;
		}

		int count = 0;
		int overlap_cnt = 0;

		// 각 등수별로 담아줄 list 선언
		List<List<Integer>> firstList = new ArrayList();
		List<List<Integer>> secondList = new ArrayList();
		List<List<Integer>> thirdList = new ArrayList();
		List<List<Integer>> fourthList = new ArrayList();
		List<List<Integer>> fifthList = new ArrayList();
		List<List<Integer>> notLottoList = new ArrayList();

		// total 수가 될때까지 무한 반복
		while (true) {
			List<Integer> lottoMachine = new ArrayList<Integer>();

			// 새로운 로또 번호를 생성
			for (int i = 0; i < 6; i++) {
				lottoMachine.add(i, ((int) (Math.random() * 45 + 1)));

				// list 안에 겹치는 숫자 없는지 중복 확인
				for (int k = 0; k < i; k++) {
					if (lottoMachine.get(k) == lottoMachine.get(i)) {
						lottoMachine.remove(i);
						i--;
						break;
					}
				}
			}

			// 생성된 로또 번호가 중복되는 갯수 구하기
			overlap_cnt = 0;
			for (Integer r : lottoMachine) {
				for (Integer c : lottoNum) {
					if (r == c) {
						overlap_cnt++;
						break;
					}
				}
			}
			System.out.println("dkssd" + overlap_cnt);

			// 각 list가 출력할 당첨자 수가 될때까지 담기
			switch (overlap_cnt) {
			case 0:
				if (notLottoList.size() < notRank) {
					notLottoList.add(lottoMachine);
					count++;
				}
				;
				break;
			case 1:
				if (notLottoList.size() < notRank) {
					notLottoList.add(lottoMachine);
					count++;
				}
				;
				break;
			case 2:
				if (fifthList.size() < fifth) {
					fifthList.add(lottoMachine);
					count++;
				}
				;
				break;
			case 3:
				if (fourthList.size() < fourth) {
					fourthList.add(lottoMachine);
					count++;
				}
				;
				break;
			case 4:
				if (thirdList.size() < third) {
					thirdList.add(lottoMachine);
					count++;
				}
				;
				break;
			case 5:
				if (secondList.size() < second) {
					secondList.add(lottoMachine);
					count++;
				}
				;
				break;
			case 6:
				if (firstList.size() < first) {
					firstList.add(lottoNum);
					count++;
				}
				;
				break;
			default:
				System.out.println("알 수 없는 오류입니다. 다시 시도해주세요.");
				return;
			}
			;

			// 카운트가 총 갯수와 같아지면 break;
			if (count == total) {
				break;
			}
			;
		}

		lotto.RankLotto(lottoNum, firstList, secondList, thirdList, fourthList, fifthList, notLottoList, first, second,
				third, fourth, fifth, notRank, random, rankAllSplit, total);

	}

	private void RankLotto(List<Integer> lottoNum, List<List<Integer>> firstList, List<List<Integer>> secondList,
			List<List<Integer>> thirdList, List<List<Integer>> fourthList, List<List<Integer>> fifthList,
			List<List<Integer>> notLottoList, int first, int second, int third, int fourth, int fifth, int notRank,
			boolean random, String[] rankAllSplit, int total) {

		System.out.print(
				"1:" + first + ",2:" + second + ",3:" + third + ",4:" + fourth + ",5:" + fifth + " " + total + "\n");

		int count = 0;
		int j = 0;

		System.out.println("=========== 결과 ===========");

		// list에 1~5등, 미등수 list를 담기
		allRankList.addAll(firstList);
		allRankList.addAll(secondList);
		allRankList.addAll(thirdList);
		allRankList.addAll(fourthList);
		allRankList.addAll(fifthList);
		allRankList.addAll(notLottoList);

		// random의 값이 true일 때만 list를 셔플
		if (random == true) {
			Collections.shuffle(allRankList);
		}

		// 당첨번호와 비교하여 count++
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
