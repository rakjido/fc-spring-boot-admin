package io.rooftop.admin.repository;

import io.rooftop.admin.AdminApplication;
import io.rooftop.admin.AdminApplicationTests;
import io.rooftop.admin.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class CategoryRepositoryTest extends AdminApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create_category() throws Exception {
        // Given
        String type = "TV";
        String title = "텔레비젼";
//        LocalDateTime createdAt = LocalDateTime.now();
//        String createdBy = "AdminServer";
        // When
        Category category = Category.builder()
                .type(type)
                .title(title)
//                .createdAt(createdAt)
//                .createdBy(createdBy)
                .build();
        Category savedCategory = categoryRepository.save(category);
        // Then
        assertNotNull(savedCategory);
        assertEquals(savedCategory.getType(), type);
        assertEquals(savedCategory.getTitle(), title);
    }

    @Test
    public void read_category() throws Exception {
        // Given
        String type = "COMPUTER";
        // When
        Optional<Category> savedCategory = categoryRepository.findByType(type);

        // Then
        savedCategory.ifPresent(category -> {
            assertEquals(category.getType(), type);
            System.out.println("category.getId() = " + category.getId());
            System.out.println("category.getType() = " + category.getType());
            System.out.println("category.getTitle() = " + category.getTitle());
        });
    }
}