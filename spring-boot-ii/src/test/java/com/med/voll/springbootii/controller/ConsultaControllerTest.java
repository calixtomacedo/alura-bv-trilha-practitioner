package com.med.voll.springbootii.controller;

import com.med.voll.springbootii.domain.enuns.Especialidade;
import com.med.voll.springbootii.domain.records.AgendamentoConsulta;
import com.med.voll.springbootii.domain.records.DetalheConsulta;
import com.med.voll.springbootii.service.ConsultaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @MockBean
    private ConsultaService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AgendamentoConsulta> agendamentoJson;

    @Autowired
    private JacksonTester<DetalheConsulta> detalheJson;

    @Test
    @DisplayName("Deveria retornar status code 400 quando as informações do request estão inválidas")
    @WithMockUser
    void createScheduleCenario1() throws Exception {
        var response = mockMvc.perform(post("/consultas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar status code 200 quando as informações do request estão válidas")
    @WithMockUser
    void createScheduleCenario2() throws Exception {
        var dataAgendamento = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        var detalhes = new DetalheConsulta(null, 1L, 2L, dataAgendamento);
        when(service.createSchedule(any())).thenReturn(detalhes);

        var response = mockMvc.perform(
                post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(agendamentoJson.write(
                                new AgendamentoConsulta(1L, 2L, dataAgendamento, especialidade)
                        ).getJson())
                )
                .andReturn()
                .getResponse();

        var jsonResponse = detalheJson.write(detalhes).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse);
    }

}