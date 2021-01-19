package io.rooftop.admin.repository;

import io.rooftop.admin.entity.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(){
        return Item.builder()
                .name("노트북")
                .price(600000)
                .content("삼성 노트북")
                .build();
    }



    @AfterEach
    public void deleteAll() {
        itemRepository.deleteAll();
    }

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