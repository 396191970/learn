package com.tuobuxie.demo.sercurity.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Shaofeng Li
 */
@Data
@Entity
public class Authorities {
	@Id
	private String userId;
	@Column
	private String authority;
	@Column
	private String permissionFlag;
	@Column
	private String createUser;
	@Column
	private String createDateTime;
	@Column
	private String updateDateTime;

}
