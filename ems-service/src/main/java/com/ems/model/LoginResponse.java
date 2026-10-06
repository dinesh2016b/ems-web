package com.ems.model;

import com.ems.bean.User;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LoginResponse {
	private User user;
	private String jwt_access_token;
    private boolean isAuthenticated;
}
