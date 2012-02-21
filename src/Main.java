import org.joda.time.DateTime;

import controller.TivooController;
import model.TivooModel;
import model.TivooReader;
import model.TivooTimeHandler;

public class Main {

    public static void main(String[] args) {
	
	String input = "googlecal.xml";
	String outputsummary = "output/testhtml_google.html";
	String outputdetails = "output/details_google/";
	DateTime startdate = TivooTimeHandler.createTimeUTC("20110301T0000");
	DateTime enddate = startdate.plusDays(180);
	// the above will come from a GUI eventually
	
	TivooController controller = new TivooController(); 
	
	try {
		controller.read(input);
		//controller.filterByTime(startdate, enddate);
		//controller.filterByKeyword("Duke");
		controller.doWriteVerticalTable(outputsummary, outputdetails, 
				startdate, enddate);
	} 
	catch (Exception e) {
	    e.printStackTrace();
	}
	
    }

}