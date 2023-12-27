package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.entities.Notification;
import com.project.entities.User;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	@Query("SELECT n"
			+ " FROM Notification n"
			+ " WHERE n.user = :user"
			+ " ORDER BY n.date DESC")
	public List<Notification> findAllByUser(User user);
	
	@Query("SELECT n"
			+ " FROM Notification n"
			+ " WHERE n.user = :user"
			+ " AND n.isRead IS NOT TRUE"
			+ " ORDER BY n.date DESC")
	public List<Notification> findAllByNotReadAndUser(User user);
	
	@Modifying
	@Query("UPDATE Notification n"
			+ " SET n.isRead = true"
			+ " WHERE n.user = :user")
	public void setNotificationsAsReadeadByUser(User user);
}
