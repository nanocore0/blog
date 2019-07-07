package com.pianxian.blog;

import com.pianxian.blog.entity.Permission;
import com.pianxian.blog.entity.User;
import com.pianxian.blog.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogAuthApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDetailsService userDetailsService;

    @Test
    public void testFindUserByUsername() throws IOException {
        User user = userMapper.findUserByUsername("eric");
        System.out.println(user);
    }

    @Test
    public void testFindPermissionListByUsername() {
        List<Permission> list = userMapper.findPermissionsListByUsername("eric");
        for (Permission permission : list) {
            System.out.println(permission.getId() + "-" + permission.getPermissionName() + "-" + permission.getPermissionTag());
        }
    }

    @Test
    public void contextLoads() {
    }

}
