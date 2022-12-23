package com.hms.spring.controller;

import java.util.List;

import com.hms.spring.entity.Doctor;
import com.hms.spring.service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1,"doctorName","asc",model);
	}
	@GetMapping("/showNewDoctorForm")
	public String showNewDoctorForm(Model model) {
		Doctor doctor = new Doctor();
		model.addAttribute("doctor",doctor);
		return "new_doctor";
	}
	
	@PostMapping("/saveDoctor")
	public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
		doctorService.saveDoctor(doctor);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{doctorId}")
	public String showFormForUpdate(@PathVariable ( value = "doctorId") int doctorId, Model model) {
		
		Doctor doctor = doctorService.getDoctorById(doctorId);
		
		model.addAttribute("doctor", doctor);
		return "update_doctor";
	}
	
	@GetMapping("/deleteDoctor/{doctorId}")
	public String deleteDoctor(@PathVariable (value = "doctorId") int doctorId) {
		
		this.doctorService.deleteDoctorById(doctorId);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 10;
		
		Page<Doctor> page = doctorService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Doctor> listDoctors = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listDoctors", listDoctors);
		return "index";
	}
	

}

