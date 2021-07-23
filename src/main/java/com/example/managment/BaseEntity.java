package com.example.managment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

	
	@CreatedDate
	@Column(name = "created_at", nullable = false, unique = false, updatable = false)
	private Date createdAt;

	@LastModifiedDate
	@Column(name = "edited_at",  nullable = true, unique = false, updatable = true)
	private Date editedAt;
	
}
