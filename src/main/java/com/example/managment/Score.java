package com.example.managment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "score")
@NoArgsConstructor
@Data
public class Score extends BaseEntity{
	
	@Id
	@Column(name = "student_no", length = 5)
	private String studentNo;
	
	@Column(name = "guk")
	private int guk;
	@Column(name = "math")
	private int math;
	@Column(name = "sahee")
	private int sahee;
	
	
	@Transient
	private double average;
	
	@PostLoad
	private void postLoad() {
		this.average = (this.guk + this.math + this.sahee)/3.0;
	}
	

}



