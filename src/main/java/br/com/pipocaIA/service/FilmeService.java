package br.com.pipocaIA.service;

import br.com.pipocaIA.dto.FilmeDTO;
import br.com.pipocaIA.dto.mapper.FilmeMapper;
import br.com.pipocaIA.enums.Categoria;
import br.com.pipocaIA.model.FilmeModel;
import br.com.pipocaIA.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    private FilmeRepository filmeRepository;
    private FilmeMapper filmeMapper;

    public FilmeService(FilmeRepository filmeRepository, FilmeMapper filmeMapper) {
        this.filmeRepository = filmeRepository;
        this.filmeMapper = filmeMapper;
    }


    public List<FilmeDTO> filmesRegistrados(){
        List<FilmeModel> filmeModelList = filmeRepository.findAll();
        return filmeModelList.stream()
                .map(filmeMapper::map)
                .collect(Collectors.toList());
    }

    public List<FilmeDTO> listarFilmesCategoria(Categoria categoria){
        List<FilmeModel> filmeModelCategoriaList = filmeRepository.findAllByCategoria(categoria);
        return filmeModelCategoriaList.stream()
                .map(filmeMapper::map)
                .collect(Collectors.toList());
    }

    public FilmeDTO listarFilmeId(Long id){
        Optional<FilmeModel> filmeModelProcurado = filmeRepository.findById(id);

        if (filmeModelProcurado.isPresent()){
            FilmeModel filmeModelEncontrado = filmeModelProcurado.get();
            return filmeMapper.map(filmeModelEncontrado);
        } return null;
    }

    public FilmeDTO registrarFilme(FilmeDTO filmeDTO){
        FilmeModel filmeModel = filmeMapper.map(filmeDTO);
        filmeModel.setNota(filmeDTO.getNota() * 2);
        FilmeModel filmeModelNovo = filmeRepository.save(filmeModel);
        return filmeMapper.map(filmeModelNovo);
    }

    public boolean deletarFilme(Long id){
        Optional<FilmeModel> filmeModelProcurado = filmeRepository.findById(id);

        if(filmeModelProcurado.isPresent()){
            filmeRepository.deleteById(id);
            return true;
        } return false;
    }

    public FilmeDTO alterarFilme(Long id, FilmeDTO filmeDTO){
        Optional<FilmeModel> filmeModelProcurado = filmeRepository.findById(id);

        if(filmeModelProcurado.isPresent()){
            FilmeModel filmeModelAtualizado = filmeMapper.map(filmeDTO);
            filmeModelAtualizado.setId(id);
            filmeModelAtualizado.setNota(filmeDTO.getNota() * 2);
            return filmeMapper.map(filmeRepository.save(filmeModelAtualizado));
        } return null;
    }

}
