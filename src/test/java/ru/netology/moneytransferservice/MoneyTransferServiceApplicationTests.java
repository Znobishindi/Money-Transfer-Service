package ru.netology.moneytransferservice;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.ConfirmOperation;
import ru.netology.moneytransferservice.model.TransferFromCardToCard;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneyTransferServiceApplicationTests {
    private final static int PORT = 5500;
    @Container
    public static GenericContainer<?> myapp = new GenericContainer<>("myapp")
            .withExposedPorts(PORT);


    private final TransferFromCardToCard transfer = new TransferFromCardToCard(
            "1111222244445555",
            "12/23",
            "075",
            "9999888877776666",
            new Amount(1000.0, "RUB"));



    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void moneyTransferTest() {


        ResponseEntity<String> forEntity = restTemplate.postForEntity(
                "http://localhost:" + myapp.getMappedPort(PORT) + "/transfer", transfer, String.class);
//        System.out.println(forEntity.getBody());
        String expected = "{\"operationId\":" + "\"1\"}";
        String actual = forEntity.getBody();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void confirmOperationTest() {
        ConfirmOperation request = new ConfirmOperation("1", "0000");

        ResponseEntity<String> forEntity = restTemplate.postForEntity(
                "http://localhost:" + myapp.getMappedPort(PORT) + "/confirmOperation", request, String.class);
        System.out.println(forEntity.getBody());
        String expected = "{\"operationId\":" + "\"1\"}";
        String actual = forEntity.getBody();
        Assertions.assertEquals(expected, actual);
    }

}
