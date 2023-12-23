package com.project.DTO;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTONotificationInsert {
	@NotNull(message = "message shouldn't be null")
	private String message;
	@NotNull(message = "date shouldn't be null")
	private Date date;
	@Positive(message = "administratorId should be positive")
	private Long administratorId;
}
