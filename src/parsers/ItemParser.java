package parsers;

import model.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemParser {


    private List<Item> items;

    public ItemParser() {
        items = new ArrayList<>();
    }
    public List<Item> getItems (){
        return items;
    }

    /**
     * Метод парсит xml файл AS_ADM_HIERARCHY
     *
     * @param filePath
     * @throws Exception
     */
    public void parseItems(String filePath) throws Exception {
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        NodeList objectNodes = doc.getElementsByTagName("ITEM");
        for (int i = 0; i < objectNodes.getLength(); i++) {
            Element objectElement = (Element) objectNodes.item(i);
            try {
                int id = parseIntAttribute(objectElement, "ID");
                int objectId = parseIntAttribute(objectElement, "OBJECTID");
                int parentobjid = parseIntAttribute(objectElement, "PARENTOBJID");
                int changeId = parseIntAttribute(objectElement, "CHANGEID");
                int prevId = parseIntAttribute(objectElement, "PREVID");
                int nextId = parseIntAttribute(objectElement, "NEXTID");
                Date updateDate = parseDateAttribute(objectElement, "UPDATEDATE");
                Date startDate = parseDateAttribute(objectElement, "STARTDATE");
                Date endDate = parseDateAttribute(objectElement, "ENDDATE");
                int isActive = parseIntAttribute(objectElement, "ISACTIVE");


                Item item = new Item();
                item.setId(id);
                item.setObjectId(objectId);
                item.setParentObjId(parentobjid);
                item.setChangeId(changeId);
                item.setPrevId(prevId);
                item.setNextId(nextId);
                item.setUpdateDate(updateDate);
                item.setStartDate(startDate);
                item.setEndDate(endDate);
                item.setActive(isActive);

                items.add(item);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private int parseIntAttribute(Element element, String attributeName) throws NumberFormatException {
        String attributeValue = element.getAttribute(attributeName);
        if (!attributeValue.isEmpty()) {
            return Integer.parseInt(attributeValue);
        } else {
            return 0;
        }
    }

    private Date parseDateAttribute(Element element, String attributeName) throws ParseException {
        String attributeValue = element.getAttribute(attributeName);
        if (!attributeValue.isEmpty()) {
            return new SimpleDateFormat("yyyy-MM-dd").parse(attributeValue);
        } else {
            return null;
        }
    }

    private boolean parseBooleanAttribute(Element element, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        return Boolean.parseBoolean(attributeValue);
    }

    public int getItemDescriptions(int objectId) {
        int result =0;
            for (Item item : items) {
                if (objectId == (item.getObjectId())) {
                    result = item.getParentObjId();
                    break;
                }
            }
        return result;
    }

    /**
     * Получение parentId по changeId
     * @param changeId
     * @return
     */
    public int getParentObjIdByChangeId(int changeId) {
        int result =0;
        for (Item item : items) {
            if (changeId == item.getChangeId()) {
                result = (item.getParentObjId());
                break;
            }
        }
        return result;
    }
}
