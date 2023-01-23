package com.vishnu.unsplash.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "topics")
public class TopicEntity extends BaseEntity{
    @Column(nullable = false,unique = true)
    String title;
    @Column(nullable = false,unique = true,columnDefinition = "text")
    String description;

    @ManyToMany(mappedBy = "topics",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<ImageEntity> images;


}
