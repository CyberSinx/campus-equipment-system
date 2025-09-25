package edu.cit.miel.kaysean.campusequipmentloan.service;

import edu.cit.miel.kaysean.campusequipmentloan.model.Equipment;
import edu.cit.miel.kaysean.campusequipmentloan.model.Student;
import edu.cit.miel.kaysean.campusequipmentloan.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final StudentRepository studentRepo;
    private final EquipmentRepository equipmentRepo;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(StudentRepository studentRepo,
                      EquipmentRepository equipmentRepo,
                      UserRepository userRepo,
                      PasswordEncoder passwordEncoder) {
        this.studentRepo = studentRepo;
        this.equipmentRepo = equipmentRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Add sample Student (only if none exist)
        if (studentRepo.count() == 0) {
            Student s1 = new Student();
            s1.setName("Juan Dela Cruz");
            s1.setStudentNo("2025-001");
            s1.setEmail("juan@cit.edu");
            studentRepo.save(s1);
        }

        // Add sample Equipment (only if none exist)
        if (equipmentRepo.count() == 0) {
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

        // Add default Student User
        if (userRepo.findByUsername("student1").isEmpty()) {
            User u1 = new User();
            u1.setUsername("student1");
            u1.setPassword(passwordEncoder.encode("12345")); // default password
            u1.setRole("STUDENT"); // ✅ plain role
            userRepo.save(u1);
            System.out.println("✅ Default student created: student1 / 12345");
        }

        // Add default Admin User
        if (userRepo.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // default password
            admin.setRole("ADMIN"); // ✅ plain role
            userRepo.save(admin);
            System.out.println("✅ Default admin created: admin / admin123");
        }
    }
}
