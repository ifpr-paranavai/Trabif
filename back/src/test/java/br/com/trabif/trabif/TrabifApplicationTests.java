// package br.com.trabif.trabif;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// import br.com.trabif.domain.Usuario;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// /**
//  * Classe de exemplo para sererm realizados os testes da aplicação
//  * 
//  * Exemplo de mock usando o MockMvc
//  */



// @ExtendWith(SpringExtension.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @AutoConfigureMockMvc
// @AutoConfigureTestDatabase
// public class TrabifApplicationTests {

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private TestRestTemplate restTemplate;

//     @Test
//     public void testMyController() throws Exception {
//         MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/myendpoint"))
//                 .andReturn();

//         assertEquals(HttpStatus.OK, result.getResponse().getStatus());
//     }

//     @Test
//     public void testMyEndpoint() {
//         String response = restTemplate.getForObject("/api/myendpoint", String.class);

//         assertEquals("Test response", response);
//     }

// 	@Test
//     void shouldReturnUser() {
//         ResponseEntity<Usuario> responseEntity = restTemplate.getForEntity("/usuario/{id}", Usuario.class, 1L);

//         assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//         assertNotNull(responseEntity.getBody());
//         assertEquals(1L, responseEntity.getBody().getId());
//         assertEquals("John", responseEntity.getBody().getNome());
//         assertEquals("john@test.com", responseEntity.getBody().getEmail());
//     }
	
// }