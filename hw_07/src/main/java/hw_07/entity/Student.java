package hw_07.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private Integer age;
}