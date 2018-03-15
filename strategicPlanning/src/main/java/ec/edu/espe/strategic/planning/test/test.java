/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.strategic.planning.test;

import com.google.gson.Gson;
import ec.edu.espe.strategic.planning.model.Dimension;
import ec.edu.espe.strategic.planning.model.Document;
import ec.edu.espe.strategic.planning.model.Node;
import ec.edu.espe.strategic.planning.model.Objective;

/**
 *
 * @author luis
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
  
        Node n1 = new Node();
        n1.setCode("TEST20171204");
        n1.setTopic("NIVEL 1");

        Document d1 = new Document();
        d1.setTitle("TESTDOC120171204 - 1");
        d1.setText("TEST DOCUMENT'S DATA 0001");
        
        Document d2 = new Document();
        d1.setTitle("TESTDOC120171204 - 2");
        d1.setText("TEST DOCUMENT'S DATA 0002");
        
        d1.getSubDocuments().add(d2);
        
        n1.getDocuments().add(d1);
        
        Dimension dn1 = new Dimension();
        dn1.setDescription("PERSPECTIVA-1");
        
        Objective obj1 = new Objective(n1.getCode());
        obj1.setCode("OBJ-1-TEST20171204");
        obj1.setDescription("TEST OBJ PERSPECTIVES");
        obj1.setType("ESTRATEGICO");
        
        Objective obj2 = new Objective(n1.getCode());
        obj2.setCode("OBJ-2-TEST20171204");
        obj2.setDescription("TEST OBJ PERSPECTIVES");
        obj2.setType("ESTRATEGICO");
        
        dn1.getObjectives().add(obj1);
        dn1.getObjectives().add(obj2);
        
        Dimension dn2 = new Dimension();
        dn2.setDescription("PERSPECTIVA-2");
        
        Objective obj3 = new Objective(n1.getCode());
        obj3.setCode("OBJ-3-TEST20171204");
        obj3.setDescription("TEST OBJ PERSPECTIVES");
        obj3.setType("ESTRATEGICO");
        
        Objective obj4 = new Objective(n1.getCode());
        obj4.setCode("OBJ-4-TEST20171204");
        obj4.setDescription("TEST OBJ PERSPECTIVES");
        obj4.setType("ESTRATEGICO");
        
        dn2.getObjectives().add(obj3);
        dn2.getObjectives().add(obj4);
        
        n1.getDimensions().add(dn1);
        n1.getDimensions().add(dn2);
        
        n1.getChildren().add(n1);
        
        
        Gson jsonObject = new Gson();
        String json = jsonObject.toJson(n1, Node.class);
        
        System.out.println(json);
        
        Gson strObject = new Gson();
        
        Node n2 = strObject.fromJson(json, Node.class);
        
        System.out.println(n2.getTopic());
       

    }

}
