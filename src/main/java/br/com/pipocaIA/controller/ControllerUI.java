package br.com.pipocaIA.controller;

import br.com.pipocaIA.dto.FilmeDTO;
import br.com.pipocaIA.dto.TmdbResponseDTO;
import br.com.pipocaIA.service.FilmeService;
import br.com.pipocaIA.service.GptRequestService;
import br.com.pipocaIA.service.TmdbFilmeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pipocaIA")
public class ControllerUI {

    private final FilmeService filmeService;
    private final GptRequestService gptRequestService;
    private final TmdbFilmeService tmdbFilmeService;

    public ControllerUI(FilmeService filmeService, GptRequestService gptRequestService, TmdbFilmeService tmdbFilmeService) {
        this.filmeService = filmeService;
        this.gptRequestService = gptRequestService;
        this.tmdbFilmeService = tmdbFilmeService;
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model, @RequestParam(name = "msg", required = false) String msg) {
        List<FilmeDTO> filmesRegistrados = filmeService.filmesRegistrados();
        model.addAttribute("filmes", filmesRegistrados);
        return "dashboard";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionar(Model model) {
        model.addAttribute("filme", new FilmeDTO());
        return "adicionar-filme";
    }

    @PostMapping("/adicionar")
    public String adicionarFilme(@ModelAttribute("filme") FilmeDTO filmeDTO) {
        filmeService.registrarFilme(filmeDTO);
        return "redirect:/pipocaIA/adicionar?msg=adicionado_ok";
    }

    @GetMapping("/recomendacoes")
    public String mostrarRecomendacoes(Model model) {
        List<TmdbResponseDTO> recomendacoes = gptRequestService.filmesSugeridos()
                .flatMap(tmdbFilmeService::filmesDetalhados)
                .block();

        model.addAttribute("recomendacoes", recomendacoes);

        return "recomendacoes";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarFilme(@PathVariable Long id) {
        filmeService.deletarFilme(id);
        return "redirect:/pipocaIA/dashboard";
    }

    @PutMapping("/alterar/{id}")
    public String alterarFilme(@PathVariable Long id, @ModelAttribute("filme") FilmeDTO filmeDTO) {
        filmeService.alterarFilme(id, filmeDTO);
        return "redirect:/pipocaIA/dashboard";
    }

    @GetMapping("/alterar/{id}")
    public String mostrarFormularioAlterar(@PathVariable Long id, Model model) {
        FilmeDTO filmeParaAlterar = filmeService.listarFilmeId(id);
        model.addAttribute("filme", filmeParaAlterar);
        return "alterar-filme";
    }

}
