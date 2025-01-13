package com.anemona.aneback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anemona.aneback.model.Paciente;
import com.anemona.aneback.repository.PacienteRepository;

@Service
public class PacienteServiceImpl implements PacienteService{
    
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Paciente createPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente getPacienteById(Long pacienteId) {
        return pacienteRepository.findById(pacienteId).orElseThrow(() -> new RuntimeException("No se encuentra el paciente indicado"));
    }

    @Override
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public void deletePacienteById(Long pacienteId) {
        if (!pacienteRepository.existsById(pacienteId)) { 
            throw new RuntimeException("Paciente no econtrado");
        }
        pacienteRepository.deleteById(pacienteId);;
    }

    @Override
    public Paciente updatePacienteById(Long pacienteId, Paciente updatedPaciente) {
        Paciente paciente = pacienteRepository.findById(pacienteId).orElseThrow(() -> new RuntimeException("No se encuentra el paciente a actualizar"));

        paciente.setNombre_paciente(updatedPaciente.getNombre_paciente());
        paciente.setApellido_paciente(updatedPaciente.getApellido_paciente());
        paciente.setRut_paciente(updatedPaciente.getRut_paciente());
        paciente.setTelefono_paciente(updatedPaciente.getTelefono_paciente());

        return pacienteRepository.save(paciente);
    }

    @Override
    public void deleteAllPacientes() {
        pacienteRepository.deleteAll();
    }

}
