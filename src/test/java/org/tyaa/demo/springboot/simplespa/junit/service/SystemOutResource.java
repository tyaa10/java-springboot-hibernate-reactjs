package org.tyaa.demo.springboot.simplespa.junit.service;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SystemOutResource implements BeforeEachCallback, AfterEachCallback {

    private PrintStream sysOut;
    private final ByteArrayOutputStream outContent =
            new ByteArrayOutputStream();

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        System.out.println("beforeEach");
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        System.setOut(sysOut);
        System.out.println("afterEach");
    }

    public String asString() {
        return outContent.toString();
    }
}
