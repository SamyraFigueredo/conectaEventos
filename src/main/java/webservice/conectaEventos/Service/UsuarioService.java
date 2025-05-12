package webservice.conectaEventos.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webservice.conectaEventos.Model.Usuario;
import webservice.conectaEventos.Repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findByidUsuario(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + id));
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = buscarPorId(id);

        usuarioExistente.setNomeUsuario(usuarioAtualizado.getNomeUsuario());
        usuarioExistente.setDataNascimento(usuarioAtualizado.getDataNascimento());
        usuarioExistente.setEmailUsuario(usuarioAtualizado.getEmailUsuario());
        usuarioExistente.setSenhaUsuario(usuarioAtualizado.getSenhaUsuario());

        return usuarioRepository.save(usuarioExistente);
    }

    public void deletarUsuario(Long id) {
        String idString = String.valueOf(id);
        if (!usuarioRepository.existsById(idString)) {
            throw new EntityNotFoundException("Usuário não encontrado com o ID: " + idString);
        }
        usuarioRepository.deleteById(idString);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmailUsuario(email);
    }
}
