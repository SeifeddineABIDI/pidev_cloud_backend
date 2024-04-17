package com.picloud.picloud.services.ressources;

import com.picloud.picloud.entities.ressources.VersionRessource;
import com.picloud.picloud.repository.ressources.IVersionRessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionVersionsRess implements IGestionVersionRess {

    @Autowired
    private IVersionRessRepository versionRessourceRepository;

    @Override
    public VersionRessource creerNouvelleVersion(VersionRessource version) {
        return versionRessourceRepository.save(version);
    }

    @Override
    public VersionRessource recupererVersion(Long id) {
        return versionRessourceRepository.findById(id).orElse(null);
    }

    @Override
    public List<VersionRessource> recupererToutesLesVersions() {
        return versionRessourceRepository.findAll();
    }

    @Override
    public void supprimerVersion(Long id) {
        versionRessourceRepository.deleteById(id);
    }


}

