/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.strategic.planning.service;

import ec.edu.espe.strategic.planning.model.Objective;
import ec.edu.espe.strategic.planning.util.MongoPersistence;
import ec.edu.espe.strategic.planning.util.code.StandardCode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

/**
 *
 * @author luis
 */
public class ObjectiveService implements Serializable {

    private MongoPersistence conn = new MongoPersistence();
    private Datastore ds = conn.context();

    public Boolean insert(Objective objective) {
        Boolean exito = Boolean.FALSE;
        try {
            if (objective != null) {
                objective.setCode(StandardCode.generatedCode("OBJ", objective));
                objective.setFlag(1);
                this.ds.save(objective);
            }
        } catch (Exception e) {
            System.out.println(">> inster objective  : " + e.getMessage());
        } finally {
            exito = Boolean.TRUE;
        }
        return exito;
    }

    public Boolean addObjectivesAndIndicators(Objective objective) {
        Boolean exito = Boolean.FALSE;
        try {
            if (objective != null && objective.getId() != null && objective.getCode() != null) {
                if (!objective.getRelations().isEmpty() || !objective.getIndicators().isEmpty()) {
                    this.ds.save(objective);
                }
            }
        } catch (Exception e) {
            System.out.println(">> addObjectivesAndIndicators : " + e.getMessage());
        } finally {
            exito = Boolean.TRUE;
        }
        return exito;
    }

    public Objective findByCode(String code) {
        Objective find = new Objective();
        Query<Objective> result = this.ds.find(Objective.class).
                field("code").equal(code).
                field("flag").equal(1);
        if (result.asList() != null && !result.asList().isEmpty()) {
            find = result.asList().get(0);
        }
        return find;
    }

    public Objective findByNode(String node) {
        Objective find = new Objective();
        Query<Objective> result = this.ds.find(Objective.class).
                field("node").equal(node).
                field("flag").equal(1);
        if (result.asList() != null && !result.asList().isEmpty()) {
            find = result.asList().get(0);
        }
        return find;
    }

    public Objective findByDescription(String description) {
        Objective find = new Objective();
        Query<Objective> result = this.ds.find(Objective.class).
                field("description").equal(description).
                field("flag").equal(1);
        if (result.asList() != null && !result.asList().isEmpty()) {
            find = result.asList().get(0);
        }
        return find;
    }

    public Boolean update(Objective objective) {
        Query<Objective> query = this.ds.createQuery(Objective.class);
        query.and(
                query.criteria("code").equal(objective.getCode())
        );
        UpdateOperations<Objective> update = this.ds.createUpdateOperations(Objective.class);
        update.set("code", objective.getCode()).
                set("node", objective.getNode()).
                set("type", objective.getType()).
                set("description", objective.getDescription()).
                set("strategies", objective.getStrategies()).
                set("indicators", objective.getIndicators()).
                set("relations", objective.getRelations()).
                set("createdBy", objective.getCreatedBy()).
                set("flag", objective.getFlag());
        UpdateResults results = this.ds.update(query, update);
        return results.getUpdatedExisting();
    }

    public Boolean deleteFlag(String code) {
        Query<Objective> query = this.ds.createQuery(Objective.class);
        query.and(
                query.criteria("code").equal(code)
        );
        UpdateOperations<Objective> update = this.ds.createUpdateOperations(Objective.class);
        update.set("flag", 0);
        UpdateResults results = this.ds.update(query, update);
        return results.getUpdatedExisting();
    }

    public List<Objective> list() {
        List<Objective> list = new ArrayList<Objective>();
        Query<Objective> result = this.ds.find(Objective.class).
                field("flag").equal(1);
        if (result.asList() != null && !result.asList().isEmpty()) {
            list = result.asList();
        }
        return list;
    }
}
