package br.com.trabif.migrations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.trabif.domain.Migration;
import br.com.trabif.domain.Permissao;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.service.MigrationService;
import br.com.trabif.service.PermissaoService;

@Component
@Order(1)
public class Migration1 implements CommandLineRunner {

    private String migrationName = Migration1.class.getName();

    @Autowired
    private MigrationService migrationService;
    
    @Autowired
	private PermissaoService permissaoService;

    public void execute() {
        String usuarioCadastro = "Sistema";
        Permissao autor = new Permissao();
        autor.setId(1);
        autor.setDescricao("Autor");
        autor.setUsuarioCadastro(usuarioCadastro);

        Permissao organizador = new Permissao();
        organizador.setId(2);
        organizador.setDescricao("Organizador");
        organizador.setUsuarioCadastro(usuarioCadastro);

        Permissao avaliador = new Permissao();
        avaliador.setId(3);
        avaliador.setDescricao("Avaliador");
        avaliador.setUsuarioCadastro(usuarioCadastro);

        try {
            permissaoService.save(autor);
            permissaoService.save(organizador);
            permissaoService.save(avaliador);
        } catch (BadResourceException | ResourceAlreadyExistsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        saveMigrationExecuted();
    }
    @Override
    public void run(String... args) throws Exception {
        List<Migration> migrationsAlreadyRun = migrationService.findAll();
        boolean podeExecutar = true;

        for (Migration m : migrationsAlreadyRun) {
            if (m.getNomeArquivo().equals(migrationName)) {
                podeExecutar = false;
                break;
            }
        }

        if (podeExecutar) {
            execute();
        }
    }

    public void saveMigrationExecuted() {
        Migration migration = new Migration();
        migration.setNomeArquivo(migrationName);
        try {
            migrationService.save(migration);
        } catch (BadResourceException | ResourceAlreadyExistsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
