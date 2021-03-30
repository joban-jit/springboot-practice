package com.springboot.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restservices.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
