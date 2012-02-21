package model;
import java.io.IOException;
import java.util.*;

import org.dom4j.DocumentException;
import org.joda.time.*;

import writers.VerticalTableWriter;

import controller.*;
import filters.Filter;

public class TivooModel {

    private List<TivooEvent> eventlist;
    
    public TivooModel()
    {
    }
    
    public void writeVerticalTable(String outputsummary, 
	    String outputdetails, DateTime startdate, DateTime enddate)
    {
    	try {
    	    new VerticalTableWriter().write(eventlist, outputsummary, outputdetails);
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    }
    
    public List<TivooEvent> read(String input) throws DocumentException
    {
    	eventlist = TivooReader.read(input);
    	return eventlist;
    }
    
    public void filterByTime(DateTime startdate, DateTime enddate)
    {
    	eventlist = Filter.filterByTime(eventlist, startdate, enddate);
    }
    
    public void filterByKeyword(String keyword)
    {
    	eventlist = Filter.filterByKeyword(eventlist, keyword);
    }
    
}

// model view controller pattern!
// everything is done in initialize right now, but
// main should have all the steps that the program goes through.
// model does things only when the controller tells it to. create the controller in main.
// put filter and other data manipulation methods in model.

// in reader, the way we pick a parser is not robust. Make a isThisThing method. Could have a default one to check first
// word, override if necessary.
// make the view higher-level with the writers extending it.
// make the html writer use methods like writehead(css), writetable(string list) etc
// make a parsing superclass to combine shared code between the parsers. 
