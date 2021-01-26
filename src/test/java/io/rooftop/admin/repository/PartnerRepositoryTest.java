package io.rooftop.admin.repository;

import io.rooftop.admin.AdminApplicationTests;
import io.rooftop.admin.entity.Category;
import io.rooftop.admin.entity.Partner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PartnerRepositoryTest extends AdminApplicationTests {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create_partner_test() throws Exception {
        // Given
        Category category = categoryRepository.findByType("TV").get();
        Partner partner = Partner.builder()
                .name("Partner2")
                .status("REGISTERED")
                .address("부산시 중구")
                .callCenter("070-234-1111")
                .partnerNumber("010-222-2222")
                .businessNumber("76324890123")
                .ceoName("고길동")
                .registeredAt(LocalDateTime.now())
                .category(category)
                .build();
        // When
        Partner savedPartner = partnerRepository.save(partner);

        // Then
        assertNotNull(savedPartner);
        assertEquals(savedPartner.getName(), partner.getName());
    }


}