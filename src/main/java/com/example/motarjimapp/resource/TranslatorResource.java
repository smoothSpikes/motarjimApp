package com.example.motarjimapp.resource;

import com.example.motarjimapp.model.TranslationRequest;
import jakarta.ws.rs.*;
import com.example.motarjimapp.service.LLMService;


@Path("/translate")
public class TranslatorResource {

    @Consumes("application/json")
    @Produces("text/plain")
    @POST
    public String getTranslation(TranslationRequest request) {

        LLMService service = new LLMService();

        return service.translate(request.getText());
    }
}


