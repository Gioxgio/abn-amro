package com.bank.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

public abstract class UnitTestBase {

    private AutoCloseable mocks;

    @BeforeEach
    public void baseSetup() {

        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void close() throws Exception {

        mocks.close();
    }

    protected static Stream<String> allPossibleStrings() {
        return Stream.of(null, "", " ", "test");
    }
}