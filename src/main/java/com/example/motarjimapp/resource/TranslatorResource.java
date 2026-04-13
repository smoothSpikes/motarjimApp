package com.example.motarjimapp.resource;

import com.example.motarjimapp.model.TranslationRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import com.example.motarjimapp.service.LLMService;
import jakarta.ws.rs.core.Response;
import jakarta.annotation.security.RolesAllowed;



@Path("/translate")
public class TranslatorResource {

    @Inject
    LLMService service;


    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("USER")
    @POST
    public Response getTranslation(TranslationRequest request) {

        if (request == null || request.getText() == null || request.getText().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Text cannot be empty")
                    .build();
        }

        String translated = service.translate(request.getText());


        return Response.ok(translated).build();

    }
}


