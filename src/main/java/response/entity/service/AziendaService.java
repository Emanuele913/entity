package response.entity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import response.entity.model.Azienda;
import response.entity.repository.AziendaRepository;

import java.util.List;

@Component
public class AziendaService {

    private final AziendaRepository aziendaRepository;

    @Autowired
    private AziendaService(AziendaRepository aziendaRepository){
        this.aziendaRepository = aziendaRepository;
    }

    public List<Azienda> getAllAziende(){
        return aziendaRepository.findAll();
    }

    public Azienda findAzienda(String name){
        return aziendaRepository.findAzienda(name);
    }
}
