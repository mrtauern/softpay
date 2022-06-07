package com.example.demo.controllerTests;

import com.example.demo.controllers.TransactionController;
import com.example.demo.models.Input;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.services.AccountServiceImpl;
import com.example.demo.services.TransactionServiceImpl;
import org.junit.Before;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
//import org.testng.annotations.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(TransactionController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Spy
    private TransactionController transactionControllerSpy;

    @MockBean
    private TransactionServiceImpl transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private AccountServiceImpl accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void newTransaction() throws Exception {
        Input input = new Input(2100, "DKK", 274747234, 28, 1234567890123456L);

        ResultActions resultActions = mockMvc.perform(post("/input").contentType(MediaType.APPLICATION_JSON)
                .flashAttr("input", input))
                .andExpect(status().is2xxSuccessful());

        MvcResult mvcResult = resultActions.andReturn();
        ModelAndView mv = mvcResult.getModelAndView();
    }
}
