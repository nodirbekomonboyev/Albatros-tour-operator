package com.nodirverse.albatros.controller;

import com.nodirverse.albatros.config.FileStorageProperties;
import com.nodirverse.albatros.dto.request.EmployeeCreateRequest;
import com.nodirverse.albatros.dto.response.EmployeeResponse;
import com.nodirverse.albatros.service.EmployeeService;
import com.nodirverse.albatros.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;


@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final FileStorageService fileStorageService;
    private final FileStorageProperties fileStorageProperties;

    @PostMapping("create")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeCreateRequest request){
        return ResponseEntity.ok(employeeService.create(request));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAll());
    }

    @PutMapping("update")
    public ResponseEntity<String> updateEmployee(
            @RequestParam(value = "id") UUID id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "position", required = false) String position
    ){
        return ResponseEntity.ok(employeeService.update(id, name, surname, phoneNumber, position));
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> delete(@RequestParam(value = "id") UUID id){
        return ResponseEntity.ok(employeeService.delete(id));
    }

    @PostMapping(value = "/{id}/upload-image",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ResponseEntity<String> uploadImage(
            @PathVariable UUID id,
            @RequestParam(value = "images") MultipartFile file
    ) {
        String imageUrl = fileStorageService.storeFile(file);
        employeeService.updateImageUrl(id, imageUrl);

        return ResponseEntity.ok(imageUrl);
    }
}
