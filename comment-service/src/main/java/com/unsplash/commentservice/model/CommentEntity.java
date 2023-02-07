package com.unsplash.commentservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comms")
public class CommentEntity {

    @Id
    @GeneratedValue(generator = "increment_generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "increment_generator",sequenceName = "increment_generator",allocationSize = 40)
    Long Id;
    @Column(nullable = false,insertable = false,updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Date createdAt;
    @Column(insertable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Date updatedAt;
//    @Column
//    Date deletedAt;

    @Column(columnDefinition = "text")
    String description;

//    @Column
//    Long likes;

    @Column(nullable = false)
    Long userId;

    @Column(nullable = false)
    Long imageId;
}
