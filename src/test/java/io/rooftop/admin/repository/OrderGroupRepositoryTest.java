package io.rooftop.admin.repository;

import io.rooftop.admin.AdminApplicationTests;
import io.rooftop.admin.entity.OrderGroup;
import io.rooftop.admin.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderGroupRepositoryTest extends AdminApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create_ordergroup_test() throws Exception {
        // Given
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222").get();
        OrderGroup orderGroup = OrderGroup.builder()
                .status("COMPLETE")
                .orderType("ALL")
                .revAddress("서울시 강남구")
                .revName("홍길동")
                .paymentType("CARD")
                .totalPrice(BigDecimal.valueOf(900000))
                .totalQuantity(1)
                .orderAt(LocalDateTime.now())
                .arrivalDate(LocalDateTime.now().plusDays(2))
//                .createdAt(LocalDateTime.now())
//                .createdBy("Admin")
                .user(user)
                .build();
        // When
        OrderGroup savedOrderGroup = orderGroupRepository.save(orderGroup);
        // Then
        assertNotNull(savedOrderGroup);
    }

}