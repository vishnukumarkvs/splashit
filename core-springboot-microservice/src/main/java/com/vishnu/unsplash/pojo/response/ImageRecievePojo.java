package com.vishnu.unsplash.pojo.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageRecievePojo {
    String title;
    String url;
    String description;

    List<MetaPojo> meta;

    Long userId;
}
