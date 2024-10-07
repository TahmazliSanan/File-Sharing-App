package org.pronet.app.controllers;

import lombok.RequiredArgsConstructor;
import org.pronet.app.dtos.FileDto;
import org.pronet.app.responses.ApiResponse;
import org.pronet.app.services.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping(value = "/create")
    public ResponseEntity<ApiResponse> createFile(
            @RequestParam Long ownerId,
            @RequestParam MultipartFile file,
            @RequestParam List<Long> visibleToUserIdList) {
        FileDto createdFile = fileService.createFile(ownerId, file, visibleToUserIdList);
        ApiResponse apiResponse = new ApiResponse(true, "File created successfully!", createdFile);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<ApiResponse> getFilesVisibleToUser(@PathVariable Long userId) {
        List<FileDto> filesVisibleToUsers = fileService.getFilesVisibleToUsers(userId);
        ApiResponse apiResponse = new ApiResponse(true, "Visible files", filesVisibleToUsers);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteFile(
            @PathVariable Long id,
            @RequestParam Long ownerId) {
        fileService.deleteFile(id, ownerId);
        return new ResponseEntity<>(new ApiResponse(true, "File deleted successfully!", null), HttpStatus.OK);
    }
}
