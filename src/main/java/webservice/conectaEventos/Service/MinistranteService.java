package webservice.conectaEventos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.conectaEventos.Model.Ministrante;
import webservice.conectaEventos.Repository.MinistranteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MinistranteService {

    @Autowired
    private MinistranteRepository ministranteRepository;

    public List<Ministrante> listarTodos() {
        return ministranteRepository.findAll();
    }

    public Optional<Ministrante> buscarPorId(Long id) {
        return ministranteRepository.findById(id);
    }

    public Ministrante salvar(Ministrante ministrante) {
        return ministranteRepository.save(ministrante);
    }

    public Ministrante atualizar(Long id, Ministrante ministranteAtualizado) {
        return ministranteRepository.findById(id).map(ministrante -> {
            ministrante.setBiografia(ministranteAtualizado.getBiografia());
            ministrante.setTipoAtividade(ministranteAtualizado.getTipoAtividade());
            ministrante.setUsuario(ministranteAtualizado.getUsuario());
            ministrante.setAtividades(ministranteAtualizado.getAtividades());
            return ministranteRepository.save(ministrante);
        }).orElseThrow(() -> new RuntimeException("Ministrante não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        ministranteRepository.deleteById(id);
    }
}
