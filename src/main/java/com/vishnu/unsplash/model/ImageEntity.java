package com.vishnu.unsplash.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "image_topic",joinColumns = @JoinColumn(name="image_id"), inverseJoinColumns = @JoinColumn(name = "topic_id") )
    List<TopicEntity> topics;

    @ElementCollection
    List<Meta> meta;

}
