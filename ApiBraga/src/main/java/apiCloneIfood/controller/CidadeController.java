package apiCloneIfood.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import apiCloneIfood.domain.model.Cidade;
import apiCloneIfood.domain.model.exception.EntidadeEmUsoException;
import apiCloneIfood.domain.model.exception.EntidadeEmUsoException;
import apiCloneIfood.domain.model.exception.EntidadeNaoEncontradaException;
import apiCloneIfood.domain.model.exception.EntidadeNaoEncontradaException;
import apiCloneIfood.domain.repository.CidadeRepository;
import apiCloneIfood.domain.services.CidadeService;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();

	}

	@GetMapping("/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable long cidadeId) {

		Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);
		
		if(cidadeAtual.isPresent()) {
			return ResponseEntity.ok(cidadeAtual.get());
		}
        
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void adicionar(@RequestBody Cidade cidade) {
		cidadeService.salvar(cidade);
	}

	@PutMapping("/{cidadeId}")
	public ResponseEntity<Cidade> atualizar(@PathVariable long cidadeId, @RequestBody Cidade cidade) {

		Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);
		if(cidadeAtual.isPresent()) {
			
		

		BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");

		
		Cidade cidadeComum = cidadeService.salvar(cidadeAtual.get());
		
		return ResponseEntity.ok(cidadeComum);
		
		}
            return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {
		try {
			cidadeService.excluir(cidadeId);	
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
