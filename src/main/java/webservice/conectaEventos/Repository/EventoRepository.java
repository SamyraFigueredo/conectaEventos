package webservice.conectaEventos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webservice.conectaEventos.Model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
