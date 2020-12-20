package com.hua.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hua.bean.ResourceObject;
import com.hua.bean.Role;
import com.hua.bean.User;
import com.hua.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public User getUserByName(String username) {
        return getMapByName(username);
    }

    /**
     * 模拟数据库查询
     *
     * @param username 用户名
     * @return User
     */
    private User getMapByName(String username) {
        ResourceObject resourceObject1 = new ResourceObject("1", "query");
        ResourceObject resourceObject2 = new ResourceObject("2", "add");
        Set<ResourceObject> resourceObjectSet = new HashSet<>();
        resourceObjectSet.add(resourceObject1);
        resourceObjectSet.add(resourceObject2);
        Role role = new Role("1", "admin", resourceObjectSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = new User("1", "wsl", "123456", roleSet);
        Map<String, User> map = new HashMap<>();
        map.put(user.getUsername(), user);

        Set<ResourceObject> resourceObjectSet1 = new HashSet<>();
        resourceObjectSet1.add(resourceObject1);
        Role role1 = new Role("2", "user", resourceObjectSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        User user1 = new User("2", "zhangsan", "123456", roleSet1);
        map.put(user1.getUsername(), user1);
        return map.get(username);
    }
    
}
