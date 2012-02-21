package writers;

import java.io.*;
import java.util.*;

import org.joda.time.*;
import org.rendersnake.*;

import static org.rendersnake.HtmlAttributesFactory.*;

import org.dom4j.*;
import org.dom4j.io.*;

import utils.TivooUtils;
import utils.writerUtility;

import model.*;

public class VerticalTableWriter implements ITivooWriter {

	public void write(List<TivooEvent> eventlist, String outputsummary,
			String outputdetails)
			throws IOException {
		TivooUtils.clearDirectory(outputdetails);
		FileWriter summarywriter = new FileWriter(outputsummary);
		Collections.sort(eventlist, TivooEvent.EventTimeComparator);
		HtmlCanvas summary = new HtmlCanvas(summarywriter);
		HashSet<Integer> writtenstartdate = new HashSet<Integer>();
		writerUtility.style(summary, "");
		for (TivooEvent e : eventlist) {
			int h = Hours.hoursBetween(e.getStart(), e.getEnd()).getHours();
			if (h >= 24)
				continue;
			String detailURL = outputdetails + eventlist.indexOf(e) + ".html";
			DateTime localstart = TivooTimeHandler.createLocalTime(e.getStart());
			DateTime localend = TivooTimeHandler.createLocalTime(e.getEnd());
			String outputdetail = outputdetails.substring(outputdetails.indexOf("/")+1) + eventlist.indexOf(e) + ".html";
			if (!writtenstartdate.contains(localstart.getDayOfYear())) {
				writerUtility.writeTableHead(summary, localstart);
				writtenstartdate.add(localstart.getDayOfYear());
			}
			writerUtility.writeSummary(summary, e, localstart, localend, outputdetail);
			ArrayList<TivooEvent> oneevent = new ArrayList<TivooEvent>();
			oneevent.add(e);
			new DetailPageWriter().write(oneevent, outputsummary, detailURL);
		}
		summary._table().write("\n")._body()._html();
		summarywriter.close();
	}

}
