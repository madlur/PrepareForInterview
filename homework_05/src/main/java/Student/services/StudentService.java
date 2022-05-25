package Student.services;

import org.hibernate.SessionFactory;
import Student.entity.Student;
import Student.repository.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository repository;
    private final SessionFactory factory;

    public StudentService(StudentRepository repository, SessionFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public void totalCount() {
        System.out.println("В БД " + repository.countAll() + " записей");
    }

    public Student findById(Integer id) {
        return repository.findById(id);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public void printAll() {
        repository.findAll().forEach(System.out::println);
    }

    public void addStudents(List<Student> studentList) {
        repository.mergeBatch(studentList);

    }

    public void removeAll() {
        repository.removeAll();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Student rename(Integer id, String newName) {
        if (repository.countAll() == 0) return null;
        Student student = repository.findById(id);
        if (student != null) {
            student.setName(newName);
            repository.merge(student);
            return student;
        }
        return null;
    }
}