package br.com.batista.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carro")
public class Carro {

	@GetMapping()
	public String get() {
		return "funcionou";
	}

}
