package parsers;

import model.AddressObject;
import model.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class AddressObjectParser {
    private List<AddressObject> addressObjects;

    public AddressObjectParser() {
        addressObjects = new ArrayList<>();
    }

    public List<AddressObject> getAddressObjects() {
        return addressObjects;
    }

    /**
     * Метод парсит xml файл AS_ADDR_OBJ_PATH
     *
     * @param filePath
     * @throws Exception
     */
    public void parseAddressObject(String filePath) throws Exception {
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        NodeList objectNodes = doc.getElementsByTagName("OBJECT");
        for (int i = 0; i < objectNodes.getLength(); i++) {
            Element objectElement = (Element) objectNodes.item(i);
            try {
                int id = parseIntAttribute(objectElement, "ID");
                int objectId = parseIntAttribute(objectElement, "OBJECTID");
                int changeId = parseIntAttribute(objectElement, "CHANGEID");
                int level = parseIntAttribute(objectElement, "LEVEL");
                int operTypeId = parseIntAttribute(objectElement, "OPERTYPEID");
                int prevId = parseIntAttribute(objectElement, "PREVID");
                int nextId = parseIntAttribute(objectElement, "NEXTID");
                Date updateDate = parseDateAttribute(objectElement, "UPDATEDATE");
                Date startDate = parseDateAttribute(objectElement, "STARTDATE");
                Date endDate = parseDateAttribute(objectElement, "ENDDATE");
                int isActual = parseIntAttribute(objectElement, "ISACTUAL");
                int isActive = parseIntAttribute(objectElement, "ISACTIVE");

                String objectGuid = objectElement.getAttribute("OBJECTGUID");
                String name = objectElement.getAttribute("NAME");
                String typeName = objectElement.getAttribute("TYPENAME");

                AddressObject addressObject = new AddressObject();
                addressObject.setId(id);
                addressObject.setObjectId(objectId);
                addressObject.setObjectGuid(objectGuid);
                addressObject.setChangeId(changeId);
                addressObject.setName(name);
                addressObject.setTypeName(typeName);
                addressObject.setLevel(level);
                addressObject.setOperTypeId(operTypeId);
                addressObject.setPrevId(prevId);
                addressObject.setNextId(nextId);
                addressObject.setUpdateDate(updateDate);
                addressObject.setStartDate(startDate);
                addressObject.setEndDate(endDate);
                addressObject.setActual(isActual);
                addressObject.setActive(isActive);

                addressObjects.add(addressObject);
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

    public List<String> getAddressDescriptionsOnDate(Date date, List<Integer> objectIds) {
        List<String> result = new ArrayList<>();
        for (int objectId : objectIds) {
            for (AddressObject address : addressObjects) {
                if ((objectId == address.getObjectId()) && (address.getStartDate().compareTo(date) <= 0) && (address.getEndDate().compareTo(date) >= 0)) {
                    result.add(objectId + ": " + address.getTypeName() + " " + address.getName());
                    break;
                }
            }
        }
        return result;
    }

    public Map<Integer, String> getAddressByParentObjId(Integer objectId) {
        Map<Integer, String> result = new HashMap<>();

        for (AddressObject address : addressObjects) {
            if (objectId == address.getObjectId() && address.isActual() == 1) {
                result.put(objectId, (address.getTypeName() + " " + address.getName()));
            }
        }
        return result;
    }

    public Map<Integer, String> getProezdDescriptions() {
        Map<Integer, String> result = new HashMap<>();
        for (AddressObject address : addressObjects) {
            if (address.getTypeName().equalsIgnoreCase("проезд") && address.isActual() == 1) {
                result.put(address.getObjectId(), address.getTypeName() + " " + address.getName());
            }
        }
        return result;
    }

    public int getChangeIdByObjectId(int objectId) {
        int result = 0;

        for (AddressObject address : addressObjects) {
            if (objectId == (address.getObjectId())) {
                result = address.getChangeId();
                break;
            }
        }
        return result;
    }
}
