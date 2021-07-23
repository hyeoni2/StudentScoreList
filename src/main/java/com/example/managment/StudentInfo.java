package com.example.managment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "student_info")
@NoArgsConstructor
@Data
public class StudentInfo extends BaseEntity{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", length = 20)
	private String name;
	
	@Column(name = "gender", length = 1)
	private String gender;
	
	@Column(name = "photo", columnDefinition = "MEDIUMBLOB")
	private byte[] photo;
	
	@Column(name = "image_type", length = 20)
	private String imeageType;

	
	@Column(name = "phone_number", length = 11)
	private String phoneNumber;
	

	
}
