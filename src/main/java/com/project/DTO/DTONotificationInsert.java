package com.project.DTO;

import java.util.Date;

import javax.validation.constraints.NotNull;

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
	private Long projectManagerId;
}
