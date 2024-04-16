package tn.esprit.pidev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.entities.Reclamtion;

import java.util.List;

@Repository
public interface IReclamationRepository extends JpaRepository<Reclamtion,Long> {

    @Query("select r from Reclamtion r join r.responses res where r.user.id = :id and res.idrep = :idrep")
    public Reclamtion getReclamationByUserAndResponse(@Param("id") Long id, @Param("idrep") long idrep);

    @Query("select r from Reclamtion r join fetch r.user join fetch r.responses")
    public List<Reclamtion> findAllReclamationsWithUserAndResponse();

}