package apiCloneIfood.domain.repository;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apiCloneIfood.domain.model.Cidade;
import apiCloneIfood.domain.model.Cozinha;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
List<Cozinha> findTodasByNomeContaining(String nome);
	
	Optional<Cozinha> findByNome(String nome);
	
	boolean existsByNome(String nome);
	
}


