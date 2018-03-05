package base_statistics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.util.Elements;

public class GoogleAPI {
	private String line;
	private String eDate;
	private String url ="https://www.googleapis.com/youtube/v3/search?q=";
	private String urlDateSPrefix="&publishedAfter=";
	private String urlDateSSuffix="-01-01T00:00:00Z";
	private String urlDateEPrefix="&publishedBefore=";
	private String url3DateESuffix="-01-01T00:00:00Z";
	private final String apiKey="&key=AIzaSyBZHoUrrXbJDVnhHNwG6CTWNyjsU4uVmfw";
	private String requestPart="&part=snippet";
	
	private String totalResultNumber;
	private String output;
	
	
	// this class get number of incident in youtube (videos)
	public GoogleAPI(String queryTerm,String startDate,String endDate) throws IOException{
		this.eDate=endDate;
		url+=queryTerm;
		url+=urlDateSPrefix;
		url+=startDate;
		url+=urlDateSSuffix;
		url+=urlDateEPrefix;
		url+=endDate;
		url+=url3DateESuffix;
		url+=apiKey;
		url+=requestPart;
		
		
		// get a url request
		URL urlRequest = new URL(url);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(urlRequest.openStream()));
		
		while((line=reader.readLine())!=null)
		{
			getTotalResult(line);
			
		}
		reader.close();
		
		
	}
	

	
	private String numberOfResult;
	
	Pattern totalResultPattern = Pattern.compile(
			"\"totalResults\": (\\d*)");
	Matcher totalResultMatcher;
	
	public void getTotalResult(String searchResult){
		totalResultMatcher = totalResultPattern.matcher(searchResult);
		if (totalResultMatcher.find() && totalResultMatcher.group() != null) {
			String numberOfResult;
			
			numberOfResult = totalResultMatcher.group(1);
			if(Integer.parseInt(eDate) < 2005 ) numberOfResult="No Data Found on Youtube";
		
			output=numberOfResult;
			
			

		}
		

	}
	
	

	public String getOutput(){
		return output;
	}
	

	
	
}

