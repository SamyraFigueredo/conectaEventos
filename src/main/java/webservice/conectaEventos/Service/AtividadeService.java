package webservice.conectaEventos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.conectaEventos.Model.Atividade;
import webservice.conectaEventos.Repository.AtividadeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public List<Atividade> listarTodas() {
        return atividadeRepository.findAll();
    }

    public Optional<Atividade> buscarPorId(Long id) {
        return atividadeRepository.findById(id);
    }

    public Atividade salvar(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public Atividade atualizar(Long id, Atividade novaAtividade) {
        return atividadeRepository.findById(id)
                .map(atividade -> {
                    atividade.setNomeAtividade(novaAtividade.getNomeAtividade());
                    atividade.setHorarioAtividade(novaAtividade.getHorarioAtividade());
                    atividade.setUsuario(novaAtividade.getUsuario());
                    atividade.setEvento(novaAtividade.getEvento());
                    atividade.setTipoUsuario(novaAtividade.getTipoUsuario());
                    return atividadeRepository.save(atividade);
                })
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
    }

    public void deletar(Long id) {
        atividadeRepository.deleteById(id);
    }
}