package controller;
import java.io.*;
import java.util.*;

import org.dom4j.DocumentException;
import org.joda.time.*;
import model.*;
import writers.*;
import filters.*;

public class TivooController {
	private TivooModel myModel;
	
	public TivooController()
	{
		myModel = new TivooModel();
	}

    public void filterByTime(DateTime startdate, DateTime enddate) {
    	myModel.filterByTime(startdate, enddate);
    }
    
    public void filterByKeyword(String keyword) {
    	myModel.filterByKeyword(keyword);

    }
    
    public void doWriteVerticalTable(String outputsummary, 
	    String outputdetails, DateTime startdate, DateTime enddate) {
	myModel.writeVerticalTable(outputsummary, outputdetails, startdate, enddate);
    }
    
    public List<TivooEvent> read(String input) throws DocumentException
    {
    	return myModel.read(input);
    }
    
}