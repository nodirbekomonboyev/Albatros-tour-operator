package com.nodirverse.albatros.service;

import com.nodirverse.albatros.dto.request.EmployeeCreateRequest;
import com.nodirverse.albatros.dto.response.EmployeeResponse;
import com.nodirverse.albatros.entity.Employee;
import com.nodirverse.albatros.exception.DataAlreadyExistsException;
import com.nodirverse.albatros.exception.DataNotFoundException;
import com.nodirverse.albatros.repository.EmployeeRepository;
import jakarta.validation.OverridesAttribute;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public String create(EmployeeCreateRequest request) {
        Optional<Employee> byPhoneNumber = employeeRepository.findByPhoneNumber(request.getPhoneNumber());
        if (byPhoneNumber.isPresent()) {
            throw new DataAlreadyExistsException("Employee already exists");
        }
        employeeRepository.save(modelMapper.map(request, Employee.class));
        return "Employee created";
    }


    public List<EmployeeResponse> getAll() {
        List<EmployeeResponse> list = new ArrayList<>();
        employeeRepository.findAll().forEach(employee -> {
            EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
            employeeResponse.setId(employee.getId());
            list.add(employeeResponse);
        });
        return list;
    }

    public String update(UUID id, String name, String surname, String phoneNumber, String position) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("employee not found!")
        );

        if(name != null){
            employee.setName(name);
        }
        if(surname != null){
            employee.setSurname(surname);
        }
        if(phoneNumber != null){
            employee.setPhoneNumber(phoneNumber);
        }
        if(position != null){
            employee.setPosition(position);
        }
        employeeRepository.save(employee);
        return "Employee updated";
    }

    public String delete(UUID id) {
        employeeRepository.deleteById(id);
        return "Employee deleted";
    }

    public Employee getEmployeeById(UUID id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("employee not found!")
        );
    }

    public void updateImageUrl(UUID id, String imageUrl) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        employee.setImageUrl(imageUrl);
        employeeRepository.save(employee);
    }
}
