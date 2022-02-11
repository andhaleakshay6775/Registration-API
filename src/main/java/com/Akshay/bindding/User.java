package com.Akshay.bindding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {
	
	private Integer userId;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private Long userPhNo;
	private LocalDate userDob;
	private String userGender;
	private Integer userCountry;
	private Integer userState;
	private Integer userCity;
	private String userPWD;
	private String userAccStatus;
	private LocalDate createDate;
	private LocalDate updateDate;

}
