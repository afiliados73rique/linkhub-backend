package com.linkhub.service;

import com.linkhub.dto.ProdutoRequest;
import com.linkhub.dto.ProdutoResponse;
import com.linkhub.model.Produto;
import com.linkhub.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    // Lista todos os produtos - usado, por exemplo, na tela de admin (tabela completa)
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Lista só as ofertas ativas - útil pra landing page pública
    public List<Produto> listarOfertasAtivas() {
        return produtoRepository.findAll().stream()
                .filter(Produto::getOfertaAtiva)
                .toList();
    }

    // Busca por id, lançando 404 se não existir.
    // Centralizar essa busca aqui evita repetir o findById + orElseThrow em todo lugar.
    public Produto buscarPorId(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto não encontrado com id " + id));
    }

    // Cadastra um novo produto a partir do DTO
    @Transactional
    public Produto cadastrar(ProdutoRequest request) {
        Produto produto = new Produto();
        preencherEntidade(produto, request);
        return produtoRepository.save(produto);
    }

    // Atualiza um produto existente
    @Transactional
    public Produto atualizar(Integer id, ProdutoRequest request) {
        Produto produto = buscarPorId(id); // já lança 404 se não existir
        preencherEntidade(produto, request);
        return produtoRepository.save(produto);
    }

    // Deleta um produto, validando antes que ele existe
    @Transactional
    public void deletar(Integer id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }

    // Método auxiliar privado - evita duplicar a cópia de campos entre cadastrar() e atualizar()
    private void preencherEntidade(Produto produto, ProdutoRequest request) {
        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPreco(request.getPreco());
        produto.setImagem(request.getImagem());
        produto.setLinkAfiliado(request.getLinkAfiliado());
        produto.setOfertaAtiva(request.getOfertaAtiva());
    }

    //Método devolve o ProdutoResponse invés de Poduto direto
    private ProdutoResponse paraResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getImagem(),
                produto.getLinkAfiliado(),
                produto.getOfertaAtiva(),
                produto.getDataCadastro()
        );
    }

    // Inativa um produto (não deleta, só marca ofertaAtiva como false)
    @Transactional
    public Produto inativar(Integer id) {
        Produto produto = buscarPorId(id); // reaproveita a busca que já lança 404 se não existir
        produto.setOfertaAtiva(false);
        return produtoRepository.save(produto);
    }
}