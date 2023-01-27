package com.vishnu.unsplash.pojo.request;

import com.vishnu.unsplash.model.ImageEntity;
import com.vishnu.unsplash.model.TopicEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopicPojo {
    String title;
    String description;
    List<Long> imageIds;
}
