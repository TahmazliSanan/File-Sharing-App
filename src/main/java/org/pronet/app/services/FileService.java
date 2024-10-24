package org.pronet.app.services;

import org.pronet.app.dtos.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileDto createFile(Long ownerId, MultipartFile file, List<Long> visibleToUserIdList);
    FileDto getFileById(Long id);
    List<FileDto> getFiles();
    List<FileDto> getFilesVisibleToUsers(Long userId);
    void deleteFile(Long id);
}
