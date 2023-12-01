package com.project.DTO;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DTOChecks {
	@NotNull(message = "isActive should't be null")
	private Boolean isActive;
}
