package webservice.conectaEventos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webservice.conectaEventos.Model.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}
