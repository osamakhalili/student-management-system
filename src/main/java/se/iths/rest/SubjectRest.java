package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.erorr.Erorr;
import se.iths.service.StudentService;
import se.iths.service.SubjectService;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/subject")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {


    SubjectService subjectService;
    StudentService studentService;
    TeacherService teacherService;

    @Inject
    public SubjectRest(SubjectService subjectService, StudentService studentService,TeacherService teacherService) {
        this.subjectService = subjectService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }


    @Path("")
    @POST
    public Response createSubject(Subject subject) {
        try {
            subjectService.createSubject(subject);

            return Response.ok(subject).build();
        }


        catch ( ConstraintViolationException error){
            Erorr err = new Erorr ("Insert a name");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity  (err).build());
        }


    }

    @Path("")
    @PUT
    public Response updateSubject(Subject subject) {

        try{
            subjectService.updateSubject(subject);
            return Response.ok(subject).build();
        }catch (Exception error){
            Erorr err = new Erorr ("No subject found");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity  (err).build());
        }
    }

    @Path("{id}")
    @GET
    public Response findSubjectById(@PathParam("id") Long id) {
        Subject foundStudent = subjectService.findSubjectById(id);

        if (foundStudent == null) {
            Erorr err = new Erorr ("Subject with ID " + id + " was not found in database.");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(err).build());
        }
        return Response.ok(foundStudent).build();
    }



    @Path("")
    @GET
    public Response getAllSubjects() {
        List<Subject> foundStudent = subjectService.getAllSubject();
        if (foundStudent == null) {
            Erorr err = new Erorr ("The list of Subject is empty");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(err).build());
        }
        return Response.ok(foundStudent).build();

    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {

        Subject foundStudent = subjectService.findSubjectById(id);

        if (foundStudent == null) {
            Erorr err = new Erorr ("Subject with ID " + id + " was not found in database.");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(err).build());
        }
        subjectService.deleteSubject(id);
        return Response.ok().build();
    }

    @Path("/student/{subjectid}/{studentid}")
    @PATCH
    public Response addStudent(@PathParam("subjectid") Long subjectId, @PathParam("studentid") Long studentId) {
        Subject foundSubject = subjectService.findSubjectById(subjectId);
        Student foundStudent = studentService.findStudentById(studentId);
        if (foundStudent == null || foundSubject == null) {
            Erorr err = new Erorr ("Subject  or student not found in database.");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(err).build());
        }
        subjectService.addStudent(foundStudent, subjectId);
        return Response.ok().build();
    }

    @Path("/teacher/{subjectid}/{teacherid}")
    @PATCH
    public Response addteacher(@PathParam("subjectid") Long subjectId, @PathParam("teacherid") Long teacherId) {
        Subject foundSubject = subjectService.findSubjectById(subjectId);
        Teacher foundTeacher = teacherService.findTeacherById(teacherId);
        if (foundTeacher == null || foundSubject == null) {
            Erorr err = new Erorr ("Subject  or Teacher not found in database.");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(err).build());
        }
        subjectService.addTeacher(foundTeacher, subjectId);
        return Response.ok().build();
    }





}