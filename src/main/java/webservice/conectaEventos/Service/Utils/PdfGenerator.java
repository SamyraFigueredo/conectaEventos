package webservice.conectaEventos.Service.Utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;
import webservice.conectaEventos.Model.Certificado;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Component
public class PdfGenerator {

    public void gerarCertificadoPdf(Certificado certificado) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 750);
                contentStream.showText("CERTIFICADO DE PARTICIPAÇÃO");
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);

                contentStream.showText("Este é para certificar que:");
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("O(a) participante: " + certificado.getUsuario().getNomeUsuario());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Participou do evento: " + certificado.getEvento().getNomeEvento());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("No período de: " + certificado.getEvento().getDataInicioEvento().toLocalDate().format(formatter)
                        + " a " + certificado.getEvento().getDataFimEvento().toLocalDate().format(formatter));
                contentStream.newLineAtOffset(0, -40);
                contentStream.showText("Emitido em: " + certificado.getDataEmissaoCertificado().format(formatter));
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Código de verificação: " + certificado.getCodigoVerificacaoCertificado());

                contentStream.endText();
            }

            document.save(certificado.getCaminhoArquivoCertificado());
        }
    }
}
