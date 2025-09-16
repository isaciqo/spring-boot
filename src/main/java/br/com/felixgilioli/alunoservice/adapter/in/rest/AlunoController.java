package br.com.felixgilioli.alunoservice.adapter.in.rest;

import br.com.felixgilioli.alunoservice.domain.entity.Aluno;
import br.com.felixgilioli.alunoservice.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public record AlunoController(AlunoService alunoService) {

    @PostMapping
    @Operation(description = "Salva um novo aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados do aluno inválidos")
    })
    public Aluno salvar(@RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    @GetMapping("/{id}")
    @Operation(description = "Busca o aluno pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o aluno"),
            @ApiResponse(responseCode = "404", description = "Não existe o aluno com o id informado")
    })
    public Aluno buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id);
    }

    @GetMapping
    @Operation(description = "Lista todos os alunos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de alunos")
    })
    public List<Aluno> listarTodos() {
        return alunoService.listarTodos();
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualiza um aluno existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não existe o aluno com o id informado")
    })
    public Aluno atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        return alunoService.atualizar(id, aluno);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Remove um aluno pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não existe o aluno com o id informado")
    })
    public void deletar(@PathVariable Long id) {
        alunoService.deletar(id);
    }
}
