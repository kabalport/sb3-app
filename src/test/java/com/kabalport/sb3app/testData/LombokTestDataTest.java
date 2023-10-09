package com.kabalport.sb3app;

import com.kabalport.sb3app.testData.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LombokTestDataTest {

    @Test
    public void testDataTest(){
        TestData testData = new TestData();

        testData.setName("kabalport");

        Assertions.assertEquals("kabalport", testData.getName());
    }
}
