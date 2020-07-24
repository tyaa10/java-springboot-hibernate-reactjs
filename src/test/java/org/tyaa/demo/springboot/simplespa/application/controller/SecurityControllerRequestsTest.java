package org.tyaa.demo.springboot.simplespa.application.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.tyaa.demo.springboot.simplespa.SpringbootSimplespaApplication;
import org.tyaa.demo.springboot.simplespa.controller.AuthController;
import org.tyaa.demo.springboot.simplespa.dao.CategoryHibernateDAO;
import org.tyaa.demo.springboot.simplespa.dao.ProductHibernateDAO;
import org.tyaa.demo.springboot.simplespa.dao.RoleHibernateDAO;
import org.tyaa.demo.springboot.simplespa.dao.UserHibernateDAO;
import org.tyaa.demo.springboot.simplespa.security.HibernateWebAuthProvider;
import org.tyaa.demo.springboot.simplespa.security.RestAuthenticationEntryPoint;
import org.tyaa.demo.springboot.simplespa.security.SavedReqAwareAuthSuccessHandler;
import org.tyaa.demo.springboot.simplespa.security.SecurityConfig;
import org.tyaa.demo.springboot.simplespa.service.AuthService;

import javax.servlet.Filter;

import java.security.AuthProvider;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AuthController.class)
@Import({HibernateWebAuthProvider.class, RestAuthenticationEntryPoint.class, SavedReqAwareAuthSuccessHandler.class, SecurityConfig.class})
public class SecurityControllerRequestsTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private AuthService authService;

    @MockBean
    private UserHibernateDAO userHibernateDAO;

    @MockBean
    private RoleHibernateDAO roleHibernateDAO;

    @MockBean
    private CategoryHibernateDAO categoryHibernateDAO;

    @MockBean
    private ProductHibernateDAO productHibernateDAO;

    @Test
    public void performLoginDefault() throws Exception {
        mvc.perform(formLogin())
            .andExpect((redirectedUrl("/api/auth/user/onerror")));
    }

    @Test
    public void performLoginWithAdminUserPassword() throws Exception {
        mvc.perform(formLogin("/login")
            .user("admin")
            .password("AdminPassword1"))
            .andExpect((status().isOk()));
    }

    @Test
    public void performLoginWithParameterSet() throws Exception {
        mvc.perform(formLogin("/login")
            .user("username", "admin")
            .password("password", "WrongAdminPassword1"))
            .andExpect((redirectedUrl("/api/auth/user/onerror")));
    }

    @Test
    public void performLogout() throws Exception {
        mvc.perform(logout("/logout"))
            .andExpect((redirectedUrl("/api/auth/user/signedout")));
    }

    @Test
    public void getAllRolesByAdminUserTest() throws Exception {
        mvc.perform(get("/api/auth/admin/roles")
            .with(httpBasic("admin","AdminPassword1")))
            .andExpect((status().isOk()));
    }
}
