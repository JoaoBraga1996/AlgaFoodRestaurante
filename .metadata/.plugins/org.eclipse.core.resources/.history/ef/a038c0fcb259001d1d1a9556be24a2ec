package apiCloneIfood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apiCloneIfood.domain.model.Restaurante;
import apiCloneIfood.domain.repository.RestauranteRepository;
import apiCloneIfood.insfracture.repository.spec.restauranteComFreteGratisSpec;
import apiCloneIfood.insfracture.repository.spec.restauranteComNomeSemelhanteSpe;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	
	@GetMapping("/restaurante/com-frete-gratis")
	public List<Restaurante> restauranteComFreteGratis(String nome){
		
		var comFreteGratis = new restauranteComFreteGratisSpec();
		var comNomeSemelhante = new restauranteComNomeSemelhanteSpe(nome);
		
		
		return  restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
	}

}
