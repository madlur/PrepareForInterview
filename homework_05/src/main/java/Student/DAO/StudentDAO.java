package Student.DAO;

import Student.entity.Student;
import Student.repository.StudentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentDAO implements StudentRepository {
    private final SessionFactory factory;

    public StudentDAO(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Student findById(Integer id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.getTransaction().commit();
            return student;
        }

    }

    @Override
    public List<Student> findAll() {
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            List<Student> studentList = session.createQuery("from Student").list();
            session.getTransaction().commit();
            return studentList;
        }
    }

    @Override
    public void merge(Student entity) {
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Student student = (Student) session.merge(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public boolean delete(Student entity) {
        return deleteById(entity.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.getNamedQuery("deleteById")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        }   catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Long countAll() {
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Long countResult = (Long) session
                    .getNamedQuery("countAll")
                    .uniqueResult();
            session.getTransaction().commit();
            return countResult;
        }
    }

    @Override
    public void mergeBatch(List<Student> entities) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            entities.forEach(session::merge);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Student> findByName(String name) {
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            List<Student> studentList = session.getNamedQuery("findByName")
                    .setParameter("name", name)
                    .list();
            session.getTransaction().commit();
            return studentList;
        }
    }

    @Override
    public void removeAll() {
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.createQuery("delete from Student").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}