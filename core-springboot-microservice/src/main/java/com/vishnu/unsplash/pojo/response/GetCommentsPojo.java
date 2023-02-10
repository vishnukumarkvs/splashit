package com.vishnu.unsplash.pojo.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetCommentsPojo {

    Long Id;
    Date createdAt;
    Date updatedAt;
    String description;
    Long userId;

    Long imageId;
}
