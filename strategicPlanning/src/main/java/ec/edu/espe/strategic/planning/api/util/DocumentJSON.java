/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.strategic.planning.api.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */

public class DocumentJSON {

    private String title;
    private String text;
    private List<DocumentJSON> subDocuments;
    
    private String createdBy;

    public DocumentJSON() {
        this.subDocuments = new ArrayList<DocumentJSON>();
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

    public List<DocumentJSON> getSubDocuments() {
        return subDocuments;
    }

    public void setSubDocuments(List<DocumentJSON> subDocuments) {
        this.subDocuments = subDocuments;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
}
