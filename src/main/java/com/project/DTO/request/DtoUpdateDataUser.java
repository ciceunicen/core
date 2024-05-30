package com.project.DTO.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record DtoUpdateDataUser (@NotBlank String name,
                                 @Email String email,
                                 @NotBlank String currentPassword,
                                 String newPassword,
                                 String newPasswordCorfirmed){}
