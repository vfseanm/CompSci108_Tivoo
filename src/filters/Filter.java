package filters;

import java.util.*;
import model.*;
import org.joda.time.*;

public class Filter {
    
    public static List<TivooEvent> filterByTime(List<TivooEvent> eventlist, DateTime start, DateTime end) {
	List<TivooEvent> filtered = new ArrayList<TivooEvent>();
	for (TivooEvent e: eventlist) {
	    DateTime eventstart = e.getStart();
	    DateTime eventend = e.getEnd();
	    DateTimeComparator comp = DateTimeComparator.getInstance();
	    if (!(comp.compare(end, eventstart) < 0 || comp.compare(eventend, start) < 0))
		filtered.add(e);
	}
	return filtered;
    }
    
    public static List<TivooEvent> filterByKeyword(List<TivooEvent> eventlist, String keyword) {
	List<TivooEvent> filtered = new ArrayList<TivooEvent>();
	for (TivooEvent e: eventlist) {
	    if (e.getTitle().toLowerCase().contains(keyword.toLowerCase())) 
		filtered.add(e);
	}
	return filtered;
    }
    
}