/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.strategic.planning.model;

import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;

/**
 *
 * @author luis
 */
@Embedded
public class Document {

    private String title;
    private String text;
    
    @Embedded
    private List<Document> subDocuments;
    
    private String createdBy;

    public Document() {
        this.subDocuments = new ArrayList<Document>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Document> getSubDocuments() {
        return subDocuments;
    }

    public void setSubDocuments(List<Document> subDocuments) {
        this.subDocuments = subDocuments;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
}
