package io.rooftop.admin.repository;

import io.rooftop.admin.AdminApplicationTests;
import io.rooftop.admin.entity.Item;
import io.rooftop.admin.entity.Partner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
class ItemRepositoryTest extends AdminApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    public Item createItem(){
        Partner partner = partnerRepository.findByName("Partner1").get();
        return Item.builder()
                .status("UNREGISETERED")
                .name("애플 맥북")
                .title("맥북프로 2019")
                .content("2019년 형 맥북프로")
                .brandName("Apple")
                .price(1550000)
                .registeredAt(LocalDateTime.now())
//                .createdAt(LocalDateTime.now())
//                .createdBy("Partner1")
                .partner(partner)
                .build();
    }



//    @AfterEach
//    public void deleteAll() {
//        itemRepository.deleteAll();
//    }

    @Test
    public void create_item_테스트() throws Exception {
        // Given
        Item item = createItem();
        // When
        Item savedItem = itemRepository.save(item);
        // Then
        assertNotNull(savedItem);
    }

    @Test
    public void read_item_테스트() throws Exception {
        // Given
        Item item = createItem();
        Item savedItem = itemRepository.save(item);
        // When
        Optional<Item> foundItem = itemRepository.findById(savedItem.getId());
        // Then
        assertTrue(foundItem.isPresent());
    }



}