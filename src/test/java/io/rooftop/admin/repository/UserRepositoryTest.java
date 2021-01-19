package io.rooftop.admin.repository;

import io.rooftop.admin.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    public User createUser() {
        // Given
        User user = new User();
        user.setAccount("testUser1");
        user.setEmail("testUser1@gmail.com");
        user.setPhonenumber("010-111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");
        return user;
    }

    @AfterEach
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Test
    public void create_테스트() throws Exception {
        // Given
        User user = createUser();

        // When
        User savedUser = userRepository.save(user);
        // Then
        System.out.println("savedUser.getAccount() = " + savedUser.getAccount());
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void read_테스트() throws Exception {
        // Given
        User user = createUser();
        User savedUser = userRepository.save(user);

        // When
        Optional<User> findUser = userRepository.findById(savedUser.getId());

        // Then
        findUser.ifPresent(selectUser -> {
                    assertThat(selectUser.getEmail()).isEqualTo(user.getEmail());
                }
        );
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
                    selectUser.setPhonenumber("010-999-9999");
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