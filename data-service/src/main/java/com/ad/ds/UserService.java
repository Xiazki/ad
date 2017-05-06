package com.ad.ds;

import com.ad.bean.user.UserRequest;
import com.ad.dao.UserDao;
import com.ad.ds.exception.ParamException;
import com.ad.entity.User;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xiang on 2017/4/7.
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleService userRoleService;

    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    public User get(Long id) {
        return userDao.get(id);
    }

    public void delete(Long id) {
        userDao.delete(id);
    }

    public List<User> list(Integer start, Integer length, String searchInfo) {
        return userDao.list(start, length, searchInfo);
    }

    public void save(User user) {
        userDao.update(user);
    }

    public void saveOrUpdateUser(UserRequest userRequest) throws ParamException {
        User user = new User();
        if (userRequest.getId() != null && userRequest.getId() > 0) {
            //修改
            user = userDao.get(userRequest.getId());
            user.setUpdateTime(System.currentTimeMillis());
        } else {
            if (userDao.getUserByName(userRequest.getUsername()) != null) {
                throw new ParamException("该用户已经存在!");
            }
            user.setCreateTime(System.currentTimeMillis());
        }
        user.setUsername(userRequest.getUsername());
        user.setJob(userRequest.getJob());
        user.setDepartment(userRequest.getDepartment());
        PasswordService psv = new DefaultPasswordService();
        String encrypt = psv.encryptPassword(userRequest.getPassword());
        user.setPassword(encrypt);
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        userDao.update(user);

        if (userRequest.getRole() != null && userRequest.getRole() > 0) {
            userRoleService.save(user.getId(), userRequest.getRole());
        }
    }

    public Integer count() {
        return userDao.count();
    }
}
