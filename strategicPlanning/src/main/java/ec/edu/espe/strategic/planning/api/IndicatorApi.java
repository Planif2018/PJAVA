/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.strategic.planning.api;

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
@Path("/ind/")
public class IndicatorApi {

    @POST
    @Path("/indicator")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response transaction(String json) {
        String result = "{message : \" no se han encontrado datos \", "
                + "query : \" " + json + " \"}";
        // return HTTP response 200 in case of success
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/indicator/{search}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response query(@PathParam("search") String search) {
        String result = "{message : \" no se han encontrado datos \"}";
        // return HTTP response 200 in case of success
        return Response.status(200).entity(result).build();
    }
    
    @GET
    @Path("/indicators")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        String result = "{message : \" no se han encontrado datos \"}";
        // return HTTP response 200 in case of success
        return Response.status(200).entity(result).build();
    }
}
