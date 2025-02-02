package io.rooftop.admin.repository;

import io.rooftop.admin.entity.Category;
import io.rooftop.admin.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Optional<Partner> findByName(String name);
    List<Partner> findByCategory(Category category);
}
