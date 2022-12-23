package com.hms.spring.controller;

import java.util.List;

import com.hms.spring.entity.Patient;
import com.hms.spring.service.PatientService;

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
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	
	
	@GetMapping("/patient")
	public String viewHomePage(Model model) {
		return findPaginated(1, "patientName", "asc", model);		
	}
	
	@GetMapping("/showNewPatientForm")
	public String showNewPatientForm(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "new_patient";
	}
	
	@PostMapping("/savePatient")
	public String savePatient(@ModelAttribute("patient") Patient patient) {
		
		patientService.savePatient(patient);
		return "redirect:/patient";
	}
	
	@GetMapping("/showFormForPatientUpdate/{patientId}")
	public String showFormForUpdate(@PathVariable ( value = "patientId") int patientId, Model model) {
		
		Patient patient = patientService.getPatientById(patientId);
		
		model.addAttribute("patient", patient);
		return "update_patient";
	}
	
	@GetMapping("/deletePatient/{patientId}")
	public String deletePatient(@PathVariable (value = "patientId") int patientId) {
		
		this.patientService.deletePatientById(patientId);
		return "redirect:/patient";
	}
	
	@GetMapping("/pages/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 10;
		
		Page<Patient> page = patientService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Patient> listPatients = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPatients", listPatients);
		return "Patients";
	}
}
	
