package org.pronet.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FileDto {
    private Long id;
    private String fileName;
    private Long fileSize;
    private String filePath;
    private Long ownerId;
    private List<Long> visibleToUserIdList;
}
