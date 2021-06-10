package kr.or.connect.reservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.UserDao;
import kr.or.connect.reservation.dto.User;
import kr.or.connect.reservation.service.security.UserEntity;
import kr.or.connect.reservation.service.security.UserRoleEntity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserDao userDao;
	 
    @Override
    @Transactional
    public UserEntity getUser(String loginUserId) {
    	User.DB user = userDao.selectUserByEmail(loginUserId);
        return new UserEntity(user.getEmail(), user.getPassword());
    }

    @Override
    @Transactional
    public List<UserRoleEntity> getUserRoles(String loginUserId) {
    	List<User.RoleDB> userRoles = userDao.selectRolesByEmail(loginUserId);
        List<UserRoleEntity> list = new ArrayList<>();
        
        for(User.RoleDB userRole : userRoles){
            list.add(new UserRoleEntity(loginUserId, userRole.getRoleName()));        	
        }
        return list;
    }
    
    @Override
    public int getUserId(String loginUserId){
    	return userDao.selectUserIdByEmail(loginUserId);
    }
}