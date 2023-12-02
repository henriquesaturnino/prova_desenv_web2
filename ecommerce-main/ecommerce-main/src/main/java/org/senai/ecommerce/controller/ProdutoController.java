package org.senai.ecommerce.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.senai.ecommerce.entity.Produto;
import org.senai.ecommerce.repository.dto.NovoProdutoDTO;
import org.senai.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/lista")
    @Operation(summary = "Busca todos os produtos", description = "Esse metodo irá retornar todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os produtos"),
            @ApiResponse(responseCode = "403", description = "Não autorizado")
    })
    public ResponseEntity<List<Produto>> getProdutos(){
        return new ResponseEntity<>(produtoService.getProdutos(), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    @Operation(summary = "Busca o produto por código", description = "Esse metodo irá retornar apenas produtos" +
            "através do código inserido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os produtos"),
            @ApiResponse(responseCode = "403", description = "Não autorizado")
    })
    public ResponseEntity<?> getProdutoPorCodigo(@PathVariable Long codigo) {
        return new ResponseEntity<>(produtoService.getProdutoPorCodigo(codigo), HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(summary = "Adicionar produto", description = "Esse método irá permitir salvar os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso!"),
            @ApiResponse(responseCode = "403", description = "Não autorizado")
    })
    public ResponseEntity<?> criarProduto(@RequestBody NovoProdutoDTO produto) {
        return new ResponseEntity<>(produtoService.criarProduto(produto), HttpStatus.CREATED);
    }

    @PutMapping("/{codigo}")
    @Operation(summary = "Alterar produto", description = "Esse método irá permitir alterar o produto através " +
            "de um código válido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto alterado com sucesso!"),
            @ApiResponse(responseCode = "403", description = "Não autorizado")
    })
    public ResponseEntity<Produto> atualizarProduto(@RequestBody Produto produto, @PathVariable("codigo") Long codigo){
        Optional<Produto> produtoOptional = produtoService.getRepo().findById(codigo);
        if (produtoOptional.isPresent()){
            Produto produtoPersistir = produtoOptional.get();
            produtoPersistir.setNome(produto.getNome());
            produtoPersistir.setDescricao(produto.getDescricao());
            produtoService.getRepo().save(produtoPersistir);
        }
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @DeleteMapping("/{codigo}")
    @Operation(summary = "Deletar produto", description = "Esse método irá permitir deletar o produto através " +
            "de um código válido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto deletado com sucesso!"),
            @ApiResponse(responseCode = "403", description = "Não autorizado")
    })
    public ResponseEntity<?> deletarProduto(@PathVariable("codigo") Long codigo){
        produtoService.getRepo().deleteById(codigo);
        return new ResponseEntity<>("Produto excluído com sucesso", HttpStatus.OK);
    }
}
