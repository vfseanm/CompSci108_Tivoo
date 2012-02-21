package parsers;

import java.util.List;

import model.TivooEvent;

import org.dom4j.Document;

public interface ITivooParser {

    public List<TivooEvent> convertToList(Document doc);
 
    public boolean isThisThing(Document doc);
}
