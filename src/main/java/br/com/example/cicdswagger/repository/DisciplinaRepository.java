package br.com.example.cicdswagger.repository;

import br.com.example.cicdswagger.model.Disciplina;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class DisciplinaRepository {
    private final Map<Long, Disciplina> store = new HashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    @PostConstruct
    public void init() {
        // Simular duas disciplinas do curso de DSM
        save(new Disciplina(null, "Desenvolvimento Mobile I", "DSM"));
        save(new Disciplina(null, "Arquitetura de Software", "DSM"));
    }

    public List<Disciplina> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Disciplina> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Disciplina save(Disciplina disciplina) {
        if (disciplina.getId() == null) {
            disciplina.setId(seq.incrementAndGet());
        }
        store.put(disciplina.getId(), disciplina);
        return disciplina;
    }

    public void clear() {
        store.clear();
        seq.set(0);
    }
}
