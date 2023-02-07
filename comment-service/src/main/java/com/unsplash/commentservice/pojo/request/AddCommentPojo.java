package com.unsplash.commentservice.pojo.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddCommentPojo {
    String description;
    Long imageId;
    Long userId;
}
