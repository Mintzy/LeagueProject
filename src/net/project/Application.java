package net.project;

import java.util.Map;

import net.project.api.CallRiot;
import net.project.api.stats.AggregatedStats;
import net.project.api.stats.PlayerStatsSummary;
import net.project.api.stats.PlayerStatsSummaryList;
import net.project.api.summoner.Summoner;
import net.project.utilities.Converter;

/**
 * <p>
 * Title: The Application class
 * </p>
 * <p>
 * Description: This class will be responsible for launches the jframe of the
 * application
 * </p>
 * 
 * @author Justin Mintzer
 *
 */
public class Application {

	public Application() {
		init();
	}

	public void init() {

		// If I want to grab a summoner by their name
		String names = "superblazingfast";
		CallRiot call = new CallRiot();
		call.setRegion("NA");
		call.setMethod(Constants.GET_SUMMONER_BY_NAME, names);
		Converter convertJson = new Converter();
		Map<String, Summoner> summoners = convertJson.toSummoner(call.now());
		Summoner first = summoners.entrySet().iterator().next().getValue();
		System.out.println(first);
		String id = Long.toString(first.getId());
		call.setMethod(Constants.GET_STATS_SUMMARY, id);
		PlayerStatsSummaryList pss = convertJson.toSummaryList(call.now());
		int count = 0;
		boolean found = false;
		while (!found) {
			if (pss.getPlayerStatSummaries().get(count).getPlayerStatSummaryType().equalsIgnoreCase("RankedSolo5x5"))
				found = true;
			else
				count++;
		}
		System.out.println("\nRANKED SOLO STATS:\nWins: " + pss.getPlayerStatSummaries().get(count).getWins()
				+ "\nLosses: " + pss.getPlayerStatSummaries().get(count).getLosses() + "\n");
		AggregatedStats stats = pss.getPlayerStatSummaries().get(count).getAggregatedStats();
		System.out.println(stats.getAverageChampionsKilled());
		count = 0;
		found = false;
		while (!found) {
			if (pss.getPlayerStatSummaries().get(count).getPlayerStatSummaryType().equalsIgnoreCase("Unranked"))
				found = true;
			else
				count++;
		}
		
		//System.out.println("UNRANKED STATS:\nWins: " + pss.getPlayerStatSummaries().get(count).getWins()
		//		+ "\nLosses: " + pss.getPlayerStatSummaries().get(count).getLosses() + "\n");
	}

}
