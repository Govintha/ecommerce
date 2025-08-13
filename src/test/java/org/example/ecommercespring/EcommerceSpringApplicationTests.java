package org.example.ecommercespring;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

// if dont have this then by default PER_METHOD(you before all has to static and you instant variable will be difficult)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class EcommerceSpringApplicationTests {


    @BeforeAll
    void testBeforeALL(){
        System.out.println("Before All class ");
    }

    @BeforeEach
    void testBeforeEach(){
        System.out.println("Before Each class ");
    }

    @AfterAll
    void testAfterALL(){
        System.out.println("After All class ");
    }

    @AfterEach
    void testAfterEach(){
        System.out.println("After Each class ");
    }



    @Test
    @DisplayName("Addtion")
    void testAddition() {
        int result = 5 + 3;
        assertEquals(8, result);
        assertNotEquals(7,8);
    }

    @Disabled("Fix it later")
    @Test
    void exceptionChack(){
        assertThrows(ArithmeticException.class,()-> {
          int x =1 / 0;
        });
    }

    @Test
    @DisplayName("Test All assertion in one go")
    void testAllAssert(){

        String name="Govinth";

        // this will do in real time
        assertAll("Name Chchek",
                ()->assertNotNull(name),
                ()->assertEquals(name,"Govinth"),
                ()->assertTrue(name.startsWith("G")));

    }

    @DisplayName("To Test Multiple Input")
    @ParameterizedTest
    @CsvSource({"2,3,5","5,3,8"})
    void sumTest(int a,int b,int sum){
        assertEquals(sum,a+b);
    }


}
