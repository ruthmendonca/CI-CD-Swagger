package br.com.example.cicdswagger.controller;

import br.com.example.cicdswagger.model.Disciplina;
import br.com.example.cicdswagger.repository.DisciplinaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DisciplinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DisciplinaRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        // garantir estado conhecido antes de cada teste
        repository.clear();
        repository.save(new Disciplina(null, "Desenvolvimento Multiplataforma I", "DSM"));
        repository.save(new Disciplina(null, "Integração e Entrega Contínua", "DSM"));
    }

    @Test
    void deveListarTodasDisciplinas() throws Exception {
        mockMvc.perform(get("/disciplinas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void deveBuscarDisciplinaPorId() throws Exception {
    // id 1 existe após o setup
    mockMvc.perform(get("/disciplinas/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome", is("Desenvolvimento Multiplataforma I")));
    }

    @Test
    void deveCriarNovaDisciplina() throws Exception {
        Disciplina nova = new Disciplina(null, "Testes Automatizados", "DSM");
        String json = objectMapper.writeValueAsString(nova);

        mockMvc.perform(post("/disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome", is("Testes Automatizados")));
    }
}
