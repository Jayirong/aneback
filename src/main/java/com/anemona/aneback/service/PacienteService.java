package com.anemona.aneback.service;

import java.util.List;

import com.anemona.aneback.model.Paciente;

public interface PacienteService {
    Paciente createPaciente(Paciente paciente);
    Paciente getPacienteById(Long pacienteId);
    List<Paciente> getAllPacientes();
    void deletePacienteById(Long pacienteId);
    Paciente updatePacienteById(Long pacienteId, Paciente updatedPaciente);
    void deleteAllPacientes();
}
