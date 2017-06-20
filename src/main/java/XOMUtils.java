import nu.xom.Element;
import nu.xom.Elements;

/**
 * @author Rodrigo A. Bartels
 */
public class XOMUtils {

    public static Boolean extractBooleanValue(Element element, String label, boolean required){
        Element child = element.getFirstChildElement(label);
        String content = null;

        if(child != null)
            content = child.getValue();
        else if(required)
            throw new IllegalArgumentException("Missing required value " + label);

        return content != null ? Boolean.valueOf(content) : false;
    }

    public static String extractStringValue(Element element, String label, boolean required){
        Element child = element.getFirstChildElement(label);
        String content = null;

        if(child != null)
            content = child.getValue();
        else if(required)
            throw new IllegalArgumentException("Missing required value " + label);

        return content;
    }

    public static Integer extractIntegerValue(Element element, String label, boolean required){
        Element child = element.getFirstChildElement(label);
        String content = null;

        if(child != null)
            content = child.getValue();
        else if(required)
            throw new IllegalArgumentException("Missing required value " + label);

        return content != null ? Integer.parseInt(content) : 0;
    }

    public static Element extractChildByName(Element element, String childName, boolean strict){
        Elements elements = element.getChildElements(childName);

        if(elements.size() != 1 && strict || elements.size() == 0)
            throw new IllegalArgumentException("There is no child named " + childName);
        else{
            return elements.get(0);
        }
    }
}
