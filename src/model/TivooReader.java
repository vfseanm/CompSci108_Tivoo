package model;
import java.io.*;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.*;
import parsers.*;

public class TivooReader {

    private static ArrayList<ITivooParser> parsers = new ArrayList<ITivooParser>();
    static {
	parsers.add(new DukeCalParser());
	parsers.add(new GoogleCalParser());
    }
    
    public static List<TivooEvent> read(String input) throws DocumentException {
	SAXReader reader = new SAXReader();
	Document doc = reader.read(new File(input));
	ITivooParser theparser = findParser(doc);
	
	if (theparser == null)
	    throw new TivooException("Malformed XML file!");
	return theparser.convertToList(doc);
    }
    
    private static ITivooParser findParser(Document doc)
    {
    	for(ITivooParser p: parsers)
    	{
    		if (p.isThisThing(doc))
    			return p;
    	}
    	return null; 
    	
    }

}