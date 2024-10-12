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

    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> getFileById(@PathVariable(value = "id") Long id) {
        FileDto foundFile = fileService.getFileById(id);
        ApiResponse apiResponse = new ApiResponse(true, "File found!", foundFile);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ApiResponse> getFiles() {
        List<FileDto> fileList = fileService.getFiles();
        ApiResponse apiResponse = new ApiResponse(true, "File list", fileList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<ApiResponse> getFilesVisibleToUser(@PathVariable(value = "userId") Long userId) {
        List<FileDto> filesVisibleToUsers = fileService.getFilesVisibleToUsers(userId);
        ApiResponse apiResponse = new ApiResponse(true, "Visible file list", filesVisibleToUsers);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteFile(
            @PathVariable(value = "id") Long id) {
        fileService.deleteFile(id);
        return new ResponseEntity<>(new ApiResponse(true, "File deleted successfully!", null), HttpStatus.OK);
    }
}
