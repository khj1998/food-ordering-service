package com.orderapplicationservice.domain.ports.output.repository;

import com.orderdomaincore.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}
