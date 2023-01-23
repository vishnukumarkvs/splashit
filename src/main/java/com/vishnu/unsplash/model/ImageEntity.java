package com.vishnu.unsplash.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "images")
public class ImageEntity extends BaseEntity{
    @Column(nullable = false)
    String title;
    @Column(nullable = false,columnDefinition = "text",unique = true)
    String url;
    @Column(columnDefinition = "text")
    String description;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    UserEntity user;

}
