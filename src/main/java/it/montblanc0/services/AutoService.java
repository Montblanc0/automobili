package it.montblanc0.services;

import it.montblanc0.entities.Alimentazione;
import it.montblanc0.entities.Auto;
import it.montblanc0.repos.AutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class AutoService {

	@Autowired
	private AutoRepository autoRepository;

	@Transactional
	public Long save(Auto auto) {
		return autoRepository.save(auto).getId();
	}

	@Transactional
	public void delete(Long id) {
		autoRepository.deleteById(id);
	}

	@Transactional
	public void update(Long id, Auto auto) {
		Auto bean = requireOne(id);
		BeanUtils.copyProperties(auto, bean);
		autoRepository.save(bean);
	}

	public Auto getById(Long id) {
		return requireOne(id);
	}

	public List<Auto> findAll() {
		return autoRepository.findAll();
	}

	private Auto requireOne(Long id) {
		return autoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
	}

	public List<Auto> findByMarca(String marca) {
		marca = "%" + marca + "%";
		return autoRepository.findByMarcaLikeOrderByMarca(marca);
	}

	public List<Auto> findByAlimentazione(Alimentazione ali) {
		return autoRepository.findByAlimentazioneOrderByMarca(ali);
	}

	public List<Auto> findByCilindrata(Integer start, Integer end) {
		return autoRepository.findByCilindrataBetween(start, end);
	}
}
