/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.strategic.planning.service;

import ec.edu.espe.strategic.planning.model.Node;
import ec.edu.espe.strategic.planning.util.MongoPersistence;
import ec.edu.espe.strategic.planning.util.code.StandardCode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

/**
 *
 * @author luis
 */
public class NodeService implements Serializable {

    private MongoPersistence conn = new MongoPersistence();
    private Datastore ds = conn.context();

    public String insert(Node node) {
        String code = "";
        try {
            if (node != null) {
                node.setCode(StandardCode.generatedCode("NOD", node));
                node.setTopic(node.getTopic().toUpperCase());
                node.setFlag(1);
                if (node.isIsroot()) {
                    node.setBackgroundColor("#008000");
                } else {
                    node.setBackgroundColor(StandardCode.RandomStringColor());
                }
                Key<Node> id = this.ds.save(node);
                System.out.println(id.toString());
            }
        } catch (Exception e) {
            System.out.println(">> insert node : " + e.getMessage());
        } finally {
            code = node.getCode();
        }
        return code;
    }

    public Boolean addNodes(Node node) {
        Boolean exito = Boolean.FALSE;
        try {
            if (node != null && node.getId() != null && node.getCode() != null) {
                if (!node.getChildren().isEmpty()) {
                    this.ds.save(node);
                }
            }
        } catch (Exception e) {
            System.out.println(">> addNodes : " + e.getMessage());
        } finally {
            exito = Boolean.TRUE;
        }
        return exito;
    }

    public Node findByCode(String code) {
        Node find = new Node();
        Query<Node> result = this.ds.find(Node.class).
                field("code").equal(code).
                field("flag").equal(1);
        if (result.asList() != null && !result.asList().isEmpty()) {
            find = result.asList().get(0);
        }
        return find;
    }

    public List<Node> findRootByName(String name) {
        List<Node> finds = new ArrayList<Node>();
        Pattern regexp = Pattern.compile(name.toUpperCase());
        Query<Node> result = this.ds.find(Node.class).
                filter("topic", regexp).
                field("isroot").equal(true).
                field("flag").equal(1);
        if (result.asList() != null && !result.asList().isEmpty()) {
            finds = result.asList();
        }
        return finds;
    }

    public Boolean update(Node node) {
        Query<Node> query = this.ds.createQuery(Node.class);
        query.and(
                query.criteria("code").equal(node.getCode())
        );
        UpdateOperations<Node> update = this.ds.createUpdateOperations(Node.class);
        update.set("topic", node.getTopic()).
                set("selectedType", node.getSelectedType()).
                set("direction", node.getDirection()).
                set("expanded", node.isExpanded()).
                set("isCreated", node.isIsCreated()).
                set("isroot", node.isIsroot()).
                set("children", node.getChildren()).
                set("level", node.getLevel()).
                set("numberLevels", node.getNumberLevels()).
                set("documents", node.getDocuments()).
                set("dimensions", node.getDimensions()).
                set("createdBy", node.getCreatedBy()).
                set("flag", node.getFlag());
        UpdateResults results = this.ds.update(query, update);
        return results.getUpdatedExisting();
    }

    public Boolean deleteFlag(Node code) {
        Query<Node> query = this.ds.createQuery(Node.class);
        query.and(
                query.criteria("code").equal(code.getCode())
        );
        UpdateOperations<Node> update = this.ds.createUpdateOperations(Node.class);
        update.set("flag", 0);
        UpdateResults results = this.ds.update(query, update);
        return results.getUpdatedExisting();
    }

    public List<Node> list() {
        List<Node> list = new ArrayList<Node>();
        Query<Node> result = this.ds.find(Node.class).
                field("flag").equal(1);
        if (result.asList() != null && !result.asList().isEmpty()) {
            list = result.asList();
        }
        return list;
    }

    public List<Node> listAsRoot() {
        List<Node> list = new ArrayList<Node>();
        Query<Node> result = this.ds.find(Node.class).
                field("isroot").equal(true).
                field("flag").equal(1);
        if (result.asList() != null && !result.asList().isEmpty()) {
            list = result.asList();
            Collections.reverse(list);
        }
        return list;
    }

}
