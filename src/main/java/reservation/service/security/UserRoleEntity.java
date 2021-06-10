package kr.or.connect.reservation.service.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
public class UserRoleEntity {
    private String userLoginId;
    private String roleName;
}