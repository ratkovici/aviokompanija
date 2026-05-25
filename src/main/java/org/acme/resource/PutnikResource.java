package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.exceptions.PutnikException;
import org.acme.model.Karta;
import org.acme.model.MultipartBody;
import org.acme.model.Putnik;
import org.acme.model.UploadedFile;
import org.acme.service.PutnikService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Path("/putnik")
public class PutnikResource {

    @Inject
    PutnikService putnikService;
    @Inject
    EntityManager em;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addPutnik")
    @RolesAllowed("admin")
    public Response addPutnik(Putnik putnik) {
        try {
            putnikService.createPutnik(putnik);
        } catch (PutnikException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllPutnici")
    public Response getAllPutnici() {
        List<Putnik> putnici = null;
        try {
            putnici = putnikService.getAllPutnici();
        } catch (PutnikException e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(putnici).build();
    }

    @GET
    @Path("/getPutnikByName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPutnikByName(@QueryParam("name") String name) {
        List<Putnik> putnici = putnikService.getPutnikByName(name);
        return Response.ok().entity(putnici).build();
    }

    @GET
    @Path("/karte/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKarteByPutnikId(@PathParam("id") Long id) {
        List<Karta> karte = putnikService.getKarteByPutnikId(id);
        return Response.ok().entity(karte).build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/uploadFile")
    @Transactional
    public Response uploadFile(MultipartBody multipartBody, @QueryParam("id") Long id) {
        try {
            Putnik putnik = em.find(Putnik.class, id);
            String path = "C:/Users/Lenovo/Desktop/files/" + multipartBody.file.fileName();
            UploadedFile uploadedFile;

            if (new File(path).exists()) {
                List<UploadedFile> istaIme = em.createQuery("SELECT f FROM UploadedFile f WHERE f.filename = :p", UploadedFile.class)
                        .setParameter("p", path).getResultList();

                if (!istaIme.isEmpty()) {
                    uploadedFile = istaIme.get(0);
                    if (!putnik.getUploadedFiles().contains(uploadedFile)) {
                        putnik.getUploadedFiles().add(uploadedFile);
                        em.merge(putnik);
                    }
                } else {
                    uploadedFile = new UploadedFile(path);
                    em.persist(uploadedFile);

                    putnik.getUploadedFiles().add(uploadedFile);
                    em.merge(putnik);
                }
                return Response.status(Response.Status.CONFLICT).build();
            }

            Files.copy(multipartBody.file.uploadedFile(), java.nio.file.Path.of(path), StandardCopyOption.REPLACE_EXISTING);

            uploadedFile = new UploadedFile(path);
            em.persist(uploadedFile);

            putnik.getUploadedFiles().add(uploadedFile);
            em.merge(putnik);

        } catch (IOException e) {
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/getWithFiles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPutnikWithFiles(@QueryParam("id") Long id) {
        Putnik putnik = em.find(Putnik.class, id);

        for (int i = 0; i < putnik.getUploadedFiles().size(); i++) {
            UploadedFile uf = putnik.getUploadedFiles().get(i);
            uf.setFile(new File(uf.getFilename()));
        }

        return Response.ok(putnik).build();
    }
}


