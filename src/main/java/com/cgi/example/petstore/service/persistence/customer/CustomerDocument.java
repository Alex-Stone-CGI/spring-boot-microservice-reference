package com.cgi.example.petstore.service.persistence.customer;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Data
@Document(collection = "customers")
public class CustomerDocument {

    @Id
    @Indexed(unique = true, name = "customerIdIndex")
    private Long customerId;

    @Indexed(unique = true, name = "usernameIndex")
    private String username;

    private String firstName;

    private String lastName;

    @Indexed(unique = true, name = "emailIndex")
    private String email;

    private CustomerAddressPersistenceType address;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModified;
}