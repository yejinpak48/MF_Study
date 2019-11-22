package testProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class NewLottoList4 {
	final int MINNUM = 1;
	final int MAXNUM = 45;

	// 랜덤 출력 유무
	private boolean setMix = false;

	// 로또 번호의 갯수, 등수
	private int lottoSize = 6;
	private String lottoName = "";

	// 각 등수 별로 출력할 인원 수를 담을 변수 선언 (1~5등, 전체개수)
	private final static int firstCount = 1;
	private int secondCount = 0;
	private int thirdCount = 0;
	private int fourthCount = 0;
	private int fifthCount = 0;
	private int totalCount = 0;

	// 미당첨자 수, 당첨자 수, 랜덤 출력 시 나눌 기준
	private int noRank = 0;
	private int rank = 0;
	private int div = 0;

	// 1등 번호 list
	Set<Integer> lottoNum = new HashSet<Integer>();

	// 등수별, 당첨, 미당첨 list
	List<List<Integer>> firstList = new ArrayList();
	List<List<Integer>> secondList = new ArrayList();
	List<List<Integer>> thirdList = new ArrayList();
	List<List<Integer>> fourthList = new ArrayList();
	List<List<Integer>> fifthList = new ArrayList();
	List<Set<Integer>> noLottoList = new ArrayList();
	List<Set<Integer>> allRankList = new ArrayList();

	public static void main(String[] args) {
		NewLottoList4 lotto = new NewLottoList4();

		try {

			// param 유효성 검사
			lotto.argsValiddation(args);

			// 로또 번호 생성
			lotto.inputLottoList();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void argsValiddation(String[] args) throws Exception {
		// args의 길이가 3개 이상인지, 길이가 4이상일 때 3번째 자리에 들어온 값이 대문자 TRUE라면 변수에 담기
		if (args.length > 2) {
			setMix = false;
		} else {
			throw new Exception(
					"파라메터가 없습니다.\nP1(필수) : 1등숫자(,로구분) / P2(필수) : 각등수별 갯수(1:1,2:3...) / P3(필수) : 생성할 전체 수 / P4(옵션) : Mix여부(TRUE) ");
		}
		if (args.length > 3) {
			String ran = args[3];
			if (ran.equals("TRUE")) {
				setMix = true;
			}
		}

		// 입력 받은 argument를 변수에 담는다.
		String lottoNumArgs = args[0];
		String rankAll = args[1];
		String totalPerson = args[2];
		String[] rankAllSplit = null;

		// 1등 번호 저장을 위해 split하여 String배열에 담고 list에 넣음
		String[] lottoSplit = lottoNumArgs.split(",");

		// 1등 번호가 지정된 갯수가 맞는지 확인
		if (lottoSplit.length != lottoSize) {
			System.out.println("당첨 번호는 " + lottoSize + "개여야 합니다.");
			System.exit(0);
		}

		for (int i = 0; i < lottoSplit.length; i++) {
			// 숫자로 변환
			lottoNum.add(Integer.parseInt(lottoSplit[i]));

			// 1등 번호가 지정된 최소값, 최대값 범위 내에 있는지 확인
			for(int item : lottoNum) {
				if(MINNUM > item || MAXNUM < item) {
					throw new Exception(String.format("1등 각 숫자는 %d 이상, %d 이하의 수를 입력해주세요.", MINNUM, MAXNUM));
				}
			}
			
		}
		int count = 0;
		for (Integer r : lottoNum) {
			// 1~5등까지 다 넣은 리스트
			for (Integer c : lottoNum) {
				// 로또 당첨번호
				if (r == c) {
					count++;
					break;
				}
			}
		}
		if (count != 6) {
			throw new Exception("로또 번호는 중복되선 안됩니다.");
		}

		// 각 등수 별 인원을 먼저 split 후, 인원 별 명수만 따로 int 변수에 담기
		rankAllSplit = rankAll.split(",");

		if (rankAllSplit.length == lottoSize - 1) {

			for (String item : rankAllSplit) {
				int index = item.indexOf(":");
				String grade = item.substring(0, index);
				int gradeCount = Integer.parseInt(item.substring(index + 1));
				
				if (gradeCount >= 0) {
					switch (grade) {
					case "2":
						secondCount = gradeCount;
						break;
					case "3":
						thirdCount = gradeCount;
						break;
					case "4":
						fourthCount = gradeCount;
						break;
					case "5":
						fifthCount = gradeCount;
						break;
					}
				} else {
					throw new Exception("2~5등은 0이상이어야합니다.");
				}
			}


		}
		
		// total보다 당첨자 수가 많으면 종료
		if (totalCount > rank) {
			totalCount = Integer.parseInt(totalPerson);
			noRank = Integer.parseInt(totalPerson) - firstCount - secondCount - thirdCount - fourthCount - fifthCount;
		}else {
			throw new Exception("전체 개수는 당첨 개수보다 높고 숫자여야 합니다.");
		}
		
		// 당첨자 수
		rank = totalCount - noRank;

		// 랜덤 출력 시 나눌 기준
		if (rank < noRank) {
			div = totalCount / rank;
		} else {
			div = totalCount / noRank;
		}


	}

	public void inputLottoList() {
		// 1등 번호와 중복되어야하는 개수
		int condition = 0;

		// 각 등수 별로 1등 번호와 같아야하는 개수를 정해 함수 실행하고 list로 담기
		for (int i = 0; i < firstCount; i++) {
			condition = lottoSize;
			allRankList.add(lottoNum);
		}
		// 2등 당첨자 수만큼 list에 담기
		for (int i = 0; i < secondCount; i++) {
			condition = lottoSize - 1;
			allRankList.add(RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}
		for (int i = 0; i < thirdCount; i++) {
			condition = lottoSize - 2;
			allRankList.add(RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}
		for (int i = 0; i < fourthCount; i++) {
			condition = lottoSize - 3;
			allRankList.add(RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}
		for (int i = 0; i < fifthCount; i++) {
			condition = lottoSize - 4;
			allRankList.add(RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}
		for (int i = 0; i < noRank; i++) {
			condition = (int) (Math.random() * 2);
			noLottoList.add(RankLotto(condition, lottoNum, MAXNUM, MINNUM));
		}

		// 출력
		PrintLotto();

	}

	/**
	 * 2~5등, 미등수를 생성하는 함수
	 * 
	 * @param condition : 1등 번호와 중복되는 번호의 수
	 * @param lottoNum  : 1등 번호
	 * @param MAXNUM    : 랜덤 최대값
	 * @param MINNUM    : 랜덤 최소값
	 * @return : 로또 번호를 생성하여 리턴
	 */
	public Set<Integer> RankLotto(int condition, Set<Integer> lottoNum, int MAXNUM, int MINNUM) {
		int lottoNumber = 0;
		Random ran = new Random();

		// 1등 번호를 담는 list
		Set<Integer> rankLotto = new HashSet<Integer>(lottoNum);

		// 랜덤으로 구한 index를 각 등수의 자리수가 되도록 제거
		while (true) {
			int index = ran.nextInt(rankLotto.size());
			rankLotto.remove(index);

			// 전달 받은 각 등수의 사이즈가 되면 break;
			if (rankLotto.size() == condition) {
				break;
			}
		}
		while (true) {
			lottoNumber = (int) (ran.nextInt(MAXNUM) + MINNUM);

			// 1등 번호와 중복되지 않고, 현재 list내에 중복이 없을 시 난수를 담기
			if (!lottoNum.contains(lottoNumber)) {
				rankLotto.add(lottoNumber);
			}
			// 정해진 자리 수가 채워졌을 때 break; (현재는 6개)
			if (rankLotto.size() == lottoSize) {
				break;
			}
		}
		// 번호 순서 섞기
		// Collections.shuffle(rankLotto);
		return rankLotto;
	}

	/**
	 * 
	 * 출력하는 메소드
	 */
	public void PrintLotto() {
		// 출력 시 번호
		int num = 1;
		// 당첨, 미당첨 인덱스
		int rankIndex = 0;
		int noLottoIndex = 0;

		// 랜덤 출력 미완성이라 랜덤 출력할 리스트
		List<List<Integer>> prepareList = new ArrayList();

		System.out.print("1:" + firstCount + ",2:" + secondCount + ",3:" + thirdCount + ",4:" + fourthCount + ",5:"
				+ fifthCount + " " + totalCount + "\n");

		System.out.println("=========== 결과 ===========");

		// 당첨리스트 + 미당첨리스트의 사이즈
		int totalSize = allRankList.size() + noLottoList.size();

		System.out.println("나눌 값 :" + div + " 토탈 사이즈 :" + totalSize + " 당첨자 수 :" + rank + " 미당첨자수 :" + noRank);

		// TURE일 시 적절하게 출력
		if (setMix == true) {
			Collections.shuffle(allRankList);
			// 출력을 위해 전체 개수만큼 반복
			for (int i = 1; i <= totalSize; i++) {
				// 미당첨 > 당첨
				if (noLottoList.size() > allRankList.size()) {
					if (i % div == 0 && rankIndex < allRankList.size()) {
						LottoName(allRankList.get(rankIndex));
						System.out.println(num + ". " + allRankList.get(rankIndex) + lottoName);
						rankIndex++;
						num++;
					} else {
						System.out.println(num + ". " + noLottoList.get(noLottoIndex));
						noLottoIndex++;
						num++;
					}

					// 당첨 > 미당첨
				} else {
					if (i % div == 0 && noLottoIndex < noLottoList.size()) {
						System.out.println(num + ". " + noLottoList.get(noLottoIndex));
						noLottoIndex++;
						num++;
					} else {
						LottoName(allRankList.get(rankIndex));
						System.out.println(num + ". " + allRankList.get(rankIndex) + lottoName);
						rankIndex++;
						num++;
					}
				}
			}

			// 순서대로 출력
		} else {
			for (int j = 0; j < allRankList.size(); j++) {
				LottoName(allRankList.get(j));
				System.out.println(num + ". " + allRankList.get(j) + lottoName);
				num++;
			}
			for (int c = 0; c < noLottoList.size(); c++) {
				System.out.println(num + ". " + noLottoList.get(c));
				num++;
			}
		}
	}

	/**
	 * 각 등수 별 lottoName을 정하기 위한 메소드
	 * 
	 * @param set 출력 메소드에서 전달 받은 list 값
	 */
	public void LottoName(Set<Integer> set) {
		int count = 0;
		int j = 0;

		// 당첨번호와 비교하여 count
		while (j < set.size()) {
			count = 0;
			for (Integer r : set) {
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
			case 0:
				lottoName = " ";
				break;
			case 1:
				lottoName = " ";
				break;
			case 2:
				lottoName = " -> 5등";
				break;
			case 3:
				lottoName = " -> 4등";
				break;
			case 4:
				lottoName = " -> 3등";
				break;
			case 5:
				lottoName = " -> 2등";
				break;
			case 6:
				lottoName = " -> 1등";
				break;
			default:
				System.out.println("정상적으로 계산되지 않았습니다");
				return;

			}
			j++;
		}
	}

}
