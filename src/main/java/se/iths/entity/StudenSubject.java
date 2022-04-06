package se.iths.entity;

public class StudenSubject {

    public long id;
    public long subjectId;
    public long studentId;

    public StudenSubject(long id, long subjectId, long studentId) {
        this.id = id;
        this.subjectId = subjectId;
        this.studentId = studentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

}
