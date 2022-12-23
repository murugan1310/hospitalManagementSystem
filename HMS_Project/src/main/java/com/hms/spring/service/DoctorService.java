package com.hms.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hms.spring.entity.Doctor;

public interface DoctorService {
	List<Doctor> getAllDoctors();
	void saveDoctor(Doctor doctor);
	Doctor getDoctorById(int doctorId);
	void deleteDoctorById(int doctorId);
	Page<Doctor> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
}
