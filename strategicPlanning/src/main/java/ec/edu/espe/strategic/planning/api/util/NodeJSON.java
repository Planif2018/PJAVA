/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.strategic.planning.api.util;

import ec.edu.espe.strategic.planning.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */
public class NodeJSON {

    private String code;
    private String topic;
    private String selectedType;
    private String direction;
    private Boolean expanded;
    private Boolean isCreated;
    private Boolean isroot;
    private String backgroundColor;
    private String color;
    private List<NodeJSON> children;
    private int level;
    private int numberLevels;
    private List<DocumentJSON> documents;
    private List<Dimension> dimensions;

    private String createdBy;
    private int flag;

    public NodeJSON() {
        this.children = new ArrayList<NodeJSON>();
        this.documents = new ArrayList<DocumentJSON>();
        this.dimensions = new ArrayList<Dimension>();
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Boolean isIsCreated() {
        return isCreated;
    }

    public void setIsCreated(Boolean isCreated) {
        this.isCreated = isCreated;
    }

    public Boolean isIsroot() {
        return isroot;
    }

    public void setIsroot(Boolean isroot) {
        this.isroot = isroot;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getColor() {
        return color;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNumberLevels() {
        return numberLevels;
    }

    public void setNumberLevels(int numberLevels) {
        this.numberLevels = numberLevels;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<NodeJSON> getChildren() {
        return children;
    }

    public void setChildren(List<NodeJSON> children) {
        this.children = children;
    }

    public List<DocumentJSON> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentJSON> documents) {
        this.documents = documents;
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.code != null ? this.code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NodeJSON other = (NodeJSON) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Node{" + "code=" + code + ", topic=" + topic + ", selectedType=" + selectedType + ", direction=" + direction + ", expanded=" + expanded + ", isCreated=" + isCreated + ", isroot=" + isroot + ", backgroundColor=" + backgroundColor + ", color=" + color + ", children=" + children + ", level=" + level + ", numberLevels=" + numberLevels + ", documents=" + documents + ", dimensions=" + dimensions + ", createdBy=" + createdBy + ", flag=" + flag + '}';
    }

}
