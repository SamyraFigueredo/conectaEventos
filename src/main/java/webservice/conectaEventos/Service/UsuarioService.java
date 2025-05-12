package webservice.conectaEventos.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import webservice.conectaEventos.Model.Usuario;
import webservice.conectaEventos.Repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + id));
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = buscarPorId(id);

        usuarioExistente.nomeUsuario = usuarioAtualizado.nomeUsuario;
        usuarioExistente.dataNascimento = usuarioAtualizado.dataNascimento;
        usuarioExistente.emailUsuario = usuarioAtualizado.emailUsuario;
        usuarioExistente.senhaUsuario = usuarioAtualizado.senhaUsuario;
        usuarioExistente.tipoUsuario = usuarioAtualizado.tipoUsuario;


        return usuarioRepository.save(usuarioExistente);
    }

    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com o ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmailUsuario(email);
    }
}
