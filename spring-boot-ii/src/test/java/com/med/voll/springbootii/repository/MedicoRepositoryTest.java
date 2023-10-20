package com.med.voll.springbootii.repository;

import com.med.voll.springbootii.domain.Consulta;
import com.med.voll.springbootii.domain.Medico;
import com.med.voll.springbootii.domain.Paciente;
import com.med.voll.springbootii.domain.enuns.Especialidade;
import com.med.voll.springbootii.domain.records.CadastroMedico;
import com.med.voll.springbootii.domain.records.CadastroPaciente;
import com.med.voll.springbootii.domain.records.DadosEndereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private MedicoRepository repository;

    @Test
    @DisplayName("Deveria retornar null quando o único médico cadastrado não estiver disponível na data")
    void chooseFreeRandomDoctorOnDateCenario1() {
        // given or arrange
        var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        // when or act
        var medicoLivre = repository.chooseFreeRandomDoctorOnDate(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        // then or assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria retornar o médico quando ele estiver disponível na data")
    void chooseFreeRandomDoctorOnDateCenario2() {
        // given or arrange
        var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        // when or act
        var medicoLivre = repository.chooseFreeRandomDoctorOnDate(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        // then or assert
        assertThat(medicoLivre).isEqualTo(medico);
    }


    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data));
    }

    private CadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new CadastroMedico(nome, email, "61999999999", crm, especialidade, dadosEndereco());
    }

    private CadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new CadastroPaciente(nome, email, "61999999999", cpf, dadosEndereco());
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco("rua xpto", "12", "bairro", "00000000", "Brasilia", "DF", "casa 1");
    }

}