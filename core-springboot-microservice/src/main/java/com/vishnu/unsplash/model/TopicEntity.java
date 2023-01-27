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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "topics")
public class TopicEntity extends BaseEntity{
    @Column(nullable = false,unique = true)
    String title;
    @Column(nullable = false,unique = true,columnDefinition = "text")
    String description;

    @ManyToMany(mappedBy = "topics",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    List<ImageEntity> images;

    @ElementCollection
    List <Meta> meta;
}
