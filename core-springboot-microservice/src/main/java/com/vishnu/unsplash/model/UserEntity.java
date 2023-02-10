package com.vishnu.unsplash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Column(nullable = false,unique = true)
    String name;
    @Column(nullable = false)
    String password;
    @Column(nullable = false,unique = true)
    String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    List<ImageEntity> images;

//    @Column
//    List<Long> commentId;
}
