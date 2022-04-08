package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void createStudent(Student student) {
        entityManager.persist(student);
    }

    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    public Student findStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT i from Student i", Student.class).getResultList();
    }

    public void deleteStudent(Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        entityManager.remove(foundStudent);
    }

    public Student updateEmail(Long id, String email ) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setEmail(email);
        return foundStudent;
    }

    public List<Student> getByLastName(String lastName) {
        String query = "SELECT i FROM Student i WHERE i.lastName = ?1";
        return entityManager.createQuery(query, Student.class).setParameter(1, lastName).getResultList();
    }
    public Student getByEmail(String email) {
        return entityManager.find(Student.class, email);
    }

    public void addSubjectToStudent(Subject subject, Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.addSubject(subject);
        entityManager.merge(foundStudent);
    }


}