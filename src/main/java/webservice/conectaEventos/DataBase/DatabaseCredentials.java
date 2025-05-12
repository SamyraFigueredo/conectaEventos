package webservice.conectaEventos.DataBase;

import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCredentials {

    private String username;
    private String password;

    public DatabaseCredentials() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome de usuário do banco de dados: ");
        this.username = scanner.nextLine();

        System.out.print("Digite a senha do banco de dados: ");
        this.password = scanner.nextLine();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
