package com.hms.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hms.spring.entity.Patient;

public interface PatientService {
	
	List<Patient> getAllPatients();
	void savePatient(Patient patient);
	Patient getPatientById(int Patient_Id);
	void deletePatientById(int Patient_Id);
	Page<Patient> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
}
