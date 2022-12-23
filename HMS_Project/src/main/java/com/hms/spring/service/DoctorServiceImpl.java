package com.hms.spring.service;

import java.util.List;
import java.util.Optional;

import com.hms.spring.entity.Doctor;
import com.hms.spring.repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();

	}

	@Override
	public void saveDoctor(Doctor doctor) {
		this.doctorRepository.save(doctor);
		
	}

	@Override
	public Doctor getDoctorById(int doctorId) {
		Optional<Doctor> optional = doctorRepository.findById(doctorId);
		Doctor doctor = null;
		if (optional.isPresent()) {
			doctor = optional.get();
		} else {
			throw new RuntimeException(" Doctor not found for id :: " + doctorId);
		}
		return doctor;	}

	@Override
	public void deleteDoctorById(int DoctorId) {
		this.doctorRepository.deleteById(DoctorId);
		
	}

	@Override
	public Page<Doctor> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.doctorRepository.findAll(pageable);
	
	}
}
	
	

	