package com.orderapplicationservice.domain.ports.output.repository;

import com.orderdomaincore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer,UUID> {
}
