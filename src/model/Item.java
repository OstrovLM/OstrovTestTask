package model;

import java.util.Date;

/**
 * Класс описывает структуру объектов из файла AS_ADM_HIERARCHY.xml
 */
public class Item {
    private int id;
    private int objectId;       //идентификатор адреса
    private int parentObjId;    //ид родительского адреса
    private int changeId;
    private int prevId;
    private int nextId;
    private Date updateDate;
    private Date startDate;   // начало действия связи
    private Date endDate;     // окончание действия связи
    private int isActive;   // признак актуальности связи

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

    public int getParentObjId() {
        return parentObjId;
    }

    public void setParentObjId(int parentObjId) {
        this.parentObjId = parentObjId;
    }

    public int getChangeId() {
        return changeId;
    }

    public void setChangeId(int changeId) {
        this.changeId = changeId;
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

    public int isActive() {
        return isActive;
    }

    public void setActive(int active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", parentObjId=" + parentObjId +
                ", changeId=" + changeId +
                ", prevId=" + prevId +
                ", nextId=" + nextId +
                ", updateDate='" + updateDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}

