package hw_07.service;

import hw_07.entity.Student;
import hw_07.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.repository = studentRepository;
    }


    public Student findById(Long id) {
        return repository.getById(id);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
