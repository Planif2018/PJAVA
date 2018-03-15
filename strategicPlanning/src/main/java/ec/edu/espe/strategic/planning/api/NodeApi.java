/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.strategic.planning.api;

import com.google.gson.Gson;
import ec.edu.espe.strategic.planning.api.util.NodeJSON;
import ec.edu.espe.strategic.planning.api.util.NodeUtil;
import ec.edu.espe.strategic.planning.model.Node;
import ec.edu.espe.strategic.planning.service.NodeService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author luis
 */
@Path("/n/")
public class NodeApi {

    private NodeService service = new NodeService();

    @POST
    @Path("/graph")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(String json) {
        String result = "{\"state\" : 0}";
        try {
            Gson objetJSON = new Gson();
            NodeJSON rootJSON = objetJSON.fromJson(json, NodeJSON.class);
            if (rootJSON != null && !rootJSON.getChildren().isEmpty()) {
                Gson objectStr = new Gson();
                String str = objectStr.toJson(rootJSON, NodeJSON.class);
                Gson srtObj = new Gson();
                Node root = srtObj.fromJson(str, Node.class);
                NodeUtil nodeUtil = new NodeUtil();
                nodeUtil.traverseGraphAndInsert(root.getChildren(), 0);
                nodeUtil.insertGraphRoot(root);
                result = "{\"state\" : 1}";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = "{\"state\" : 0}";
        } finally {
            System.out.println(">> " + result);
        }
        // return HTTP response 200 in case of success
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/node/{search}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response query(@PathParam("search") String search) {
        String result = "{ \"state\" : 0 }";
        try {
            List<Node> nodes = this.service.findRootByName(search);
            Gson objectList = new Gson();
            if (nodes != null && !nodes.isEmpty()) {
                result = objectList.toJson(nodes);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // return HTTP response 200 in case of success
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/graphs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAsRoot() {
        String result = "{ \"state\" : 0 }";
        try {
            List<Node> nodes = this.service.listAsRoot();
            Gson objectList = new Gson();
            if (nodes != null && !nodes.isEmpty()) {
                result = objectList.toJson(nodes);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // return HTTP response 200 in case of success
        return Response.status(200).entity(result).build();
    }
}
