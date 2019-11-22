package testProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoArrayList {
	List<Integer> lottoNum = new ArrayList<Integer>();;
	List<List<Integer>> firstList, secondList, thirdList, fourthList, fifthList, notLottoList,
			allRankList = new ArrayList();

	public static void main(String[] args) {
		// 입력 받은 argument를 변수에 담는다.
		String lottoNumArgs = args[0];
		String rankAll = args[1];
		String totalPerson = args[2];
		boolean random = false;

		// TRUE를 입력하지 않았을 시 boolean변수 안에 false값을 넣고 TRUE 입력 시 String변수로 받아 boolean값으로 변경
//		if (args.length == 3) {
//			random = false;
//		} else {
//			String ran = args[3];
//			random = Boolean.valueOf(ran).booleanValue();
//		}
		if (args.length == 3) {
			random = false;
		} else{
			String ran = args[3];
			if(ran.equals("TRUE") || ran.equals("true")) {				
				random = true;
			}else {
				System.out.println("잘못 입력하셨습니다. 다시 실행해주세요!");return;
			}
		}

		new LottoArrayList(lottoNumArgs, rankAll, totalPerson, random);

	}

	public LottoArrayList(String lottoNumArgs, String rankAll, String totalPerson, boolean random) {
		// list 선언
		lottoNum = new ArrayList<Integer>();
		firstList = new ArrayList();
		secondList = new ArrayList();
		thirdList = new ArrayList();
		fourthList = new ArrayList();
		fifthList = new ArrayList();
		notLottoList = new ArrayList();
		allRankList = new ArrayList();

		// 각 등수 별로 출력할 인원 수를 담을 변수 선언
		int first, second, third, fourth, fifth, total, notRank;

		// 로또 당첨 번호 저장을 위해 split하여 String배열에 담고 list에 넣어준다.
		String[] lottoSplit = lottoNumArgs.split(",");

		for (int i = 0; i < lottoSplit.length; i++) {
			lottoNum.add(Integer.parseInt(lottoSplit[i]));
		}

		// 각 등수 별 인원을 먼저 split 후, 인원 별 명수만 따로 int 변수에 담기
		String[] rankAllSplit = rankAll.split(",");

		first = Integer.parseInt(rankAllSplit[0].substring(2));
		second = Integer.parseInt(rankAllSplit[1].substring(2));
		third = Integer.parseInt(rankAllSplit[2].substring(2));
		fourth = Integer.parseInt(rankAllSplit[3].substring(2));
		fifth = Integer.parseInt(rankAllSplit[4].substring(2));
		total = Integer.parseInt(totalPerson);
		notRank = Integer.parseInt(totalPerson) - first - second - third - fourth - fifth;

		//System.out.println("전체 당첨자 수 :::" + first + "::" + second + "::" + third + "::" + fourth + "::" + fifth + "::" + notRank);

		// 등수 별로 처리
		this.firstLotto(first);
		this.secondLotto(second);
		this.thirdLotto(third);
		this.fourthLotto(fourth);
		this.fifthLotto(fifth);
		this.notRankLotto(notRank);
		this.printLotto(first, second, third, fourth, fifth, notRank, random, rankAllSplit, total);
		
//		if (random == false) {
//			this.printLotto(first, second, third, fourth, fifth, notRank, random, rankAllSplit, total);
//		} else {
//			this.randomPrint(first, second, third, fourth, fifth, notRank, random, rankAllSplit, total);
//		}
	}

	public void firstLotto(int first) {
		Random ran = new Random();
		int count = 0;

		// 입력된 1등 당첨자 수만큼 반복
		while (count < first) {
			firstList.add(lottoNum);
			count++;
		}
	}

	public void secondLotto(int second) {
		List<Integer> rankSecond = new ArrayList<Integer>();
		Random ran = new Random();
		int count = 0;
		int secondNum = 0;

		// 입력된 2등 당첨자 수만큼 반복
		while (count < second) {
			// 로또 당첨 번호를 넣어 줄 리스트 선언
			List<Integer> firstCopy = new ArrayList<Integer>();
			firstCopy.addAll(lottoNum);

			// 당첨 번호와 숫자 1개가 다르게 반복
			for (int i = 0; i < 1; i++) {
				// 반복문에서 사용할 rankSecond 리스트에 당첨 번호를 대입
				rankSecond = firstCopy;
				// 현재 리스트의 자리수 범위 내에서 랜덤값을 생성하여 인덱스를 삭제
				int index = ran.nextInt(rankSecond.size()); // 0~5 랜덤
				rankSecond.remove(index);

				// rankSecond의 사이즈가 5자리가 되면 break;
				if (rankSecond.size() == 5) {
					break;
				}
			}

			// 리스트에 넣어줄 새로운 난수를 구하고 그 난수가 당첨 번호와 중복이 되지 않는지 확인
			for (int j = 0; j < 1; j++) {
				secondNum = (int) (ran.nextInt(45) + 1);
				// secondNum = (int) (Math.random() * 45 + 1);

				for (int r = 0; r < lottoNum.size(); r++) {
					if (secondNum == lottoNum.get(r)) {
						j--;
					}
				}
			}
			rankSecond.add(secondNum);
			secondList.add(rankSecond);
			count++;
		}
	}

	public void thirdLotto(int third) {
		List<Integer> rankThird = new ArrayList<Integer>();
		Random ran = new Random();
		int count = 0;
		int thirdNum = 0;

		// 입력된 3등 당첨자 수만큼 반복
		while (count < third) {
			List<Integer> firstCopy = new ArrayList<Integer>();
			firstCopy.addAll(lottoNum);

			while (true) {
				rankThird = firstCopy;
				int index = ran.nextInt(rankThird.size()); // 0~5 랜덤
				rankThird.remove(index);

				// 4자리가 되면 break;
				if (rankThird.size() == 4) {
					break;
				}
			}

			while (true) {
				thirdNum = (int) (ran.nextInt(45) + 1);
				// 새로 넣어줄 난수가 당첨 번호와 중복되지 않고, 이미 담겨있는 3등 번호 값들과도 중복되지 않을 시 값을 3등번호 리스트에 추가
				if (!lottoNum.contains(thirdNum) && !rankThird.contains(thirdNum)) {
					rankThird.add(thirdNum);
				}

				// 번호 6개가 생성되면 break;
				if (rankThird.size() == 6) {
					break;
				}

			}
			thirdList.add(rankThird);
			count++;
		}
	}

	public void fourthLotto(int fourth) {
		List<Integer> rankFourth = new ArrayList<Integer>();
		Random ran = new Random();
		int count = 0;
		int fourthNum = 0;

		// 입력된 4등 당첨자 수만큼 반복
		while (count < fourth) {
			List<Integer> firstCopy = new ArrayList<Integer>();
			firstCopy.addAll(lottoNum);

			while (true) {
				rankFourth = firstCopy;
				int index = ran.nextInt(rankFourth.size());
				rankFourth.remove(index);

				// 4등이기 때문에 새로운 수를 3개 넣어주기 위해 사이즈가 3일때까지 반복
				if (rankFourth.size() == 3) {
					break;
				}
			}

			while (true) {
				fourthNum = (int) (ran.nextInt(45) + 1);
				if (!lottoNum.contains(fourthNum) && !rankFourth.contains(fourthNum)) {
					rankFourth.add(fourthNum);
				}

				if (rankFourth.size() == 6) {
					break;
				}
			}

			fourthList.add(rankFourth);
			count++;
		}
	}

	public void fifthLotto(int fifth) {
		List<Integer> rankFifth = new ArrayList<Integer>();
		Random ran = new Random();
		int count = 0;
		int fifthNum = 0;

		// 입력된 5등 당첨자 수만큼 반복
		while (count < fifth) {
			List<Integer> firstCopy = new ArrayList<Integer>();
			firstCopy.addAll(lottoNum);

			while (true) {
				rankFifth = firstCopy;
				int index = ran.nextInt(rankFifth.size());
				rankFifth.remove(index);

				if (rankFifth.size() == 2) {
					break;
				}
			}

			while (true) {
				fifthNum = (int) (ran.nextInt(45) + 1);

				if (!lottoNum.contains(fifthNum) && !rankFifth.contains(fifthNum)) {
					rankFifth.add(fifthNum);
				}
				if (rankFifth.size() == 6) {
					break;
				}
			}

			fifthList.add(rankFifth);
			count++;
		}
	}

	public void notRankLotto(int notRank) {
		Random ran = new Random();
		int count = 0;
		int overlap_cnt = 0;

		// 미등수 수만큼 반복
		while (count < notRank) {
			List<Integer> notRankLotto = new ArrayList<Integer>();

			// 새로운 로또 번호를 생성
			for (int i = 0; i < 6; i++) {
				notRankLotto.add(i, ((int) (Math.random() * 45 + 1)));

				// 겹치는 숫자 없는지 중복 확인
				for (int k = 0; k < i; k++) {
					if (notRankLotto.get(k) == notRankLotto.get(i)) {
						notRankLotto.remove(i);
						i--;
						break;
					}
				}
			}

			// 생성된 로또 번호가 로또 당첨 번호와 0개 또는 1개 일치 시에만 리스트에 담기
			overlap_cnt = 0;
			for (Integer r : notRankLotto) {
				for (Integer c : lottoNum) {
					if (r == c) {
						overlap_cnt++;
						break;
					}
				}
			}

			if (overlap_cnt == 0 || overlap_cnt == 1) {
				notLottoList.add(notRankLotto);
				count++;
			}
		}
	}

	//결과 출력
	public void printLotto(int first, int second, int third, int fourth, int fifth, int notRank, boolean random,
			String[] rankAllSplit, int total) {
		System.out.println("1:" + first + ",2:" + second + ",3:" + third + ",4:" + fourth + ",5:" + fifth + " " + total + "\n");

		int count = 0;

		System.out.println("=========== 결과 ===========");
		
		//list에 1~5등, 미등수 list를 담기
		allRankList.addAll(firstList);
		allRankList.addAll(secondList);
		allRankList.addAll(thirdList);
		allRankList.addAll(fourthList);
		allRankList.addAll(fifthList);
		allRankList.addAll(notLottoList);
		
		//random의 값이 true일 때만 list를 셔플
		if(random == true) {
			Collections.shuffle(allRankList);
		}
		
		//당첨번호와 비교하여 count++
		for (int j = 0; j < total; j++) {
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

			//count의 값대로 출력
			switch(count) {
			case 0 : System.out.println((j+1) + ". " + allRankList.get(j));break;
			case 1 : System.out.println((j+1) + ". " + allRankList.get(j));break;
			case 2 : System.out.println((j+1) + ". " + allRankList.get(j) + " -> 5등");break;
			case 3 : System.out.println((j+1) + ". " + allRankList.get(j) + " -> 4등");break;
			case 4 : System.out.println((j+1) + ". " + allRankList.get(j) + " -> 3등");break;
			case 5 : System.out.println((j+1) + ". " + allRankList.get(j) + " -> 2등");break;
			case 6 : System.out.println((j+1) + ". " + allRankList.get(j) + " -> 1등");break;
			default : System.out.println("정상적으로 계산되지 않았습니다");return;
			
			}
		}
	}
}
