package com.ems.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LoginRequest {
	private String userName;
	private String password;
	private boolean isLogin;
}
