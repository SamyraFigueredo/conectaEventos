package webservice.conectaEventos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webservice.conectaEventos.Model.Ministrante;

@Repository
public interface MinistranteRepository extends JpaRepository<Ministrante, Long> {
}
