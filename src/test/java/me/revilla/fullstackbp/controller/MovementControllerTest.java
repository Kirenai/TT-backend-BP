package me.revilla.fullstackbp.controller;

import me.revilla.fullstackbp.dto.AccountDto;
import me.revilla.fullstackbp.dto.CustomerDto;
import me.revilla.fullstackbp.dto.MovementDto;
import me.revilla.fullstackbp.dto.PersonDto;
import me.revilla.fullstackbp.service.MovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebMvcTest(value = MovementController.class)
class MovementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovementService movementService;

    private final StringBuilder URL = new StringBuilder("/api/movements");

    private List<MovementDto> movementDtoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        PersonDto personDto = PersonDto.builder()
                .personId(2L)
                .name("Marianela Montalvo")
                .gender("Female")
                .age(32)
                .identification("56744356")
                .address("Amazonas y NNUU")
                .phone("097548965")
                .build();

        CustomerDto customerDto = CustomerDto.builder()
                .customerId(2L)
                .password("5678")
                .state(true)
                .person(personDto)
                .build();

        AccountDto corriente = AccountDto.builder().
                accountId(2L)
                .accountNumber("225487")
                .accountType("Corriente")
                .initialBalance(100)
                .state(true)
                .customer(customerDto)
                .build();

        AccountDto ahorros = AccountDto.builder().
                accountId(4L)
                .accountNumber("496825")
                .accountType("Ahorros")
                .initialBalance(540)
                .state(true)
                .customer(customerDto)
                .build();

        MovementDto movementOne = MovementDto.builder()
                .movementId(1L)
                .date(LocalDate.parse("2022-02-10"))
                .value(600)
                .balance(700)
                .movementType("DEPOSITO")
                .account(corriente)
                .build();

        MovementDto movementTwo = MovementDto.builder()
                .movementId(2L)
                .date(LocalDate.parse("2022-02-08"))
                .value(-540)
                .balance(700)
                .movementType("RETIRO")
                .account(ahorros)
                .build();

        this.movementDtoList.add(movementOne);
        this.movementDtoList.add(movementTwo);
    }

    @Test
    @DisplayName("Should return a list of movements by date and customer")
    void shouldReturnListOfMovementsByDateAndCustomer() throws Exception {
        Mockito.when(this.movementService.findMovementsByDateAndCustomer(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(this.movementDtoList);


        RequestBuilder request = MockMvcRequestBuilders
                .get(this.URL.append("/reports?date=2022-01-08 2022-02-15&customerId=2").toString())
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value(this.movementDtoList.get(0).getDate().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].client").value(movementDtoList.get(0).getAccount().getCustomer().getPerson().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].accountNumber").value(this.movementDtoList.get(0).getAccount().getAccountNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value(this.movementDtoList.get(0).getMovementType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].initialBalance").value(this.movementDtoList.get(0).getAccount().getInitialBalance()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].state").value(this.movementDtoList.get(0).getAccount().getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].movement").value(this.movementDtoList.get(0).getValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].balance").value(this.movementDtoList.get(0).getBalance()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].date").value(this.movementDtoList.get(1).getDate().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].client").value(movementDtoList.get(1).getAccount().getCustomer().getPerson().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].accountNumber").value(this.movementDtoList.get(1).getAccount().getAccountNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].type").value(this.movementDtoList.get(1).getMovementType()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].initialBalance").value(this.movementDtoList.get(1).getAccount().getInitialBalance()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].state").value(this.movementDtoList.get(1).getAccount().getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].movement").value(this.movementDtoList.get(1).getValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].balance").value(this.movementDtoList.get(1).getBalance()));
    }

    @Test
    @DisplayName("Should return a empty list of movements by date and customer")
    void shouldReturnEmptyListOfMovementsByDateAndCustomer() throws Exception {
        Mockito.when(this.movementService.findMovementsByDateAndCustomer(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(Collections.emptyList());


        RequestBuilder request = MockMvcRequestBuilders
                .get(this.URL.append("?date=2022-01-08 2022-02-05&customerId=2").toString())
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }
}