package org.example.springboot.report_27_람다;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class PredicateTest {
    @Test
    void 문자열이_비었는지_검사(){
        Predicate<String> isEmpty = a -> a.isEmpty();

        assertTrue(isEmpty.test(""));
        assertFalse(isEmpty.test("hello"));

    }
}