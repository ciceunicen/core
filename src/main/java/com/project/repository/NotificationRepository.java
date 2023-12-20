package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.entities.Notification;
import com.project.entities.ProjectManager;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	@Query("SELECT n"
			+ " FROM Notification n"
			+ " WHERE n.projectManager = :projectManager")
	public List<Notification> findByProjectManager(ProjectManager projectManager);

	@Query("SELECT n"
			+ " FROM Notification n"
			+ " WHERE n.projectManager = :projectManager"
			+ " AND n.isRead IS NOT TRUE")
	public List<Notification> findByNotReadProjectManager(ProjectManager projectManager);
}
