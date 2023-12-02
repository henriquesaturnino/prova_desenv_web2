package org.senai.ecommerce.service;

import jakarta.persistence.EntityNotFoundException;
import org.senai.ecommerce.entity.Produto;
import org.senai.ecommerce.repository.ProdutoRepository;
import org.senai.ecommerce.repository.dto.NovoProdutoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService{

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> getProdutoPorCodigo(Long codigo) {
        return produtoRepository.findById(codigo);
    }


    public Long criarProduto(NovoProdutoDTO novoProdutoDTO) {
        Produto produto = new Produto();
        produto.setNome(novoProdutoDTO.getNome());
        produto.setDescricao(novoProdutoDTO.getDescricao());
        produto.setPreco(novoProdutoDTO.getPreco());
        produto.setEstoque(novoProdutoDTO.getEstoque());
        produto = produtoRepository.save(produto);
        return produto.getCodigo();
    }

    public ProdutoRepository getRepo(){
        return produtoRepository;
    }
}
