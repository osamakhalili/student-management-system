package se.iths.service;


import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public void createSubject(Subject subject) {
        entityManager.persist(subject);
    }

    public void updateSubject(Subject subject) {
        entityManager.merge(subject);
    }

    public Subject findSubjectById(Long id) {
        return entityManager.find(Subject.class, id);
    }

    public List<Subject> getAllSubject() {
        return entityManager.createQuery("SELECT i from Subject i", Subject.class).getResultList();
    }
    public Subject getByName(String name) {
        return entityManager.find(Subject.class, name);
    }

    public void deleteSubject(Long id) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        entityManager.remove(foundSubject);
    }
    public void addStudent(Student student, long id) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        foundSubject.addStudent(student);
        entityManager.merge(foundSubject);
    }


    public void addTeacher(Teacher teacher, Long id) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        foundSubject.addTeacher(teacher);
        entityManager.merge(foundSubject);
    }
}