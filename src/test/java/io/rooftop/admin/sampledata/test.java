package io.rooftop.admin.sampledata;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class test {

    @Test
    public void test() throws Exception {
        // Given
        Random random = new Random();
        for(int i=0; i<20; i++) {
            int orderCount = random.nextInt(10) + 1;
            int itemNumber = random.nextInt(405)+1;
            System.out.println(orderCount);
        }
        // When

        // Then
    }

}
