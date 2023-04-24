package it.montblanc0.repos;

import it.montblanc0.entities.Alimentazione;
import it.montblanc0.entities.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AutoRepository extends JpaRepository<Auto, Long>, JpaSpecificationExecutor<Auto> {

	List<Auto> findByMarcaLikeOrderByMarca(String marca);

	List<Auto> findByAlimentazioneOrderByMarca(Alimentazione ali);

	List<Auto> findByCilindrataBetween(Integer start, Integer end);
}