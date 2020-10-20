import java.util.Random;

public class GamblingSimulation {

	public static final int STARTING_DAILY_STAKE = 100;
	public static final int BET = 1;
	public static final int DAYS = 30;


	public static int totalWin = 0;
	public static int totalLoss = 0;


	private static int checkWin() {
		return (int) Math.floor(Math.random()*10)%2;
	}


	private static int checkResign(int startingStake, int endDayAmount) {

		double winResignAmt = 1.5 * startingStake;
		double lossResignAmt = 0.5 * startingStake;
		if (endDayAmount >= winResignAmt || endDayAmount <= lossResignAmt)
			return 1;
		else
			return 0;
	}
	

	public static void main(String[] args) {
		int month=0;
		do{ 
			int netTotal = 0;
			int luckyDayAmt = 0;
			int unluckyDayAmt = 0;
			int luckyDay = 0;
			int unluckyDay = 0;

			for (int day=1;day<=DAYS;day++) {

				int endDayAmount = STARTING_DAILY_STAKE;
				int dailyWin = 0;
				int dailyLoss = 0;

				while(checkResign(STARTING_DAILY_STAKE, endDayAmount) != 1){

					if (checkWin() == 1){
						endDayAmount += BET;
						dailyWin += BET;
					}
					else {
						endDayAmount -= BET;
						dailyLoss += BET;
					}
				}
				totalWin += dailyWin;
				totalLoss += dailyLoss;
				netTotal += (dailyWin - dailyLoss);

				if (luckyDayAmt < netTotal) {
					luckyDayAmt = netTotal;
					luckyDay = day;
				}

				if (unluckyDayAmt > netTotal) {
					unluckyDayAmt = netTotal;
					unluckyDay = day;
				}

			}

			System.out.println("Total amount won : "+ totalWin);
			System.out.println("Total amount lost : "+ totalLoss);
			System.out.println("Net amount : "+ (totalWin-totalLoss));
			System.out.println("Lucky day is Day "+luckyDay+" amount won is "+luckyDayAmt+".");
			System.out.println("Unlucky day is Day "+unluckyDay+" amount lost is "+unluckyDayAmt+".");
		}while((totalWin-totalLoss) > 0);
	}
}





