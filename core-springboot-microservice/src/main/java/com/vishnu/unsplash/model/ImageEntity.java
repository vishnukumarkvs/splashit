package com.vishnu.unsplash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
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

    @Column
    Long likes;

    @OneToMany(mappedBy = "image",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonIgnore
    List<CommentEntity> comments;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    UserEntity user;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "image_topic",joinColumns = @JoinColumn(name="image_id"), inverseJoinColumns = @JoinColumn(name = "topic_id") )
    List<TopicEntity> topics;

    @ElementCollection
    List<Meta> meta;

}
