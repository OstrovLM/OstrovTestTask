package service;

import parsers.AddressObjectParser;
import parsers.ItemParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GetAdressService {
    AddressObjectParser addressObjectParser = new AddressObjectParser();
    ItemParser itemParser = new ItemParser();
    String asAdmHierarchyPath;
    String asAddrObjPath;
    String objectsString;
    String inputDateStr;

    private final String AS_ADDR_OBJ_PATH = "AS_ADDR_OBJ_PATH";
    private final String AS_ADM_HIERARCHY_PATH = "AS_ADM_HIERARCHY_PATH";
    private final String PROPERTY_PATH = "resourses/config.properties";

    public GetAdressService() {
        try {
            this.loadProperties();
            addressObjectParser.parseAddressObject(asAddrObjPath);
            itemParser.parseItems(asAdmHierarchyPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод загрузки параметров из config-файла
     * Вызывается при создании объекта
     */
    private void loadProperties() {
        try {
            Properties properties = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTY_PATH);
            properties.load(input);
            input.close();
            asAddrObjPath = properties.getProperty(AS_ADDR_OBJ_PATH);
            asAdmHierarchyPath = properties.getProperty(AS_ADM_HIERARCHY_PATH);
            objectsString = properties.getProperty("OBJECTID");
            inputDateStr = properties.getProperty("DATE");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод получения полного адреса
     */
    public void getFullAddress() {
        try {
            itemParser.parseItems(asAdmHierarchyPath);
            Map<Integer, String> proezdDescriptions = addressObjectParser.getProezdDescriptions();
            for (Map.Entry<Integer, String> entry : proezdDescriptions.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue();
                String lastResult = printNestedAddresses(key, value);
                if (lastResult != null) {
                    System.out.println("Final Result: " + lastResult);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод получения конечного адреса
     * @param parentId
     * @param value
     * @return
     */
    private String printNestedAddresses(Integer parentId, String value) {
        try {
            int changeId = addressObjectParser.getChangeIdByObjectId(parentId);
            int parentObjId = itemParser.getParentObjIdByChangeId(changeId);
            String lastResult = null;

            if (parentObjId != 0) {
                Map<Integer, String> newAdress = addressObjectParser.getAddressByParentObjId(parentObjId);
                for (Map.Entry<Integer, String> entry : newAdress.entrySet()) {
                    Integer key1 = entry.getKey();
                    String value1 = entry.getValue();
                    lastResult = printNestedAddresses(key1, value1 + ", " + value);
                }
            } else {
                lastResult = value;
            }
            return lastResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод выводит адреса в зависимости от даты
     * и objectId
     */
    public void getAddressByDate() {
        try {
            String[] objectIdsArray = objectsString.split(",\\s*"); // Разделение строки по запятым с возможными пробелами после запятых
            List<Integer> inputObjectIds = new ArrayList<>();
            for (String objectId : objectIdsArray) {
                inputObjectIds.add(Integer.valueOf(objectId.trim())); // Преобразование каждой подстроки в число и добавление в список
            }

            Date inputDate = new SimpleDateFormat("yyyy-MM-dd").parse(inputDateStr);

            List<String> descriptions = addressObjectParser.getAddressDescriptionsOnDate(inputDate, inputObjectIds);

            for (String description : descriptions) {
                System.out.println(description);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

