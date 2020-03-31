package br.com.dynamicapp.softplayerapi.resources;

import java.net.URI;
import java.util.List;

import br.com.dynamicapp.softplayerapi.model.Produto;
import br.com.dynamicapp.softplayerapi.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> listar() {
		return ResponseEntity.ok().body(produtoService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPeloId(@PathVariable Long id) {

		// se nao encontrar um produto com o ID passado como parametro, retorna um 404 NOT FOUND
		Produto produto = produtoService.buscarPeloId(id);

		return ResponseEntity.ok().body(produto);
	}

	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Produto produto) {

		produto = produtoService.salvar(produto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualiazar(@PathVariable Long id, @RequestBody Produto produto) {

		// garantindo que vai atualizar o produto correto
		produto.setId(id);
		produtoService.atualizar(produto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		produtoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
