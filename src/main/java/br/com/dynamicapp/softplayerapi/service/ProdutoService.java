package br.com.dynamicapp.softplayerapi.service;

import java.util.List;

import br.com.dynamicapp.softplayerapi.model.Produto;
import br.com.dynamicapp.softplayerapi.service.exceptions.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.dynamicapp.softplayerapi.respository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	
	public Produto buscarPeloId(Long id) {
		
		Produto produto = produtoRepository.findOne(id);
		
		if(produto == null) {
			
			/* neste caso ira lancar uma RecursoNaoEncontradoException,  que ira chamar o ExceptionHandler 
			 * correspondente da classe RecursosExceptionHandler do pacote handler, quer ira 
			 *retornar um 404 para o cliente com detalhes da possivel causa do erro */
			throw new RecursoNaoEncontradoException("Nenhum produto foi encontrado");
		}
		
		return produto;
	}
	
	public Produto salvar(Produto produto) {
		
		/* este metodo nao faz atualizacao por isso e preciso garantir que o id sera sempre nulo,
		 * caso contrario o produto sera atualizado em vez de criado um novo produto
		 * */
		
		produto.setId(null);
		return produtoRepository.save(produto);
	}
	
	/**
	 * Metodo para verificar a existencia de um produto.
	 * Chama o metodo buscarPeloId que se nao encontrar
	 * o produto lanca uma excecao que lanca um NOT FOUND
	 * @param produto
	 */
	public void verificarExistencia(Produto produto) {
		buscarPeloId(produto.getId());
	}
	
	public void atualizar(Produto produto) {
		
		/* vericando se o produto realmente existe, poderia ter
		 * chamado o metodo buscarPeloId direto, mas criei o metodo
		 *  verificar existencia para ajudar na legibilidade do codigo */
		verificarExistencia(produto);
		produtoRepository.save(produto);
	}
	
	public void deletar(Long id) {
		
		try {			
			produtoRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException("O produto não foi encontrado");
		}
	}

}
