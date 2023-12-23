package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.entities.Notification;
import com.project.entities.User;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	@Query("SELECT n"
			+ " FROM Notification n"
			+ " WHERE n.administrator = :administrator"
			+ " ORDER BY n.date DESC")
	public List<Notification> findAllByAdministrator(User administrator);
	
	@Query("SELECT n"
			+ " FROM Notification n"
			+ " WHERE n.administrator = :administrator"
			+ " AND n.isRead IS NOT TRUE"
			+ " ORDER BY n.date DESC")
	public List<Notification> findAllByNotReadAndAdministrator(User administrator);
}
