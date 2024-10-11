package org.pronet.app.services.impls;

import lombok.RequiredArgsConstructor;
import org.pronet.app.dtos.FileDto;
import org.pronet.app.entities.File;
import org.pronet.app.entities.User;
import org.pronet.app.enums.Role;
import org.pronet.app.exceptions.FileSizeLimitExceededException;
import org.pronet.app.exceptions.ResourceNotFoundException;
import org.pronet.app.exceptions.UnauthorizedException;
import org.pronet.app.repositories.FileRepository;
import org.pronet.app.repositories.UserRepository;
import org.pronet.app.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    private static final long MAX_FILE_SIZE = (long) (30 * Math.pow(1024, 2));

    @Override
    public FileDto createFile(Long ownerId, MultipartFile file, List<Long> visibleToUserIdList) {
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new FileSizeLimitExceededException("File size exceeds the 30MB limit!");
        }
        User owner = userRepository
                .findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found!"));
        List<User> visibleToUserList = userRepository
                .findAllById(visibleToUserIdList);
        File newFile = new File();
        newFile.setFileName(file.getOriginalFilename());
        newFile.setFileSize(file.getSize());
        newFile.setFilePath("/uploads/" + file.getOriginalFilename());  // Customize this path
        newFile.setOwner(owner);
        newFile.setVisibleToUserList(visibleToUserList);
        fileRepository.save(newFile);
        return new FileDto(
                newFile.getId(),
                newFile.getFileName(),
                newFile.getFileSize(),
                newFile.getFilePath(),
                ownerId,
                visibleToUserIdList);
    }

    @Override
    public List<FileDto> getFilesVisibleToUsers(Long userId) {
        User foundUser = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        List<File> fileList = fileRepository
                .findAll()
                .stream()
                .filter(file -> file.getVisibleToUserList().contains(foundUser) ||
                        file.getOwner().getId().equals(userId))
                .toList();
        return fileList
                .stream()
                .map(file -> new FileDto(
                        file.getId(),
                        file.getFileName(),
                        file.getFileSize(),
                        file.getFilePath(),
                        file.getOwner().getId(),
                        file.getVisibleToUserList()
                                .stream()
                                .map(User::getId)
                                .toList()))
                .toList();
    }

    @Override
    public void deleteFile(Long id, Long ownerId) {
        File foundFile = fileRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("File not found!"));
        User owner = userRepository
                .findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found!"));
        if (!foundFile.getOwner().getId().equals(ownerId) && owner.getRole() != Role.Admin) {
            throw new UnauthorizedException("You are not authorized to delete this file!");
        }
        fileRepository.delete(foundFile);
    }
}
