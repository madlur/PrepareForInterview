package Student.repository;

import Student.entity.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();
    void merge(Student student);
    boolean delete(Student student);
    boolean deleteById(Integer id);
    Student findById(Integer id);
    Long countAll();
    void mergeBatch(List<Student> student);
    List<Student> findByName(String name);
    void removeAll();
}
