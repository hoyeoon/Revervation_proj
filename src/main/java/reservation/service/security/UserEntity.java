package kr.or.connect.reservation.service.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
public class UserEntity {
    private String loginUserId;
    private String password;
}