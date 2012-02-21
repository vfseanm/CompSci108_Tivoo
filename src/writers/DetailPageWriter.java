package writers;
import static org.rendersnake.HtmlAttributesFactory.*;
import java.io.*;
import java.util.*;
import org.rendersnake.HtmlCanvas;

import utils.writerUtility;
import model.*;

public class DetailPageWriter implements ITivooWriter {

    public void write(List<TivooEvent> eventlist, String outputsummary,
	    String outputdetails)
	    throws IOException {
	if (new File(outputdetails).isDirectory()) {
	    for (TivooEvent e: eventlist) {
		String detailURL = outputdetails + eventlist.indexOf(e) + ".html";
		writeOneDetailPage(e, outputsummary, detailURL);
	    }
	}
	else writeOneDetailPage(eventlist.get(0), outputsummary, outputdetails);
    }
    
    private void writeOneDetailPage(TivooEvent e, String outputsummary, String detailURL)  throws IOException {
    	FileWriter detailwriter = new FileWriter(detailURL);
		HtmlCanvas detail = new HtmlCanvas(detailwriter);
		writerUtility.style(detail, "../");
		writerUtility.writeDetail(detail, e, outputsummary);
		detailwriter.close();
    }
    
}