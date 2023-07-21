package model;

import java.util.Date;

/**
 * Класс описывает структуру объектов из файла AS_ADDR_OBJ.xml
 */
public class AddressObject {
    private int id;
    private int objectId;       //ид адреса
    private String objectGuid;
    private int changeId;
    private String name;        // название адреса
    private String typeName;    //тип адреса
    private int level;
    private int operTypeId;
    private int prevId;
    private int nextId;
    private Date updateDate;
    private Date startDate;   // начало действия связи
    private Date endDate;     // окончание действия связи
    private int isActual;   // признак актуальности связи
    private int isActive;   // признак актуальности связи



    @Override
    public String toString() {
        return "AddressObject{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", objectGuid='" + objectGuid + '\'' +
                ", changeId=" + changeId +
                ", name='" + name + '\'' +
                ", typeName='" + typeName + '\'' +
                ", level=" + level +
                ", operTypeId=" + operTypeId +
                ", prevId=" + prevId +
                ", nextId=" + nextId +
                ", updateDate='" + updateDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", isActual=" + isActual +
                ", isActive=" + isActive +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getObjectGuid() {
        return objectGuid;
    }

    public void setObjectGuid(String objectGuid) {
        this.objectGuid = objectGuid;
    }

    public int getChangeId() {
        return changeId;
    }

    public void setChangeId(int changeId) {
        this.changeId = changeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOperTypeId() {
        return operTypeId;
    }

    public void setOperTypeId(int operTypeId) {
        this.operTypeId = operTypeId;
    }

    public int getPrevId() {
        return prevId;
    }

    public void setPrevId(int prevId) {
        this.prevId = prevId;
    }

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int isActual() {
        return isActual;
    }

    public void setActual(int actual) {
        isActual = actual;
    }

    public int isActive() {
        return isActive;
    }

    public void setActive(int active) {
        isActive = active;
    }


}
