package com.example.rest;

import com.example.ejb.ExampleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class ExampleResource {

    @Inject
    private ExampleService exampleService;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getHello() {
        return Response.ok(exampleService.getMessage()).build();
    }
} 