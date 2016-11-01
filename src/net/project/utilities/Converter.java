package net.project.utilities;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.project.api.stats.PlayerStatsSummaryList;
import net.project.api.summoner.Summoner;

public class Converter {

	private Gson gson;

	public Converter() {
		gson = new Gson();
	}

	/*
	 * Get some information on player given their names
	 * 
	 * @return a map of players
	 */
	public Map<String, Summoner> toSummoner(String input) {
		Map<String, Summoner> summoner = gson.fromJson(input, new TypeToken<Map<String, Summoner>>() {
		}.getType());
		return summoner;
	}

	public PlayerStatsSummaryList toSummaryList(String input) {
		PlayerStatsSummaryList summaryList = gson.fromJson(input, PlayerStatsSummaryList.class);
		return summaryList;
	}

}
