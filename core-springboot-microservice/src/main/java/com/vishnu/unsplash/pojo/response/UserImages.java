package com.vishnu.unsplash.pojo.response;

import com.vishnu.unsplash.model.ImageEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserImages {
    String username;
    List<ImageEntity> images;
}
