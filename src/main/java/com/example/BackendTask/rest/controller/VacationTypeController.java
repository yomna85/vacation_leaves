package com.example.BackendTask.rest.controller;

import com.example.BackendTask.rest.dto.VacationTypeDto;
import com.example.BackendTask.rest.handler.VacationTypeHandler;
import com.example.BackendTask.rest.validation.InsertValidation;
import com.example.BackendTask.rest.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/vacation-type")
@Tag(name = "VacationType",description = "Rest API for VacationType")
public class VacationTypeController {
private VacationTypeHandler vacationTypeHandler;

    @GetMapping
    @Operation(summary = "get all" ,description = "this API to get all VacationType in pages")
    public ResponseEntity<?> getAll(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                    @RequestParam(value = "size",defaultValue = "10") Integer size)
    {
        return vacationTypeHandler.getAll(page,size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get by id" ,description = "this API to get VacationType by id")
    public ResponseEntity<?>getById(@PathVariable Integer id)
    {
        return vacationTypeHandler.getById(id);
    }
    @PostMapping
    @Operation(summary = "add VacationType" ,description = "this API to add new VacationType")
    public ResponseEntity<?>save(@Validated(InsertValidation.class) @RequestBody VacationTypeDto dto)
    {
        return vacationTypeHandler.save(dto);
    }
    @PutMapping("/{id}")
    @Operation(summary = "update VacationType" ,description = "this API to update an existing VacationType")
    public ResponseEntity<?>update(@Validated(UpdateValidation.class) @RequestBody VacationTypeDto dto,
                                   @PathVariable Integer id)
    {
        return vacationTypeHandler.update(dto, id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete VacationType" ,description = "this API to delete VacationType")
    public ResponseEntity<?>delete(@PathVariable Integer id)
    {
        return vacationTypeHandler.delete(id);
    }
}
