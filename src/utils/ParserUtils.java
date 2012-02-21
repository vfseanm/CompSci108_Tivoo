package utils;
import java.util.*;
import org.dom4j.*;
import org.joda.time.*;
import model.*;
public class ParserUtils {

	public static String getInfo(Node n, String key)
	{
		String tofind = "./*[name()='"+key+"']";
		Node info = n.selectSingleNode(tofind);
		return info.getStringValue();
	}
	
	public static String getInfo2(Node n, String key)
	{
		String tofind = "//*[name()='"+key+"']";
		Node info = n.selectSingleNode(tofind);
		return info.getStringValue();
	}
	
}
