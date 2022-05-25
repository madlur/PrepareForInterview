package Student;

import org.hibernate.SessionFactory;
import Student.entity.Student;
import Student.DAO.StudentDAO;
import Student.factory.HibernateSessionFactory;
import Student.repository.StudentRepository;
import Student.services.StudentService;

import java.util.ArrayList;
import java.util.List;

public class TestRequest {
    private final StudentService service;

    public TestRequest() {
        SessionFactory factory = HibernateSessionFactory.getSessionFactory();
        StudentRepository repository = new StudentDAO(factory);
        service = new StudentService(repository, factory);
    }

    public void start() {
        service.totalCount();
        System.out.println("добавляем 100 записей");
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            students.add(new Student("Студент#" + i, (int) (Math.random()*10)));
        }
        service.addStudents(students);
        System.out.print("Список студентов в таблице: ");
        service.printAll();
        System.out.println("Количество записей");
        service.totalCount();
        System.out.println("очищаем таблицу");
        service.removeAll();
        System.out.print("список студентов в таблице:");
        service.printAll();
        System.out.println("Количество записей");
        service.totalCount();
        System.out.println();

        System.out.println("Добавим 10 записей");
        List<Student> studentsNew = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            students.add(new Student("Студент#" + i, (int) (Math.random()*10)));}
        service.addStudents(studentsNew);
        System.out.print("список студентов в таблице: ");
        service.printAll();
        System.out.println("Количество записей: ");
        service.totalCount();
        System.out.println();

        List<Student> studentList = service.findAll();
        Integer id = studentList.get(5).getId();
        System.out.println("Удаляем студента с ID " + id);
        service.deleteById(id);
        System.out.print("список студентов в таблице: ");
        service.printAll();
        System.out.println("Количество записей: ");
        service.totalCount();
        System.out.println();

        String newName = "NEW_STUDENT#9999";
        studentList = service.findAll();
        Student student = studentList.get(studentList.size()-5);
        id = student.getId();
        System.out.println("Студент " + student.getName() + "меняем на " + newName);
        service.rename(id, newName);
        System.out.println(service.findById(id));
        System.out.println();
        System.out.print("список студентов в таблице: ");
        service.printAll();
        System.out.println("Количество записей: ");
        service.totalCount();
        System.out.println();
        System.out.println("Очищаем таблицу");
        service.removeAll();

        HibernateSessionFactory.shutdown();
    }
}