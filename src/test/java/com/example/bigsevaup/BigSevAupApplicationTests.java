package com.example.bigsevaup;

import com.example.bigsevaup.Model.Dolj;
import com.example.bigsevaup.Model.Employee;
import com.example.bigsevaup.Model.Role;
import com.example.bigsevaup.Repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class BigSevAupApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    void
    T_Registartion() throws Exception {
        Employee us = new Employee();
        Dolj dolj = new Dolj();
        dolj.setId((long)65);
        us.setActive(true);
        us.setName("p0Beda!");
        us.setSurname("p0Beda!");
        us.setOtchestvo("p0Beda!");
        us.setUsername("p0Beda!");
        us.setPassword("p0Beda!");
        us.setRoles(Set.of(Role.ADMIN));
        us.setDolj(dolj);
        Mockito.when(employeeRepository.save(us)).thenReturn(Optional.of(us).orElseThrow());
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                        .content(objectMapper.writeValueAsString(us)))
                .andExpect(status().isFound());
    }

    @Test
    void
    T_RegistartionFailed() throws Exception {
        Employee us = new Employee();
        us.setActive(true);
        us.setName("p0Beda!");
        us.setSurname("p0Beda!");
        us.setOtchestvo("p0Beda!");
        us.setUsername("p0Beda!");
        us.setPassword("aaa!");
        Mockito.when(employeeRepository.save(us)).thenReturn(Optional.of(us).orElseThrow());
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                        .content(objectMapper.writeValueAsString(us)))
                .andExpect(status().isFound());
    }
}