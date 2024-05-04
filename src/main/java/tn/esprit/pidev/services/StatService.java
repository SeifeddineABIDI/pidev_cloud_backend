package tn.esprit.pidev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.repository.IRessourceRepository;

import java.util.Date;
import java.util.List;

@Service
public class StatService {

    @Autowired
    private IRessourceRepository ressourceRepository;



    public Long countRessourcesAddedByWeek(Date startOfWeek, Date endOfWeek) {
        return ressourceRepository.countRessourcesAddedByWeek(startOfWeek, endOfWeek);
    }

    public List<Object[]> countRessourcesByOption() {
        return ressourceRepository.countRessourcesByOption();
    }
}
