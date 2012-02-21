package utils;

import static org.rendersnake.HtmlAttributesFactory.class_;
import static org.rendersnake.HtmlAttributesFactory.colspan;
import static org.rendersnake.HtmlAttributesFactory.href;
import static org.rendersnake.HtmlAttributesFactory.width;

import java.io.FileWriter;
import java.io.IOException;

import model.TivooEvent;

import org.joda.time.DateTime;
import org.rendersnake.HtmlCanvas;

public class writerUtility {

	public static void style(HtmlCanvas content, String folder)
			throws IOException {
		content.html()
				.head()
				.link(href(folder + "styles/detail_page_style.css")
						.type("text/css").rel("stylesheet").media("screen"))
				._head().body().write("\n").table(width("70%").align("center"));
	}
	
	public static void writeSummary(HtmlCanvas content, TivooEvent e, DateTime localstart, DateTime localend, String outputdetail) throws IOException {
		content.tr().write("\n")
		.th(class_("time"))
		.write(localstart.toString("HH:mm") + "-" + localend.toString("HH:mm"))
		._th()
		.write("\n")
		.td()
		.a(href(outputdetail))
		.write(e.getTitle())._a()._td().write("\n")._tr()
		.write("\n");
	}
	
	public static void writeDetail(HtmlCanvas content, TivooEvent e, String summaryURL) throws IOException {
		content.tr().th(colspan("2").class_("title")).write(e.getTitle())
		._th().write("\n")._tr().tr().td().write(e.getDescription())
		._td()._tr().tr().td(class_("back"))
		.a(href("../../" + summaryURL)).write("Back to summary")._a()
		._td()._tr()._table()._body()._html();
	}
	
	public static void writeTableHead(HtmlCanvas content, DateTime localstart) throws IOException {
		content.tr().write("\n");
		content.th(colspan("2").align("left").class_("day"))
				.write(localstart.toString("EEE, MMM dd"))._th()._tr();
	}
}
