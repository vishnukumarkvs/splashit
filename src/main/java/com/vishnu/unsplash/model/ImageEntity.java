package com.vishnu.unsplash.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

}
