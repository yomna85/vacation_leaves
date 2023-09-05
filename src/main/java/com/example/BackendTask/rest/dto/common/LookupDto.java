package com.example.BackendTask.rest.dto.common;

import com.example.BackendTask.rest.validation.InsertValidation;
import com.example.BackendTask.rest.validation.UpdateValidation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of = {"code"}, callSuper = true)
@ToString(of = {"englishName", "enabled", "code"}, callSuper = true)
public class LookupDto extends RestDto {

    @NotBlank(message = "Arabic Name is mandatory", groups = {InsertValidation.class})

    @Size(max = 50, message = "English name's max length allowed is 50 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String englishName;

    @Size(max = 50, message = "Code's max length allowed is 50 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String code;

    @NotNull(message = "Enabled is mandatory", groups = {InsertValidation.class})
    private Boolean enabled;
}
