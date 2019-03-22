package com.tuobuxie.demo.sercurity.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Users  {

	@Id
	private String userId;
    @Column
	private String loginId;
    @Column
	private String password;
    @Column
	private String displayName;
    @Column
	private String enabled;
    @Column
	private String authorityKind;
	

}
