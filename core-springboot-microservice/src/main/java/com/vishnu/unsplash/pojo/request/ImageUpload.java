package com.vishnu.unsplash.pojo.request;

import com.vishnu.unsplash.model.Meta;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ImageUpload {

    String title;
    String url;
    String description;
    Long userId;

    List<Meta> meta;
}
