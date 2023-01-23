package com.vishnu.unsplash.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import java.util.Date;

@MappedSuperclass
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Where(clause = "deleted_at is null")
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "increment_generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "increment_generator",sequenceName = "increment_generator",allocationSize = 40)
    Long Id;
    @Column(nullable = false,insertable = false,updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Date createdAt;
    @Column(insertable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Date updatedAt;
    Date deletedAt;
}
