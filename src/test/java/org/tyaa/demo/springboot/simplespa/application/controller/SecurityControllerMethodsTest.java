package org.tyaa.demo.springboot.simplespa.application.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.tyaa.demo.springboot.simplespa.controller.AuthController;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SecurityControllerMethodsTest {

    @Autowired
    public AuthController authController;

    @Test
    public void shouldThrowAuthenticationCredentialsNotFoundException() {
        assertThrows(AuthenticationCredentialsNotFoundException.class, () -> {
            authController.getAllRoles();
        });
    }
    @Test
    @WithUserDetails(
        value = "admin",
        userDetailsServiceBeanName = "hibernateWebAuthProvider")
    public void withUserDetailsTest() {}

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void getAllRolesByAdminUserTest() {
        ResponseEntity responseEntity = authController.getAllRoles();
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    public void getAllRolesByAdminRoleTest() {
        ResponseEntity responseEntity = authController.getAllRoles();
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}
