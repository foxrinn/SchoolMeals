package com.artemsolovev.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "teachers")
public class Teacher extends User{

    public Teacher(long id, @NonNull String login, @NonNull String password, @NonNull String surname,
                   @NonNull String name, @NonNull String patronymic, @NonNull School school) {
        super(id, login, password, surname, name, patronymic, school);
    }
}
