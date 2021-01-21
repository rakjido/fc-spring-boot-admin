package io.rooftop.admin.repository;

import io.rooftop.admin.entity.Item;
import io.rooftop.admin.entity.OrderDetail;
import io.rooftop.admin.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    public Item createItem(){
        return Item.builder()
                .name("노트북")
                .price(600000)
                .content("삼성 노트북")
                .build();
    }

    public User createUser() {
        // Given
        User user = new User();
        user.setAccount("testUser1");
        user.setEmail("testUser1@gmail.com");
        user.setPhoneNumber("010-111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");
        return user;
    }

    @Test
    public void create_OrderDetail_테스트() throws Exception {
//        // Given
//        User user = createUser();
//        User savedUser = userRepository.save(user);
//
//        Item item = createItem();
//        Item savedItem = itemRepository.save(item);
//
//        // When
//        OrderDetail orderDetail = OrderDetail.builder()
//                .orderAt(LocalDateTime.now())
//                .item(savedItem)
//                .user(savedUser)
//                .build();
//        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
//        // Then
//        assertNotNull(savedOrderDetail);
    }

}