/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.strategic.planning.api.util;

import ec.edu.espe.strategic.planning.model.Node;
import ec.edu.espe.strategic.planning.service.NodeService;
import java.util.List;

/**
 *
 * @author luis
 */
public class NodeUtil {

    private NodeService service;

    public NodeUtil() {
        this.service = new NodeService();
    }

    public void traverseGraphAndInsert(List<Node> nds, int n) {
        if (nds.isEmpty()) {
            System.out.println("*****");
        } else {
            n++;
            for (int i = 0; i < nds.size(); i++) {
                traverseGraphAndInsert(nds.get(i).getChildren(), n);
                nds.get(i).setLevel(n);
                String code = this.service.insert(nds.get(i));
                nds.set(i, this.service.findByCode(code));
            }
        }
    }
    

    public String insertGraphRoot(Node root) {
        String code = this.service.insert(root);
        if (code == null || code.equals("")) {
            code = "0";
        }
        return code;
    }

}
