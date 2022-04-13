package se.iths.rest;

import se.iths.entity.Student;
import se.iths.erorr.Erorr;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @PersistenceContext
    EntityManager entityManager;



    StudentService studentService;

    @Inject
    public StudentRest(StudentService studentService) {
        this.studentService = studentService;
    }

    @Path("")
    @POST
    public Response createStudent( Student student) {

        try {
            studentService.createStudent(student);
            return Response.ok(student).build();
        }catch (Exception ex){
            Erorr err = new Erorr ("Email is alrady used or name is missing found");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity  (err).build());
        }
    }

    @Path("{id}")
    @GET
    public Response findStudentById(@PathParam("id") Long id) {
        Student student = studentService.findStudentById(id);

        if (student == null) {
            Erorr err = new Erorr ("Student with ID " + id + " was not found in database.");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(err).build());
        }
        return Response.ok(student).build();
    }

    @Path("")
    @GET
    public Response getAllStudents() {
        List<Student> foundStudents = studentService.getAllStudents();
        return Response.ok(foundStudents).build();
    }

    @Path("getbylastname")
    @GET
    public Response getBylastName(@QueryParam("lastName") String lastName) {

        List<Student> result= studentService.getByLastName(lastName);
        if (result.size()<=0) {
            Erorr err = new Erorr ("Student with lastName " + lastName + " was not found in database.");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(err).build());
        }
        return  Response.ok(result).build() ;

    }

    @Path("")
    @PUT
    public Response updateStudent(Student student) {

        try {
            studentService.updateStudent(student);
            return Response.ok(student).build();
        }catch (Exception ex){
            Erorr err = new Erorr ("Email is alrady used or Something is missing ");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity  (err).build());
        }
    }

    @Path("{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, Student student) {
        Student findStudent = studentService.findStudentById(id);

        if (findStudent == null) {
            Erorr err = new Erorr ("Student with ID " + id + " was not found in database.");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(err).build());
        }

        Student updateEmail = studentService.updateEmail(id,student.getEmail());
        return Response.ok(updateEmail).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        Student student = studentService.findStudentById(id);

        if (student == null) {
            Erorr err = new Erorr ("Student with ID " + id + " was not found in database.");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(err).build());
        }
        studentService.deleteStudent(id);
        return Response.ok().build();
    }
}