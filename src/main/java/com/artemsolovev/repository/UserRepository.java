package com.artemsolovev.repository;

import com.artemsolovev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllBySchool_Id(long idSchool);

    Optional<User> findByLogin(String login);
}
