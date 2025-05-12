package webservice.conectaEventos.Model;

import jakarta.persistence.*;
import lombok.*;
import webservice.conectaEventos.Model.enums.TipoUsuario;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    public Long idUsuario;

    @Column(name = "nome_usuario", nullable = false, length = 100)
    public String nomeUsuario;

    @Column(name = "data_nascimento", nullable = false)
    public LocalDate dataNascimento;

    @Column(name = "email_usuario", nullable = false, length = 100, unique = true)
    public String emailUsuario;

    @Column(name = "senha_usuario", nullable = false)
    public String senhaUsuario;

    @Enumerated(EnumType.STRING)
    public TipoUsuario tipoUsuario;
}