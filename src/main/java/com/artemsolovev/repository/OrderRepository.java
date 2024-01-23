package com.artemsolovev.repository;

import com.artemsolovev.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByLocalDateBeforeAndSchool_Id(LocalDate dateNow, long idSchool);
    List<Order> findAllByLocalDateBeforeAndStudent_Grade(LocalDate dateNow, String grade);
    List<Order> findAllByLocalDateBeforeAndParent_Id(LocalDate dateNow, long idParent);
    List<Order> findAllByParent_Id(long idParent);
}
