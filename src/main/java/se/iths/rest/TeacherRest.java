package se.iths.rest;

;
import se.iths.entity.Teacher;

import se.iths.erorr.Erorr;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("teacher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {


    TeacherService teacherService;

    @Inject
    public TeacherRest(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Path("")
    @GET
    public Response getAllTeacher() {

        List<Teacher> foundTeacher = teacherService.getAllTeacher();
        if (foundTeacher == null) {
          Erorr err = new Erorr ("No Teacher found");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(err).build());
        }
        return Response.ok(foundTeacher).build();
    }


    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {

        try{
        teacherService.createTeacher(teacher);
        return Response.ok(teacher).build();}
        catch (Exception error){
            Erorr err = new Erorr ("Teacher is already exists or name is missing ");
            return  Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(err)
                    .build();
        }
    }

    @Path("")
    @PUT
    public Response updateTeacher(Teacher teacher) {
        try{
        teacherService.updateTeacher(teacher);
        return Response.ok(teacher).build();
        }catch (Exception error){
            Erorr err = new Erorr ("No Teacher found");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity  (err).build());
        }
    }

    @Path("{id}")
    @GET
    public Response findTeacherById(@PathParam("id") Long id) {
        Teacher foundTeacher = teacherService.findTeacherById(id);
        if (foundTeacher == null) {
            Erorr err = new Erorr ("No Teacher with id " + id + " found");
            return  Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(err)
                    .build();
        }
        return Response.ok(foundTeacher).build();
    }


    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {
        try{
        teacherService.deleteTeacher(id);
        return Response.ok().build();}
        catch (Exception error){
            Erorr err = new Erorr ("No Teacher with id " + id + " found");
            return  Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(err)
                    .build();
        }
    }

}