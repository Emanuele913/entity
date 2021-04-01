package response.entity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import response.entity.model.Ruolo;
import response.entity.repository.RuoloRepository;

import java.util.List;

@Component
public class RuoloService {

    private final RuoloRepository ruoloRepository;

    @Autowired
    private RuoloService(RuoloRepository ruoloRepository){
        this.ruoloRepository = ruoloRepository;
    }

    public List<Ruolo> getAllRuoli(){
        return ruoloRepository.findAll();
    }
}
