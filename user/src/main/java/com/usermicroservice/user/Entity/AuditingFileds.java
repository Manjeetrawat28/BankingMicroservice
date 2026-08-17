package com.usermicroservice.user.Entity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingFileds {

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private String created_by;
	
	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private LocalDateTime created_at;
	
	@LastModifiedBy
	@Column(name = "updated_by")
	private String updated_by;
	
	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updated_at;

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
	
	
}
