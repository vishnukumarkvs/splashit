package com.vishnu.unsplash.pojo.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SqsImageUploadPojo {
    String title;
    String url;
    String description;

    List<Meta> meta;

    Long userId;

    public static class Meta{
        String key;
        String value;
    }

}