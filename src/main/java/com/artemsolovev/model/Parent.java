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
@Table(name = "parents")
public class Parent extends User {
    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JsonIgnore
    private List<Student> students;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Order> orders;

    public Parent(long id, @NonNull String login, @NonNull String password, @NonNull String surname,
                  @NonNull String name, @NonNull String patronymic, @NonNull School school, List<Student> students,
                  List<Order> orders) {
        super(id, login, password, surname, name, patronymic, school);
        this.students = students;
        this.orders = orders;
    }
}
