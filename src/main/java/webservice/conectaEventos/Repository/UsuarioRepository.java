package webservice.conectaEventos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webservice.conectaEventos.Model.Usuario;
import webservice.conectaEventos.Model.enums.TipoUsuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmailUsuario(String emailUsuario);
    Optional<Usuario> findByidUsuario(Long IDUsuario);
    Optional<Usuario>findByTipoUsuario(TipoUsuario tipoUsuario);
}
