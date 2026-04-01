package com.example.datlichkhambenh.service.impl;

import com.example.datlichkhambenh.dto.AppointmentRequest;
import com.example.datlichkhambenh.entity.*;
import com.example.datlichkhambenh.repository.*;
import com.example.datlichkhambenh.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Appointment createAppointment(Long userId, AppointmentRequest request) {
        User patientUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bác sĩ"));

        Specialty specialty = specialtyRepository.findById(request.getSpecialtyId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên khoa"));

        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch khám"));

        if (!"AVAILABLE".equals(schedule.getStatus())) {
            throw new RuntimeException("Lịch khám không khả dụng");
        }

        if (schedule.getBookedCount() >= schedule.getMaxPatients()) {
            throw new RuntimeException("Khung giờ này đã đầy");
        }

        Appointment appointment = new Appointment();
        appointment.setPatientUser(patientUser);
        appointment.setDoctor(doctor);
        appointment.setSpecialty(specialty);
        appointment.setSchedule(schedule);
        appointment.setAppointmentDate(schedule.getWorkDate());
        appointment.setSymptoms(request.getSymptoms());
        appointment.setNote(request.getNote());
        appointment.setStatus("PENDING");

        schedule.setBookedCount(schedule.getBookedCount() + 1);
        if (schedule.getBookedCount() >= schedule.getMaxPatients()) {
            schedule.setStatus("FULL");
        }
        scheduleRepository.save(schedule);

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByUser(Long userId) {
        User patientUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        return appointmentRepository.findByPatientUser(patientUser);
    }
}