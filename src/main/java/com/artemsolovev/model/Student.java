package com.artemsolovev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "students")
public class Student extends User{
    private int tableNumber;
    private boolean presence = false;
    private String grade;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Parent parent;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Order> orders;

    public Student(long id, @NonNull String login, @NonNull String password, @NonNull String surname,
                   @NonNull String name, @NonNull String patronymic, @NonNull School school, int tableNumber,
                   boolean presence, String grade, @NonNull Parent parent, List<Order> orders) {
        super(id, login, password, surname, name, patronymic, school);
        this.tableNumber = tableNumber;
        this.presence = presence;
        this.grade = grade;
        this.parent = parent;
        this.orders = orders;
    }
}
