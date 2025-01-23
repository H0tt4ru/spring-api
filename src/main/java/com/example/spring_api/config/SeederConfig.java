package com.example.spring_api.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.base_domain.dto.constant.Gender;
import com.example.base_domain.dto.constant.Major;
import com.example.base_domain.dto.constant.Role;
import com.example.base_domain.dto.model.History;
import com.example.base_domain.dto.model.Student;
import com.example.base_domain.dto.model.User;
import com.example.base_domain.dto.model.Wallet;
import com.example.base_domain.dto.model.Error;
import com.example.base_domain.repository.ErrorRepository;
import com.example.base_domain.repository.HistoryRepository;
import com.example.base_domain.repository.StudentRepository;
import com.example.base_domain.repository.UserRepository;
import com.example.base_domain.repository.WalletRepository;

@Configuration
public class SeederConfig {

        @Bean
        CommandLineRunner commandLineRunner(UserRepository userRepository, StudentRepository studentRepository,
                        HistoryRepository historyRepository, ErrorRepository errorRepository,
                        WalletRepository walletRepository,
                        PasswordEncoder passwordEncoder) {
                return _ -> {
                        if (errorRepository.count() == 0) {
                                errorRepository.saveAll(List.of(
                                                new Error("4101", "Broken request fields"),
                                                new Error("4102", "Empty fields"),
                                                new Error("4103", "Email already exists"),
                                                new Error("4104",
                                                                "Password must have at least 8 characters, 1 uppercase, 1 lowercase, and 1 number"),
                                                new Error("4105", "Full name be no more than 100 characters"),
                                                new Error("4106", "Gender must be either Male or Female"),
                                                new Error("4107", "Invalid date of birth format"),
                                                new Error("4108", "Major must be one of the provided majors"),
                                                new Error("4109",
                                                                "Phone number must be numbers only and minimum 7 digits"),
                                                new Error("4110", "Username must be no more than 20 characters"),
                                                new Error("4200", "Empty fields"),
                                                new Error("4201", "Wrong email or password"),
                                                new Error("4300", "Empty database"),
                                                new Error("4301", "Student not found"),
                                                new Error("4302", "Gender must be either Male or Female"),
                                                new Error("4303", "Invalid date of birth format"),
                                                new Error("4304", "Invalid major"),
                                                new Error("4400", "Empty fields"),
                                                new Error("4401", "Destination student not found"),
                                                new Error("4402", "Amount does not follow the rule"),
                                                new Error("4403", "Not enough points"),
                                                new Error("4500",
                                                                "Invalid type (must be one of: SentBy, ReceivedBy, All, or SentByReceivedBy)"),
                                                new Error("4501", "SentBy student not found"),
                                                new Error("4502", "ReceivedBy student not found"),
                                                new Error("4600", "JWT token is missing"),
                                                new Error("4601", "Invalid JWT token"),
                                                new Error("4602", "Expired JWT token"),
                                                new Error("4603", "Insufficient permissions"),
                                                new Error("4604", "Account locked"),
                                                new Error("4700", "Invalid email format"),
                                                new Error("4701", "Invalid password format"),
                                                new Error("4702", "Invalid phone number format"),
                                                new Error("4703", "Invalid date of birth format"),
                                                new Error("4800", "Duplicate key violation"),
                                                new Error("4801", "Foreign key violation"),
                                                new Error("4802", "Transaction failed"),
                                                new Error("4900", "External API timeout"),
                                                new Error("4901", "Invalid response from external service"),
                                                new Error("4902", "Unauthorized request to external service"),
                                                new Error("4903", "Inconsistent state"),
                                                new Error("4904", "Missing required field"),
                                                new Error("5100", "Rate limit exceeded – Too many requests"),
                                                new Error("5101", "API rate limit exceeded"),
                                                new Error("5200", "Feature not yet implemented"),
                                                new Error("5201", "Unknown error occurred"),
                                                new Error("5000", "Internal server error")));
                        }
                        if (userRepository.count() == 0) {
                                User admin = User.builder()
                                                .username("admin")
                                                .email("admin@admin.com")
                                                .password(passwordEncoder.encode("passwordAdmin1"))
                                                .role(Role.ADMIN)
                                                .studentNim(null)
                                                .build();

                                User student1 = User.builder()
                                                .username("Student1")
                                                .email("student1@gmail.com")
                                                .password(passwordEncoder.encode("passwordStudent1"))
                                                .role(Role.USER)
                                                .studentNim("000001")
                                                .build();

                                User student2 = User.builder()
                                                .username("Student2")
                                                .email("student2@gmail.com")
                                                .password(passwordEncoder.encode("passwordStudent2")).role(Role.USER)
                                                .studentNim("000002")
                                                .build();

                                User student3 = User.builder().username("Student3").email("Student3@gmail.com")
                                                .password(passwordEncoder.encode("passwordStudent3")).role(Role.USER)
                                                .studentNim("000003").build();

                                Student student1Data = new Student("000001", "Student 1", Gender.Female,
                                                LocalDate.of(2000, 1, 1),
                                                Major.Science,
                                                "081234567890", "Jl. Jalan No. 1");

                                Student student2Data = new Student("000002", "Student 2", Gender.Male,
                                                LocalDate.of(1999, 2, 23), Major.Accounting, "0913748265",
                                                "Jl. jln jln no 3");

                                Student student3Data = new Student("000003", "Student 3", Gender.Female,
                                                LocalDate.of(1878, 9, 20), Major.Arts, "5214789630", "Gothenburg");

                                Wallet student1Wallet = new Wallet("000001", 80);
                                Wallet student2Wallet = new Wallet("000002", 256);
                                Wallet student3Wallet = new Wallet("000003", 125);

                                History history1 = new History("000001", "000002", 50);
                                History history2 = new History("000001", "000002", 75);
                                History history3 = new History("000002", "000001", 100);
                                History history4 = new History("000003", "000001", 25);
                                History history5 = new History("000003", "000002", 65);

                                userRepository.saveAll(List.of(admin, student1, student2, student3));
                                historyRepository.saveAll(List.of(history1, history2, history3, history4, history5));
                                studentRepository.saveAll(List.of(student1Data, student2Data, student3Data));
                                walletRepository.saveAll(List.of(student1Wallet, student2Wallet, student3Wallet));
                        }
                };
        };
}
