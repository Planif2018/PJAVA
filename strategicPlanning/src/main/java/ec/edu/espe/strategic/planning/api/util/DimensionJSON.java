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

public class DimensionJSON {

    private String description;
    private List<ObjectiveJSON> objectives;
    private String createdBy;

    public DimensionJSON() {
        this.objectives = new ArrayList<ObjectiveJSON>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ObjectiveJSON> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<ObjectiveJSON> objectives) {
        this.objectives = objectives;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    
}
