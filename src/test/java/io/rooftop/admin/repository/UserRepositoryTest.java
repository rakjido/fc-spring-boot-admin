package io.rooftop.admin.repository;

import io.rooftop.admin.AdminApplicationTests;
import io.rooftop.admin.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest extends AdminApplicationTests {

    @Autowired
    private UserRepository userRepository;

    public User createUser() {
        String account = "Test01";
        String password = "1234";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .phoneNumber(phoneNumber)
                .registeredAt(registeredAt)
                .createdAt(createdAt)
                .createdBy(createdBy)
                .build();
        return user;
    }


    @Test
    public void create_user_테스트() throws Exception {
        // Given
        User user = createUser();

        // When
        User savedUser = userRepository.save(user);
        // Then
        assertNotNull(savedUser);
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void read_user_테스트() throws Exception {
        // Given
        User user = createUser();
//        User savedUser = userRepository.save(user);

        // When
        User findUser = userRepository.findFirstByPhoneNumberOrderByIdDesc(user.getPhoneNumber());

        // Then
        assertNotNull(findUser);
    }



    @Test
    public void read_에러_테스트() throws Exception {
        // Given
        User user = createUser();
        User savedUser = userRepository.save(user);

        // When
        Optional<User> findUser = userRepository.findById(2L);

        // Then
        // JUnit 5
        assertThrows(NoSuchElementException.class, ()-> {findUser.get();});
    }

    @Test
    public void update_테스트() throws Exception {
        // Given
        User user = createUser();
        User savedUser = userRepository.save(user);

        // When
        Optional<User> findUser = userRepository.findById(savedUser.getId());
        findUser.ifPresent(selectUser -> {
                    selectUser.setEmail("viva@gmail.com");
                    selectUser.setPhoneNumber("010-999-9999");
//                    userRepository.save(selectUser);
                });

        // Then
        User updatedUser = userRepository.findById(savedUser.getId()).get();
        assertThat(updatedUser.getEmail()).isEqualTo("viva@gmail.com");
//        assertThat(updatedUser.getEmail()).isEqualTo("testUser1@gmail.com");
    }

    @Test
    public void delete_테스트() throws Exception {
        // Given
        User user = createUser();
        User savedUser = userRepository.save(user);

        // When
        Optional<User> findUser = userRepository.findById(1L);
        findUser.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        // Then
        Optional<User> deletedUser = userRepository.findById(1L);
        assertFalse(deletedUser.isPresent());
    }


}