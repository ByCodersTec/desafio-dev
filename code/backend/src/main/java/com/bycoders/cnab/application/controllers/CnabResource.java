package com.bycoders.cnab.application.controllers;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bycoders.cnab.dominio.services.CnabService;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Path("/cnab")
public class CnabResource {

    @Inject
    CnabService cnabService;

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response upload(@MultipartForm final MultipartFormDataInput input) {
        
        cnabService.uploadArquivo(input);
        return Response.ok("Leitura Efetuada com Sucesso!").build();
        
    }

    @GET
    public Response consultaTodos(){
        return Response.ok(cnabService.obterTodosRegistros()).build();
    }
  
}