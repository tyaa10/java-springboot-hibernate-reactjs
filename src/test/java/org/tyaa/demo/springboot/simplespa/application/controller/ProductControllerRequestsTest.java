package org.tyaa.demo.springboot.simplespa.application.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.tyaa.demo.springboot.simplespa.SpringbootSimplespaApplication;
import org.tyaa.demo.springboot.simplespa.model.ProductModel;
import org.tyaa.demo.springboot.simplespa.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// включение режима теста приложения с запуском на реальном веб-сервере
// и с доступом к контексту приложения
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = SpringbootSimplespaApplication.class
)
// режим создания одиночного экземпляра класса тестов для всех кейсов
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// активация контейнера выполнения кейсов согласно пользовательской нумерации
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductControllerRequestsTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    final String baseUrl = "http://localhost:" + 8090 + "/simplespa/api";

    @Test
    @Order(1)
    public void givenNameAndQuantity_whenRequestsListOfORCLProducts_thenCorrect() throws Exception {
        // получение от REST API списка товаров, у которых название ORCL
        // и цена выше 1500
        ResponseEntity<ResponseModel> response =
            testRestTemplate.getForEntity(
            baseUrl + "/products/filtered/?search=name:ORCL,quantity>1500",
                ResponseModel.class
            );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ArrayList products =
            (ArrayList) response.getBody().getData();
        assertNotNull(products);
        // такой товар должен быть найден толко один
        assertEquals(1, products.size());
        // сложный тестовый веб-клиент testRestTemplate десериализует множество данных моделей
        // как список словарей, поэтому нужно явное преоразование в список моделей ProductModel
        List<ProductModel> productModels =
            (new ObjectMapper())
                .convertValue(products, new TypeReference<List<ProductModel>>() { });
        // у каждого найденного товара должны быть значения полей,
        // соотетствующие параметрам поискового запроса
        productModels.forEach(product -> {
            assertEquals("ORCL", product.getTitle());
            assertTrue(product.getQuantity() > 1500);
        });
    }
}
