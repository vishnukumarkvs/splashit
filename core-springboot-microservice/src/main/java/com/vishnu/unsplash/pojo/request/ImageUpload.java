package com.vishnu.unsplash.pojo.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
}
