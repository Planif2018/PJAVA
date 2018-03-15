/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.strategic.planning.model;

import ec.edu.espe.strategic.planning.util.entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author luis
 */
@Entity
@Indexes({
    @Index(fields = @Field("code"), options = @IndexOptions(unique = true)),
    @Index(fields = @Field("flag"))})
public class Objective extends BaseEntity {

    private String code;
    private String node;
    private String type;
    private String description;
    private List<String> strategies;

    @Reference
    private List<Indicator> indicators;
    @Reference
    private List<Objective> relations;

    private String createdBy;
    private int flag;

    public Objective() {
        this.indicators = new ArrayList<Indicator>();
        this.strategies = new ArrayList<String>();
        this.relations = new ArrayList<Objective>();
    }

    public Objective(String node) {
        this.node = code;
        this.indicators = new ArrayList<Indicator>();
        this.strategies = new ArrayList<String>();
        this.relations = new ArrayList<Objective>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }

    public List<String> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<String> strategies) {
        this.strategies = strategies;
    }

    public List<Objective> getRelations() {
        return relations;
    }

    public void setRelations(List<Objective> relations) {
        this.relations = relations;
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
        int hash = 7;
        hash = 59 * hash + (this.code != null ? this.code.hashCode() : 0);
        hash = 59 * hash + (this.description != null ? this.description.hashCode() : 0);
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
        final Objective other = (Objective) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        return true;
    }

}
