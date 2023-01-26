package com.vishnu.unsplash.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="comments")
public class CommentEntity extends BaseEntity{

    @Column(columnDefinition = "text")
    String description;

    @Column
    Long likes;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    UserEntity user;

    @ManyToOne
    @JoinColumn(name="image_id",nullable = false)
    ImageEntity image;
}
