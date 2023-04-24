package it.montblanc0.services;

import it.montblanc0.entities.Motore;
import it.montblanc0.repos.MotoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class MotoreService {

	@Autowired
	private MotoreRepository motoreRepository;

	public List<Motore> getAll() {
		return motoreRepository.findAll();
	}

	public Motore getById(Long id) {
		return requireOne(id);
	}

	private Motore requireOne(Long id) {
		return motoreRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
	}
}
