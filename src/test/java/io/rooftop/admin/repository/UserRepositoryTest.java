package io.rooftop.admin.repository;

import io.rooftop.admin.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create_테스트() throws Exception {
        // Given
        User user = new User();
        user.setAccount("testUser1");
        user.setEmail("testUser1@gmail.com");
        user.setPhonenumber("010-111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");
        
        // When
        User savedUser = userRepository.save(user);
        System.out.println("savedUser.getAccount() = " + savedUser.getAccount());
        // Then
    }

}