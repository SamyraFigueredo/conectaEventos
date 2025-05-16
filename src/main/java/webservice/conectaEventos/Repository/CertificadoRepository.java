package webservice.conectaEventos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webservice.conectaEventos.Model.Certificado;

import java.util.Optional;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Long> {
    Optional<Certificado> findByCodigoVerificacaoCertificado(String codigoVerificacaoCertificado);
}
