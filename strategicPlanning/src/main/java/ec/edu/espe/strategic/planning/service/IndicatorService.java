/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.strategic.planning.service;

import ec.edu.espe.strategic.planning.model.Indicator;
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
public class IndicatorService implements Serializable {

    private MongoPersistence conn = new MongoPersistence();
    private Datastore ds = conn.context();

    public Boolean insert(Indicator indicator) {
        Boolean exito = Boolean.FALSE;
        try {
            if (indicator != null) {
                indicator.setCode(StandardCode.generatedCode("IND", indicator));
                indicator.setFlag(1);
                this.ds.save(indicator);
            }
        } catch (Exception e) {
            System.out.println(">> insert indicator : " + e.getMessage());
        } finally {
            exito = Boolean.TRUE;
        }
        return exito;
    }

    public Indicator findByCode(String code) {
        Indicator find = new Indicator();
        Query<Indicator> result = this.ds.find(Indicator.class).
                field("code").equal(code).
                field("flag").equal(1);
        if (result.asList() != null && !result.asList().isEmpty()) {
            find = result.asList().get(0);
        }
        return find;
    }

    public Indicator findByDescription(String description) {
        Indicator find = new Indicator();
        Query<Indicator> result = this.ds.find(Indicator.class).
                field("description").equal(description).
                field("flag").equal(1);
        if (result.asList() != null && !result.asList().isEmpty()) {
            find = result.asList().get(0);
        }
        return find;
    }

    public Boolean update(Indicator indicator) {
        Query<Indicator> query = this.ds.createQuery(Indicator.class);
        query.and(
                query.criteria("code").equal(indicator.getCode())
        );
        UpdateOperations<Indicator> update = this.ds.createUpdateOperations(Indicator.class);
        update.set("code", indicator.getCode()).
                set("type", indicator.getType()).
                set("description", indicator.getDescription()).
                set("createdBy", indicator.getCreatedBy()).
                set("flag", indicator.getFlag());
        UpdateResults results = this.ds.update(query, update);
        return results.getUpdatedExisting();
    }

    public Boolean deleteFlag(String code) {
        Query<Indicator> query = this.ds.createQuery(Indicator.class);
        query.and(
                query.criteria("code").equal(code)
        );
        UpdateOperations<Indicator> update = this.ds.createUpdateOperations(Indicator.class);
        update.set("flag", 0);
        UpdateResults results = this.ds.update(query, update);
        return results.getUpdatedExisting();
    }

    public List<Indicator> list() {
        List<Indicator> list = new ArrayList<Indicator>();
        Query<Indicator> result = this.ds.find(Indicator.class).
                field("flag").equal(1);
        if (result.asList() != null && !result.asList().isEmpty()) {
            list = result.asList();
        }
        return list;
    }
}
