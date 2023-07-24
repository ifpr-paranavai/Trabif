package br.com.trabif.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.Migration;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.MigrationRepository;

@Service
public class MigrationService {
	
	@Autowired
	private MigrationRepository migrationRepository;
	
	private boolean existsById(Long id) {
		return migrationRepository.existsById(id);
	}
	
	public Migration findById(Long id) throws ResourceNotFoundException {
		Migration migration = migrationRepository.findById(id).orElse(null);
		
		if(migration == null) {
			throw new ResourceNotFoundException("Migration não encontrada com o id: " + id);
		} else {
			return migration;
		}
	}
	
	public List<Migration> findAll() {
		return migrationRepository.findAll();
	}

	public Migration save(Migration migration) throws BadResourceException, ResourceAlreadyExistsException {
		if(!migration.getNomeArquivo().isEmpty()) {
			if(existsById(migration.getId())) {
				throw new ResourceAlreadyExistsException("Migration com id: " + migration.getId() + " já existe.");
			}
			migration.setDataExecucao(Calendar.getInstance().getTime());
			return migrationRepository.save(migration);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar migration");
			exe.addErrorMessage("Migration esta vazia ou nula");
			throw exe;
		}
	}

	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Migration não encontrada com o id: " + id);
		} else {
			migrationRepository.deleteById(id);
		}

	}  public Long count() {
		return migrationRepository.count();
	}

}
