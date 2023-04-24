package it.montblanc0.repos;

import it.montblanc0.entities.Motore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MotoreRepository extends JpaRepository<Motore, Long>, JpaSpecificationExecutor<Motore> {

}