package com.example.work;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.work.dto.ConvertResponse;
import com.example.work.dto.CurrencyRequest;
import com.example.work.dto.CurrencyResponse;
import com.example.work.service.CoindeskService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@Autowired
	CoindeskService coindeskService;
	
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;
	
	@Test
	void contextLoads() {
	}

	@Test
	void CoindeskServiceTest() {
        ResponseEntity<String> responseEntity =
                testRestTemplate.getForEntity("/coindesk/", String.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody()); 
	}
	
	@Test
	void CoindeskServiceConvertTest() {
        ResponseEntity<ConvertResponse> responseEntity =
                testRestTemplate.getForEntity("/coindesk/convert", ConvertResponse.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
	}
	
	@Test
	void CurrencyServiceIntegratedTest() {
		//新增
		CurrencyRequest req = new CurrencyRequest();
		req.setId(0);
		req.setName("TWD");
		req.setCname("台幣");
        ResponseEntity<CurrencyResponse> responseEntity1 =
                testRestTemplate.postForEntity("/currency/", req ,CurrencyResponse.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());
        Assertions.assertNotNull(responseEntity1.getBody());        
        
		//查詢
        ResponseEntity<CurrencyResponse> responseEntity2 =
                testRestTemplate.getForEntity("/currency/1", CurrencyResponse.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
        Assertions.assertNotNull(responseEntity2.getBody());
        
        //更新
        req = new CurrencyRequest();
		req.setId(1);
		req.setName("USD");
		req.setCname("美金");
        testRestTemplate.put("/currency/1", req, new HashMap<>());
        
        //查詢
        ResponseEntity<CurrencyResponse> responseEntity3 =
                testRestTemplate.getForEntity("/currency/1", CurrencyResponse.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity3.getStatusCode());
        Assertions.assertNotNull(responseEntity3.getBody());  
        Assertions.assertEquals("USD",responseEntity3.getBody().getName());
         
        //刪除
        testRestTemplate.delete("/currency/1");

		//查詢
        ResponseEntity<CurrencyResponse> responseEntity4 =
                testRestTemplate.getForEntity("/currency/1", CurrencyResponse.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity4.getStatusCode());
         
	}
}
