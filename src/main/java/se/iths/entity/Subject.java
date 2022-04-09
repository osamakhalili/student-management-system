package se.iths.entity;

import com.fasterxml.jackson.annotation.*;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

   @ManyToMany
   @JsonBackReference
   private List<Student> students;

    @ManyToMany(mappedBy = "subjects",cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Teacher> teachers;

    public Subject(String name, List<Student> students, List<Teacher> teachers) {
        this.name = name;
        this.students = students;
        this.teachers = teachers;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }



    public Subject() {
    }


    public Subject(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public void addStudent(Student student){
        students.add(student);
    }
    public void removeStudent(Student student){
        students.remove(student);
    }
    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }
    public void removeTeacher(Teacher teacher){
        teachers.remove(teacher);
    }


}



