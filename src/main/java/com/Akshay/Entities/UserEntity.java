package com.Akshay.Entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.Data;

@Data
@Entity
@Table(name = "User_DTLS")
public class UserEntity {
	@Id
	@Column(name = "USER_ID")
	private Integer userId;
	@Column(name = "USER_FIRSTNAME")
	private String userFirstName;
	@Column(name = "USER_LASTNAME")
	private String userLastName;
	@Column(name = "USER_EMAIL")
	private String userEmail;
	@Column(name = "USER_PHNO")
	private Long userPhNo;
	@Column(name = "USER_DOB")
	private LocalDate userDob;
	@Column(name = "USER_GENDER")
	private String userGender;
	@Column(name = "USER_COUNTRTY")
	private Integer userCountry;
	@Column(name = "USER_STATE")
	private Integer userState;
	@Column(name = "USER_CITY")
	private Integer userCity;
	@Column(name = "USER_PWD")
	private String userPWD;
	@Column(name = "USER_ACCSTATUS")
	private String userAccStatus;
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createDate;
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate updateDate;

}
