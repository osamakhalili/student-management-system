package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
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
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity  ( "Insert a name").type(MediaType.TEXT_PLAIN_TYPE).build());
        }


    }

    @Path("")
    @PUT
    public Response updateSubject(Subject subject) {
        subjectService.updateSubject(subject);
        return Response.ok(subject).build();
    }

    @Path("{id}")
    @GET
    public Response findSubjectById(@PathParam("id") Long id) {
        Subject foundStudent = subjectService.findSubjectById(id);

        if (foundStudent == null) {

            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Subject with ID " + id + " was not found in database.").type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        return Response.ok(foundStudent).build();
    }

    @Path("query")
    @GET
    public Response getAllSubject(@QueryParam("subject") String subject) {



        String responseString = "Here is the list of  subject  " + subject;
        return Response.ok(responseString).type(MediaType.TEXT_PLAIN_TYPE).build();


    }


    @Path("")
    @GET
    public Response getAllSubjects() {
        List<Subject> foundStudent = subjectService.getAllSubject();
        if (foundStudent == null) {

            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("The list of Subject is empty").type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        return Response.ok(foundStudent).build();

    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {

        Subject foundStudent = subjectService.findSubjectById(id);

        if (foundStudent == null) {

            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Subject with ID " + id + " was not found in database.").type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        subjectService.deleteSubject(id);
        return Response.ok().build();
    }

    @Path("/student/{subjectid}/{studentid}")
    @PATCH
    public Response addStudent(@PathParam("subjectid") Long subjectId, @PathParam("studentid") Long studentId) {
        Subject foundSubject = subjectService.findSubjectById(subjectId);
        Student foundStudent = studentService.findStudentById(studentId);
        subjectService.addStudent(foundStudent, subjectId);
       //studentService.addSubjectToStudent(foundSubject, studentId);
        return Response.ok().build();
    }

    @Path("/teacher/{subjectid}/{teacherid}")
    @PATCH
    public Response addteacher(@PathParam("subjectid") Long subjectId, @PathParam("teacherid") Long teacherId) {
        Subject foundSubject = subjectService.findSubjectById(subjectId);
        Teacher foundTeacher = teacherService.findTeacherById(teacherId);
        subjectService.addTeacher(foundTeacher, subjectId);
        teacherService.addSubjectToTeacher(teacherId,foundSubject);
        return Response.ok().build();
    }





}