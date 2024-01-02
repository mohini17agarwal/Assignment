package Maven_Assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CountryLookupService {

	private static final String API_URL = "https://www.travel-advisory.info/api";
	private static final String FILE_PATH = "data.json";

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please provide country code(s) as arguments.");
			return;
		}

		String countryCode;
		for (String arg : args) {
			countryCode = arg.split("=")[0];
			String countryName = getCountryName(countryCode);
			System.out.println(countryCode + " -> " + countryName);
		}
	}

	private static String getCountryName(String countryCode) {
		try {
			// Loading data 
			JSONObject data = loadData();
			JSONObject countries = (JSONObject) data.get("data");
			JSONObject countryInfo = (JSONObject) countries.get(countryCode);

			if (countryInfo != null) {
				return (String) countryInfo.get("name");
			} else {
				return "Country code not found.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error occurred.";
		}
	}

	private static JSONObject loadData() throws IOException {
		JSONObject data = null;
		BufferedReader reader;

		try {
			// Reading from the API
			URL url = new URL(API_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				data = (JSONObject) new JSONParser().parse(reader);
				// Saveing data to a local file 
				dataToFile(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// If API call fails or data couldn't be retrieved from the API so loading
		// from file
		if (data == null) {
			try {
				reader = new BufferedReader(new FileReader(FILE_PATH));
				data = (JSONObject) new JSONParser().parse(reader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return data;
	}

	private static void dataToFile(JSONObject data) {
		try {
			// Writing data to a local file for offline use
			data.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
