package edu.cit.miel.kaysean.campusequipmentloan.service;

import edu.cit.miel.kaysean.campusequipmentloan.model.Equipment;
import edu.cit.miel.kaysean.campusequipmentloan.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final StudentRepository studentRepo;
    private final EquipmentRepository equipmentRepo;

    public DataLoader(StudentRepository studentRepo, EquipmentRepository equipmentRepo) {
        this.studentRepo = studentRepo;
        this.equipmentRepo = equipmentRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Student s1 = new Student();
        s1.setName("Juan Dela Cruz");
        s1.setStudentNo("2025-001");
        s1.setEmail("juan@cit.edu");
        studentRepo.save(s1);

        Equipment e1 = new Equipment();
        e1.setName("Laptop");
        e1.setType("Electronics");
        e1.setSerialNumber("ABC123");
        e1.setAvailable(true);
        equipmentRepo.save(e1);

        Equipment e2 = new Equipment();
        e2.setName("Projector");
        e2.setType("Electronics");
        e2.setSerialNumber("PRJ001");
        e2.setAvailable(true);
        equipmentRepo.save(e2);
    }
}
