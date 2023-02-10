package com.unsplash.commentservice.pojo.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCommentPojo {
    Long userId;
    Long imageId;
    Long commentId;
}
