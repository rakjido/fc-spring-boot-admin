package io.rooftop.admin.repository;

import io.rooftop.admin.AdminApplicationTests;
import io.rooftop.admin.entity.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


class AdminUserRepositoryTest extends AdminApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create_admin_user_test() throws Exception {
        // Given
        AdminUser adminUser = AdminUser.builder()
                .account("Admin2")
                .password("1234")
                .status("REGISTERED")
                .role("PARTNER")
//                .createdAt(LocalDateTime.now())
//                .createdBy("AdminServer")
                .build();
        // When
        AdminUser savedAdminUser = adminUserRepository.save(adminUser);
        // Then
        assertNotNull(savedAdminUser);
    }

}