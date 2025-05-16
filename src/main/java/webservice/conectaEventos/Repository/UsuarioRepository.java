package webservice.conectaEventos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webservice.conectaEventos.Model.Usuario;
import webservice.conectaEventos.Model.enums.TipoUsuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailUsuario(String email);
    Optional<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
    Optional<Usuario> findById(Long id);
}
