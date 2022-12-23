package com.hms.spring.service;

import java.util.List;
import java.util.Optional;

import com.hms.spring.entity.Patient;
import com.hms.spring.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class patientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;
	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public void savePatient(Patient patient) {
		this.patientRepository.save(patient);
	}

	@Override
	public Patient getPatientById(int patientId) {
		Optional<Patient> optional = patientRepository.findById(patientId);
		Patient patient = null;
		if (optional.isPresent()) {
			patient = optional.get();
		} else {
			throw new RuntimeException(" Patient not found for id :: " + patientId);
		}
		return patient;
	}

	@Override
	public void deletePatientById(int patientId) {
		this.patientRepository.deleteById(patientId);
	}

	@Override
	public Page<Patient> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.patientRepository.findAll(pageable);
	}
}
