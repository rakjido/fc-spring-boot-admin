package io.rooftop.admin.sampledata;


import io.rooftop.admin.AdminApplicationTests;
import io.rooftop.admin.component.LoginUserAuditorAware;
import io.rooftop.admin.config.JpaConfig;
import io.rooftop.admin.entity.Category;
import io.rooftop.admin.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@DataJpaTest                                                                    // JPA 테스트 관련 컴포넌트만 Import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
@DisplayName("CategorySample 생성")
@Import({JpaConfig.class, LoginUserAuditorAware.class}) // BaseEntity에 필요한 설정
@Transactional(propagation = Propagation.NOT_SUPPORTED) // transaction 중지
public class CategorySample {
//    public class CategorySample extends AdminApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createSample(){
        List<String> category = Arrays.asList("COMPUTER","CLOTHING","MULTI_SHOP","INTERIOR","FOOD","SPORTS","SHOPPING_MALL","DUTY_FREE","BEAUTY");
        List<String> title = Arrays.asList("컴퓨터-전자제품","의류","멀티샵","인테리어","음식","스포츠","쇼핑몰","면세점","화장");

        for(int i = 0; i < category.size(); i++){
            String c = category.get(i);
            String t = title.get(i);
            Category create = Category.builder().type(c).title(t).build();
            categoryRepository.save(create);
        }
    }
}