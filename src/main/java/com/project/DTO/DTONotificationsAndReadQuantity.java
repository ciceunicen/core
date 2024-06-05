package com.project.DTO;

import java.util.List;

import com.project.entities.Notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTONotificationsAndReadQuantity {
	private List<Notification> notifications;
	private Long readQuantity;
}
