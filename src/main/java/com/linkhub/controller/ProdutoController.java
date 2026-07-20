package com.linkhub.controller;

import com.linkhub.dto.ProdutoRequest;
import com.linkhub.model.Produto;
import com.linkhub.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    // GET /api/produtos -> usado pela tela de admin (lista tudo)
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    // GET /api/produtos/ativos -> usado pela landing page pública
    @GetMapping("/ativos")
    public ResponseEntity<List<Produto>> listarOfertasAtivas() {
        return ResponseEntity.ok(produtoService.listarOfertasAtivas());
    }

    // GET /api/produtos/{id} -> tela de edição no admin, por exemplo
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    // POST /api/produtos -> cadastro de novo produto
    @PostMapping
    public ResponseEntity<Produto> cadastrar(@Valid @RequestBody ProdutoRequest request) {
        Produto novoProduto = produtoService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    // PUT /api/produtos/{id} -> edição de produto existente
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Integer id,
                                             @Valid @RequestBody ProdutoRequest request) {
        return ResponseEntity.ok(produtoService.atualizar(id, request));
    }

    // DELETE /api/produtos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}