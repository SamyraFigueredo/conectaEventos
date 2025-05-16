package webservice.conectaEventos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.conectaEventos.Model.Certificado;
import webservice.conectaEventos.Repository.CertificadoRepository;
import webservice.conectaEventos.Service.Utils.PdfGenerator;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CertificadoService {

    @Autowired
    private CertificadoRepository certificadoRepository;

    @Autowired
    private PdfGenerator pdfGenerator;

    public List<Certificado> listarTodos() {
        return certificadoRepository.findAll();
    }

    public Optional<Certificado> buscarPorId(Long id) {
        return certificadoRepository.findById(id);
    }

    public Optional<Certificado> buscarPorCodigo(String codigo) {
        return certificadoRepository.findByCodigoVerificacaoCertificado(codigo);
    }

    public Certificado salvar(Certificado certificado) {
        if (certificado.getCodigoVerificacaoCertificado() == null || certificado.getCodigoVerificacaoCertificado().isEmpty()) {
            certificado.setCodigoVerificacaoCertificado(UUID.randomUUID().toString());
        }

        // Define um caminho para salvar o PDF
        String caminho = "certificados/" + certificado.getCodigoVerificacaoCertificado() + ".pdf";
        certificado.setCaminhoArquivoCertificado(caminho);

        // Salva no banco de dados antes para garantir ID e relacionamentos
        Certificado certificadoSalvo = certificadoRepository.save(certificado);

        // Gera o PDF
        try {
            pdfGenerator.gerarCertificadoPdf(certificadoSalvo);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar o PDF do certificado", e);
        }

        return certificadoSalvo;
    }

    public Certificado atualizar(Long id, Certificado certificadoAtualizado) {
        return certificadoRepository.findById(id).map(certificado -> {
            certificado.setDataEmissaoCertificado(certificadoAtualizado.getDataEmissaoCertificado());
            certificado.setCodigoVerificacaoCertificado(certificadoAtualizado.getCodigoVerificacaoCertificado());
            certificado.setUsuario(certificadoAtualizado.getUsuario());
            certificado.setEvento(certificadoAtualizado.getEvento());
            certificado.setCaminhoArquivoCertificado(certificadoAtualizado.getCaminhoArquivoCertificado());
            return certificadoRepository.save(certificado);
        }).orElseThrow(() -> new RuntimeException("Certificado não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        certificadoRepository.deleteById(id);
    }

    public Path obterCaminhoArquivoCertificado(Long id) {
        return certificadoRepository.findById(id)
                .map(certificado -> Paths.get(certificado.getCaminhoArquivoCertificado()))
                .orElseThrow(() -> new RuntimeException("Certificado não encontrado"));
    }

}
