package com.unsplash.upload.pojo.request;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageUploadPojo {
    String title;
    String url;
    String description;

    List<MetaPojo> meta;

    Long userId;

    List<MultipartFile> files;


}
