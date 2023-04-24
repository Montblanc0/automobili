package it.montblanc0.controllers;

import it.montblanc0.entities.Alimentazione;
import it.montblanc0.entities.Auto;
import it.montblanc0.entities.Motore;
import it.montblanc0.services.AutoService;
import it.montblanc0.services.MotoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class MainController {

	@Autowired
	private AutoService autoService;

	@Autowired
	private MotoreService motoreService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("auto", new Auto());
		model.addAttribute("motori", motoreService.getAll());
		return "index";
	}

	@GetMapping("/error")
	public String viewError() {
		return "error";
	}

	@GetMapping("/{id}")
	public ModelAndView getById(@PathVariable("id") Long id) {
		Auto auto = autoService.getById(id);
		return new ModelAndView("auto", "auto", auto);
	}

	@GetMapping("/all")
	public String getAll(Model model) {
		model.addAttribute("lista", autoService.findAll());
		return "lista-auto";
	}

	@GetMapping("/marca")
	public String findByMarca(@RequestParam("marca") String marca, Model model) {
		model.addAttribute("lista", autoService.findByMarca(marca));
		return "lista-auto";
	}

	@GetMapping("/ali")
	public String findByAlimentazione(@RequestParam("ali") Alimentazione ali, Model model) {
		model.addAttribute("lista", autoService.findByAlimentazione(ali));
		return "lista-auto";
	}

	@GetMapping("/cc")
	public String findByCilindrata(@RequestParam("start") Integer start, @RequestParam("end") Integer end, Model model) {
		model.addAttribute("lista", autoService.findByCilindrata(start, end));
		return "lista-auto";
	}

	@PostMapping("/new")
	public ModelAndView save(@ModelAttribute("auto") Auto auto, @RequestParam("motore") Long idMotore) {

		Long id = null;
		try {
			auto.setPMotore(motoreService.getById(idMotore));
			id = autoService.save(auto);
		} catch (IllegalArgumentException | NoSuchElementException e) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("redirect:/" + id);
	}


	@GetMapping("/delete/{id}")
	public ModelAndView deleteAuto(@PathVariable("id") Long id) {
		autoService.delete(id);
		return new ModelAndView("redirect:/");
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		Auto auto = null;
		List<Motore> motori = null;
		try {
			auto = autoService.getById(id);
			motori = motoreService.getAll();
		} catch (NoSuchElementException e) {
			return new ModelAndView("error");
		}
		ModelAndView mav = new ModelAndView("edit");
		mav.addObject("auto", auto);
		mav.addObject("motori", motori);
		return mav;
	}

	@PostMapping("/update")
	public ModelAndView updateAuto(@ModelAttribute("auto") Auto auto, @RequestParam("motore") Long idMotore) {
		auto.setPMotore(motoreService.getById(idMotore));
		autoService.update(auto.getId(), auto);
		ModelAndView mav = new ModelAndView("auto");
		mav.addObject("auto", auto);
		return mav;
	}


}
