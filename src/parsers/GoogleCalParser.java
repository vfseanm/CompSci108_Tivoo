package parsers;

import java.util.*;
import org.dom4j.*;
import org.joda.time.*;
import org.joda.time.format.*;

import utils.ParserUtils;
import utils.TivooUtils;
import model.*;

public class GoogleCalParser implements ITivooParser {

    public List<TivooEvent> convertToList(Document doc) {
	@SuppressWarnings("unchecked")
	List<Node> list = doc.selectNodes("//*[name()='entry']");
	String timezone = 
	  ((Element) doc.selectSingleNode("//*[name()='gCal:timezone']")).attributeValue("value");
	List<TivooEvent> eventlist = new ArrayList<TivooEvent>();
	for (Node n: list) {
		String title = ParserUtils.getInfo(n, "title");
		String description = ParserUtils.getInfo(n, "content");
		String timestring = ParserUtils.getInfo(n, "summary");
	    if (timestring.startsWith("Recurring")) continue;
	    ArrayList<DateTime> startend = parseOneTimeEvent(timestring, timezone);
	    if (startend == null) continue;
	    eventlist.add(new TivooEvent(TivooUtils.sanitizeString(title), 
		    startend.get(0), startend.get(1), 
		    TivooUtils.sanitizeString(description)));
	}
	return eventlist;
    }
    
    @SuppressWarnings("serial")
    private ArrayList<DateTime> parseOneTimeEvent(String timestring, String timezone) {
	DateTimeZone thezone = DateTimeZone.forID(timezone);
	String[] splitted = timestring.split(" ");
	if (splitted.length < 8) return null;
	//When: Wed Sep 21, 2011 5:30pm to 6pm&nbsp; EDT<br> <br>Event Status: confirmed
	String month = splitted[2], 
		date = splitted[3].substring(0, splitted[3].length() - 1), 
		year = splitted[4], starttime = splitted[5], 
		endtime = splitted[7].split("&")[0];
	DateTimeFormatter monthformat = DateTimeFormat.forPattern("MMM");
	int _month = monthformat.parseDateTime(month).getMonthOfYear();
	int _date = Integer.parseInt(date);
	int _year = Integer.parseInt(year);
	DateTimeParser[] parsers = { 
	        DateTimeFormat.forPattern( "hh:mmaa" ).getParser(),
	        DateTimeFormat.forPattern( "hhaa" ).getParser() };
	DateTimeFormatter hourformat = new DateTimeFormatterBuilder().append( null, parsers ).toFormatter();
	int _starthour = hourformat.parseDateTime(starttime).getHourOfDay();
	int _startminute = hourformat.parseDateTime(starttime).getMinuteOfHour();
	int _endhour = hourformat.parseDateTime(endtime).getHourOfDay();
	int _endminute = hourformat.parseDateTime(endtime).getMinuteOfHour();
	final DateTime start = new DateTime(_year, _month, 
		_date, _starthour, _startminute, thezone);
	final DateTime end = new DateTime(_year, _month, 
		_date, _endhour, _endminute, thezone);
	return new ArrayList<DateTime>() {{
	    add(start); add(end);
	}};
    }
    
    private DateTime parseRecurringEvent() {
	return null;
    }
    
    public boolean isThisThing(Document doc)
    {
    	String rootname = doc.getRootElement().getName();
    	return (rootname.contentEquals("feed"));
    }
    
}
