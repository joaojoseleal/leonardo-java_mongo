package br.com.dynamicapp.softplayerapi.respository;

import br.com.dynamicapp.softplayerapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
