package parsers;
import java.util.*;
import org.dom4j.*;
import org.joda.time.*;

import utils.ParserUtils;
import model.*;

public class DukeCalParser implements ITivooParser {
    
    public List<TivooEvent> convertToList(Document doc) {
	@SuppressWarnings("unchecked")
	List<Node> list = doc.selectNodes("//*[name()='event']");
	List<TivooEvent> eventlist = new ArrayList<TivooEvent>();
	for (Node n: list) {
		String title = ParserUtils.getInfo(n, "summary");
		String description = ParserUtils.getInfo(n, "description");
	    Node startfield = n.selectSingleNode("./start/*[name()='utcdate']");
	    DateTime starttime = TivooTimeHandler.createTimeUTC(startfield.getStringValue());
	    Node endfield = n.selectSingleNode("./end/*[name()='utcdate']");
	    DateTime endtime = TivooTimeHandler.createTimeUTC(endfield.getStringValue());
	    eventlist.add(new TivooEvent(title, 
		    starttime, endtime, description));
	}
	return eventlist;
    }
    
    public boolean isThisThing(Document doc)
    {
    	String rootname = doc.getRootElement().getName();
    	return (rootname.contentEquals("events"));
    }
    
}