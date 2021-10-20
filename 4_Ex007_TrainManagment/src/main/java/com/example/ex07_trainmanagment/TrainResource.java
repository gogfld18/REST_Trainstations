package com.example.ex07_trainmanagment;

import com.example.ex07_trainmanagment.bl.Train;
import com.example.ex07_trainmanagment.bl.TrainDatabaseStatic;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.security.KeyException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.RecursiveTask;

@Path("/train")
public class TrainResource {

    @Context
    UriInfo uriInfo;

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getStations(@PathParam("id") int id) {
        try{
            Train t =TrainDatabaseStatic.getInstance().getById(id);
            return Response.ok(t).build();
        }catch (NoSuchElementException noSuchElementException){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces("application/json")
    public List<Train> allTrains(){
        return TrainDatabaseStatic.getInstance().getTrainList();
    }

    @POST
    @Produces("application/json")
    public Response addTrain(Train t){
        try {
            TrainDatabaseStatic.getInstance().addTrain(t);
        }catch (KeyAlreadyExistsException keyException){
            return Response.status(Response.Status.CONFLICT).build();
        }

        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        uri.path(""+t.getId());

        return Response.created(uri.build()).entity(t).build();
    }

    @POST
    @Produces("application/json")
    @Path("/{id}")
    public Response addTrainStation(@PathParam("id") int id, String station){
        try {
            TrainDatabaseStatic.getInstance().addStation(id,station);
        }catch (KeyAlreadyExistsException keyException){
            return Response.status(Response.Status.CONFLICT).build();
        }

        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        uri.path(""+id);

        return Response.created(uri.build()).entity(TrainDatabaseStatic.getInstance().getById(id)).build();
    }
}