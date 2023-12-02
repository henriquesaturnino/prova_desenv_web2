package org.senai.ecommerce.repository;

import org.senai.ecommerce.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByCodig(Long codigo);

    List<Produto> findByNome(String nome);

    List<Produto> findByNomeIgnoreCaseContains(String nome);
}
