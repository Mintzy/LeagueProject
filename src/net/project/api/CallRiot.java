package net.project.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import net.project.Constants;

/**
 * <p>
 * Title: Call Riot class
 * </p>
 * <p>
 * Description: This class will be used to call riot and store whatever they
 * reply with
 * </p>
 * 
 * @author Justin Mintzer
 *
 */
public class CallRiot {

	private String url;
	private String region;
	private String response;
	/**
	 * Creates the base for our URL
	 */
	public CallRiot() {
		url = new String();
	}

	public String now() {
		try {
			url += "api_key=" + Constants.API_KEY;
			URL url = new URL(this.url);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);
			response = new String(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setMethod(int method, String args) {
		url = "https://" + region + ".api.pvp.net/api/lol/" + region + "/";
		switch (method) {
		
		case Constants.GET_SUMMONER_BY_NAME:
			if (!args.isEmpty()) {
				try {
					url += "v1.4/summoner/by-name/" + URLEncoder.encode(args, "UTF-8").replace("+", "%20") + "?";
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			break;
			
		case Constants.GET_STATS_SUMMARY:
			if (!args.isEmpty()) {
				url += "v1.3/stats/by-summoner/" + args + "/summary?season=SEASON2016&";
			}
			break;
			
		}
		
	}

}
