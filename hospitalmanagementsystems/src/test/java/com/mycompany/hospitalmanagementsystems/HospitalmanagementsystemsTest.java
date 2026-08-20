package com.mycompany.hospitalmanagementsystems;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;

/**
 * JUnit Test Class for Hospital Management System
 * Tests all major functionality of the system
 */
public class HospitalmanagementsystemsTest {

    private Hospitalmanagementsystems system;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        system = new Hospitalmanagementsystems();
        // Redirect System.out for output testing
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }

    // ===== PATIENT TESTS =====

    @Test
    @DisplayName("Test Patient Creation")
    void testPatientCreation() {
        Hospitalmanagementsystems.Patient patient = system.new Patient(
            "P001", "John", "Doe", 30, "Male", "Flu", 
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        );

        assertNotNull(patient);
        assertEquals("P001", patient.getPatientId());
        assertEquals("John", patient.getFirstName());
        assertEquals("Doe", patient.getLastName());
        assertEquals(30, patient.getAge());
        assertEquals("Male", patient.getGender());
        assertEquals("Flu", patient.getMedicalCondition());
        assertEquals(Hospitalmanagementsystems.PatientCategory.OUTPATIENT, patient.getCategory());
    }

    @Test
    @DisplayName("Test Patient Setters")
    void testPatientSetters() {
        Hospitalmanagementsystems.Patient patient = system.new Patient(
            "P002", "Jane", "Smith", 25, "Female", "Cold",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        );

        patient.setFirstName("Janet");
        patient.setLastName("Doe");
        patient.setAge(26);
        patient.setGender("Female");
        patient.setMedicalCondition("Recovered");
        patient.setCategory(Hospitalmanagementsystems.PatientCategory.INPATIENT);

        assertEquals("Janet", patient.getFirstName());
        assertEquals("Doe", patient.getLastName());
        assertEquals(26, patient.getAge());
        assertEquals("Female", patient.getGender());
        assertEquals("Recovered", patient.getMedicalCondition());
        assertEquals(Hospitalmanagementsystems.PatientCategory.INPATIENT, patient.getCategory());
    }

    @Test
    @DisplayName("Test Patient Display Details")
    void testPatientDisplayDetails() {
        Hospitalmanagementsystems.Patient patient = system.new Patient(
            "P003", "Alice", "Brown", 40, "Female", "Headache",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        );

        patient.displayDetails();
        String output = outContent.toString();
        assertTrue(output.contains("Patient ID: P003"));
        assertTrue(output.contains("Name: Alice Brown"));
        assertTrue(output.contains("Age: 40"));
        assertTrue(output.contains("Gender: Female"));
        assertTrue(output.contains("Medical Condition: Headache"));
        assertTrue(output.contains("Category: OUTPATIENT"));
    }

    // ===== INPATIENT TESTS =====

    @Test
    @DisplayName("Test Inpatient Creation")
    void testInpatientCreation() {
        Hospitalmanagementsystems.Inpatient inpatient = system.new Inpatient(
            "P004", "Bob", "Johnson", 55, "Male", "Heart Disease",
            1, "B05"
        );

        assertNotNull(inpatient);
        assertEquals("P004", inpatient.getPatientId());
        assertEquals("Bob", inpatient.getFirstName());
        assertEquals("Johnson", inpatient.getLastName());
        assertEquals(55, inpatient.getAge());
        assertEquals("Male", inpatient.getGender());
        assertEquals("Heart Disease", inpatient.getMedicalCondition());
        assertEquals(Hospitalmanagementsystems.PatientCategory.INPATIENT, inpatient.getCategory());
        assertEquals(1, inpatient.getWardNumber());
        assertEquals("B05", inpatient.getBedNumber());
    }

    @Test
    @DisplayName("Test Inpatient Setters")
    void testInpatientSetters() {
        Hospitalmanagementsystems.Inpatient inpatient = system.new Inpatient(
            "P005", "Charlie", "White", 60, "Male", "Diabetes",
            2, "B10"
        );

        inpatient.setWardNumber(1);
        inpatient.setBedNumber("B03");

        assertEquals(1, inpatient.getWardNumber());
        assertEquals("B03", inpatient.getBedNumber());
    }

    @Test
    @DisplayName("Test Inpatient Display Details")
    void testInpatientDisplayDetails() {
        Hospitalmanagementsystems.Inpatient inpatient = system.new Inpatient(
            "P006", "Diana", "Green", 35, "Female", "Surgery",
            1, "B07"
        );

        inpatient.displayDetails();
        String output = outContent.toString();
        assertTrue(output.contains("Patient ID: P006"));
        assertTrue(output.contains("Name: Diana Green"));
        assertTrue(output.contains("Ward Number: 1"));
        assertTrue(output.contains("Bed Number: B07"));
    }

    // ===== HOSPITAL WARD TESTS =====

    @Test
    @DisplayName("Test Hospital Ward Initialization")
    void testHospitalWardInitialization() {
        Hospitalmanagementsystems.HospitalWard ward = system.new HospitalWard();

        assertNotNull(ward);
        assertEquals(4, Hospitalmanagementsystems.HospitalWard.ROWS);
        assertEquals(5, Hospitalmanagementsystems.HospitalWard.COLS);
        assertEquals(20, Hospitalmanagementsystems.HospitalWard.TOTAL_BEDS);
        assertFalse(ward.isFull());
        assertEquals(0, ward.getOccupiedBedsCount());
        assertEquals(0.0, ward.getOccupancyPercentage());
    }

    @Test
    @DisplayName("Test Bed Number Generation")
    void testBedNumberGeneration() {
        Hospitalmanagementsystems.HospitalWard ward = system.new HospitalWard();

        // Test first bed
        assertEquals("B01", ward.getBedNumber(0, 0));
        // Test last bed
        assertEquals("B20", ward.getBedNumber(3, 4));
        // Test middle bed
        assertEquals("B08", ward.getBedNumber(1, 2));
    }

    @Test
    @DisplayName("Test Bed Allocation")
    void testBedAllocation() {
        Hospitalmanagementsystems.HospitalWard ward = system.new HospitalWard();

        // Allocate first bed
        assertTrue(ward.allocateBed("P001"));
        assertEquals(1, ward.getOccupiedBedsCount());
        assertEquals("B01", ward.findBedByPatient("P001"));
        assertTrue(ward.isBedOccupied("P001"));

        // Allocate second bed
        assertTrue(ward.allocateBed("P002"));
        assertEquals(2, ward.getOccupiedBedsCount());
        assertEquals("B02", ward.findBedByPatient("P002"));

        // Try to allocate to same patient again
        assertFalse(ward.allocateBed("P001"));
        assertEquals(2, ward.getOccupiedBedsCount());
    }

    @Test
    @DisplayName("Test Bed Release")
    void testBedRelease() {
        Hospitalmanagementsystems.HospitalWard ward = system.new HospitalWard();

        ward.allocateBed("P001");
        ward.allocateBed("P002");

        // Release first patient
        assertTrue(ward.releaseBed("P001"));
        assertEquals(1, ward.getOccupiedBedsCount());
        assertNull(ward.findBedByPatient("P001"));

        // Try to release non-existent patient
        assertFalse(ward.releaseBed("P999"));
        assertEquals(1, ward.getOccupiedBedsCount());
    }

    @Test
    @DisplayName("Test Ward Full")
    void testWardFull() {
        Hospitalmanagementsystems.HospitalWard ward = system.new HospitalWard();

        // Fill all 20 beds
        for (int i = 1; i <= 20; i++) {
            String patientId = String.format("P%03d", i);
            assertTrue(ward.allocateBed(patientId));
        }

        assertTrue(ward.isFull());
        assertEquals(20, ward.getOccupiedBedsCount());
        assertEquals(100.0, ward.getOccupancyPercentage());

        // Try to allocate one more
        assertFalse(ward.allocateBed("P021"));
    }

    @Test
    @DisplayName("Test Find Bed by Patient")
    void testFindBedByPatient() {
        Hospitalmanagementsystems.HospitalWard ward = system.new HospitalWard();

        ward.allocateBed("P001");
        ward.allocateBed("P002");

        assertEquals("B01", ward.findBedByPatient("P001"));
        assertEquals("B02", ward.findBedByPatient("P002"));
        assertNull(ward.findBedByPatient("P999"));
    }

    @Test
    @DisplayName("Test Get Bed Number for Patient")
    void testGetBedNumberForPatient() {
        Hospitalmanagementsystems.HospitalWard ward = system.new HospitalWard();

        ward.allocateBed("P001");

        assertEquals("B01", ward.getBedNumberForPatient("P001"));
        assertNull(ward.getBedNumberForPatient("P002"));
    }

    // ===== PATIENT REGISTRATION TESTS =====

    @Test
    @DisplayName("Test Register Outpatient")
    void testRegisterOutpatient() {
        // Simulate user input
        String input = "P001\nJohn\nDoe\n30\nMale\nFlu\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        system.registerPatient();

        assertEquals(1, system.patients.size());
        Hospitalmanagementsystems.Patient patient = system.patients.get(0);
        assertEquals("P001", patient.getPatientId());
        assertEquals("John", patient.getFirstName());
        assertEquals("Doe", patient.getLastName());
        assertEquals(30, patient.getAge());
        assertEquals("Male", patient.getGender());
        assertEquals("Flu", patient.getMedicalCondition());
        assertEquals(Hospitalmanagementsystems.PatientCategory.OUTPATIENT, patient.getCategory());
    }

    @Test
    @DisplayName("Test Register Inpatient")
    void testRegisterInpatient() {
        String input = "P002\nJane\nSmith\n25\nFemale\nSurgery\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        system.registerPatient();

        assertEquals(1, system.patients.size());
        Hospitalmanagementsystems.Patient patient = system.patients.get(0);
        assertEquals("P002", patient.getPatientId());
        assertEquals(Hospitalmanagementsystems.PatientCategory.INPATIENT, patient.getCategory());
        assertEquals(1, system.ward.getOccupiedBedsCount());
        assertNotNull(system.ward.findBedByPatient("P002"));
    }

    @Test
    @DisplayName("Test Register Emergency Patient")
    void testRegisterEmergencyPatient() {
        String input = "P003\nAlice\nBrown\n40\nFemale\nHeart Attack\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        system.registerPatient();

        assertEquals(1, system.patients.size());
        Hospitalmanagementsystems.Patient patient = system.patients.get(0);
        assertEquals("P003", patient.getPatientId());
        assertEquals(Hospitalmanagementsystems.PatientCategory.EMERGENCY, patient.getCategory());
    }

    @Test
    @DisplayName("Test Register Duplicate Patient ID")
    void testRegisterDuplicatePatientId() {
        // Register first patient
        String input1 = "P001\nJohn\nDoe\n30\nMale\nFlu\n2\n";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        system.registerPatient();

        // Try to register duplicate
        String input2 = "P001\nJane\nSmith\n25\nFemale\nCold\n2\n";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        system.registerPatient();

        // Should still only have 1 patient
        assertEquals(1, system.patients.size());
        String output = outContent.toString();
        assertTrue(output.contains("Patient ID already exists"));
    }

    @Test
    @DisplayName("Test Register Inpatient When Ward Full")
    void testRegisterInpatientWhenWardFull() {
        // Fill the ward
        for (int i = 1; i <= 20; i++) {
            system.ward.allocateBed(String.format("P%03d", i));
        }

        // Try to register another inpatient
        String input = "P999\nTest\nUser\n30\nMale\nSurgery\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        system.registerPatient();

        // Should not add patient
        assertEquals(0, system.patients.size());
        String output = outContent.toString();
        assertTrue(output.contains("No beds available"));
    }

    // ===== PATIENT SEARCH TESTS =====

    @Test
    @DisplayName("Test Find Patient By ID")
    void testFindPatientById() {
        // Add a patient
        Hospitalmanagementsystems.Patient patient = system.new Patient(
            "P001", "John", "Doe", 30, "Male", "Flu",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        );
        system.patients.add(patient);

        // Find existing patient
        Hospitalmanagementsystems.Patient found = system.findPatientById("P001");
        assertNotNull(found);
        assertEquals("P001", found.getPatientId());

        // Find non-existing patient
        assertNull(system.findPatientById("P999"));
    }

    @Test
    @DisplayName("Test Search Patient")
    void testSearchPatient() {
        // Add a patient
        Hospitalmanagementsystems.Patient patient = system.new Patient(
            "P001", "John", "Doe", 30, "Male", "Flu",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        );
        system.patients.add(patient);

        // Search for patient
        String input = "P001\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        system.searchPatient();

        String output = outContent.toString();
        assertTrue(output.contains("Patient Found"));
        assertTrue(output.contains("Patient ID: P001"));
        assertTrue(output.contains("Name: John Doe"));
    }

    @Test
    @DisplayName("Test Search Patient Not Found")
    void testSearchPatientNotFound() {
        String input = "P999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        system.searchPatient();

        String output = outContent.toString();
        assertTrue(output.contains("Patient not found"));
    }

    // ===== PATIENT UPDATE TESTS =====

    @Test
    @DisplayName("Test Update Patient Details")
    void testUpdatePatient() {
        // Add a patient
        Hospitalmanagementsystems.Patient patient = system.new Patient(
            "P001", "John", "Doe", 30, "Male", "Flu",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        );
        system.patients.add(patient);

        // Update patient
        String input = "P001\n\n\n35\n\nRecovered\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        system.updatePatient();

        assertEquals("John", patient.getFirstName());
        assertEquals("Doe", patient.getLastName());
        assertEquals(35, patient.getAge());
        assertEquals("Male", patient.getGender());
        assertEquals("Recovered", patient.getMedicalCondition());
    }

    @Test
    @DisplayName("Test Update Patient Not Found")
    void testUpdatePatientNotFound() {
        String input = "P999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        system.updatePatient();

        String output = outContent.toString();
        assertTrue(output.contains("Patient not found"));
    }

    // ===== PATIENT DELETION TESTS =====

    @Test
    @DisplayName("Test Delete Patient")
    void testDeletePatient() {
        // Add an inpatient
        Hospitalmanagementsystems.Patient patient = system.new Inpatient(
            "P001", "John", "Doe", 30, "Male", "Surgery",
            1, "B05"
        );
        system.patients.add(patient);
        system.ward.allocateBed("P001");

        // Delete patient
        String input = "P001\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        system.deletePatient();

        assertEquals(0, system.patients.size());
        assertNull(system.ward.findBedByPatient("P001"));
    }

    @Test
    @DisplayName("Test Delete Patient Not Found")
    void testDeletePatientNotFound() {
        String input = "P999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        system.deletePatient();

        String output = outContent.toString();
        assertTrue(output.contains("Patient not found"));
    }

    // ===== DISPLAY TESTS =====

    @Test
    @DisplayName("Test Display All Patients")
    void testDisplayAllPatients() {
        // Add patients
        system.patients.add(system.new Patient(
            "P001", "John", "Doe", 30, "Male", "Flu",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        ));
        system.patients.add(system.new Patient(
            "P002", "Jane", "Smith", 25, "Female", "Cold",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        ));

        system.displayAllPatients();

        String output = outContent.toString();
        assertTrue(output.contains("P001"));
        assertTrue(output.contains("John Doe"));
        assertTrue(output.contains("P002"));
        assertTrue(output.contains("Jane Smith"));
        assertTrue(output.contains("Total: 2 patients"));
    }

    @Test
    @DisplayName("Test Display Total Patients")
    void testDisplayTotalPatients() {
        system.patients.add(system.new Patient(
            "P001", "John", "Doe", 30, "Male", "Flu",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        ));

        system.displayTotalPatients();

        String output = outContent.toString();
        assertTrue(output.contains("Total Registered Patients: 1"));
    }

    // ===== SORTING TESTS =====

    @Test
    @DisplayName("Test Sort Patients By Surname")
    void testSortPatientsBySurname() {
        // Add patients in unsorted order
        system.patients.add(system.new Patient(
            "P003", "Alice", "Zebra", 30, "Male", "Flu",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        ));
        system.patients.add(system.new Patient(
            "P001", "John", "Apple", 25, "Female", "Cold",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        ));
        system.patients.add(system.new Patient(
            "P002", "Jane", "Banana", 35, "Female", "Cold",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        ));

        system.sortPatientsBySurname();

        assertEquals("Apple", system.patients.get(0).getLastName());
        assertEquals("Banana", system.patients.get(1).getLastName());
        assertEquals("Zebra", system.patients.get(2).getLastName());
    }

    @Test
    @DisplayName("Test Sort Patients By ID")
    void testSortPatientsById() {
        // Add patients in unsorted order
        system.patients.add(system.new Patient(
            "P003", "Alice", "Zebra", 30, "Male", "Flu",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        ));
        system.patients.add(system.new Patient(
            "P001", "John", "Apple", 25, "Female", "Cold",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        ));
        system.patients.add(system.new Patient(
            "P002", "Jane", "Banana", 35, "Female", "Cold",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        ));

        system.sortPatientsById();

        assertEquals("P001", system.patients.get(0).getPatientId());
        assertEquals("P002", system.patients.get(1).getPatientId());
        assertEquals("P003", system.patients.get(2).getPatientId());
    }

    // ===== READ INTEGER TESTS =====

    @Test
    @DisplayName("Test Read Integer Valid Input")
    void testReadIntegerValid() {
        String input = "42\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        int result = system.readInteger("Enter number: ");
        assertEquals(42, result);
    }

    @Test
    @DisplayName("Test Read Integer Invalid Then Valid Input")
    void testReadIntegerInvalidThenValid() {
        String input = "abc\n42\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        int result = system.readInteger("Enter number: ");
        assertEquals(42, result);

        String output = outContent.toString();
        assertTrue(output.contains("Invalid input"));
    }

    // ===== MAIN METHOD TEST =====

    @Test
    @DisplayName("Test Main Method")
    void testMainMethod() {
        // This test just ensures the main method runs without error
        // We can't easily test the full interactive flow, but we can test that main exists
        assertDoesNotThrow(() -> {
            // The main method would normally run interactively
            // We'll just test that the class is properly set up
            Hospitalmanagementsystems system = new Hospitalmanagementsystems();
            assertNotNull(system);
        });
    }

    // ===== EDGE CASE TESTS =====

    @Test
    @DisplayName("Test Patient with Empty Fields")
    void testPatientWithEmptyFields() {
        Hospitalmanagementsystems.Patient patient = system.new Patient(
            "", "", "", 0, "", "",
            Hospitalmanagementsystems.PatientCategory.OUTPATIENT
        );

        assertNotNull(patient);
        assertEquals("", patient.getPatientId());
        assertEquals("", patient.getFirstName());
        assertEquals("", patient.getLastName());
        assertEquals(0, patient.getAge());
        assertEquals("", patient.getGender());
        assertEquals("", patient.getMedicalCondition());
    }

    @Test
    @DisplayName("Test Invalid Age Registration")
    void testInvalidAgeRegistration() {
        String input = "P001\nJohn\nDoe\n150\nMale\nFlu\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        system.registerPatient();

        // Should not add patient with invalid age
        assertEquals(0, system.patients.size());
        String output = outContent.toString();
        assertTrue(output.contains("Invalid age"));
    }

    @Test
    @DisplayName("Test Empty Patient ID Registration")
    void testEmptyPatientIdRegistration() {
        String input = "\nJohn\nDoe\n30\nMale\nFlu\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        system.registerPatient();

        assertEquals(0, system.patients.size());
        String output = outContent.toString();
        assertTrue(output.contains("Patient ID cannot be empty"));
    }

    @Test
    @DisplayName("Test Ward Layout Display")
    void testWardLayoutDisplay() {
        system.ward.allocateBed("P001");
        system.ward.allocateBed("P002");
        system.ward.displayWardLayout();

        String output = outContent.toString();
        assertTrue(output.contains("WARD LAYOUT"));
        assertTrue(output.contains("B01"));
        assertTrue(output.contains("B02"));
        assertTrue(output.contains("EMPTY"));
    }

    @Test
    @DisplayName("Test Available Beds Display")
    void testAvailableBedsDisplay() {
        system.ward.allocateBed("P001");
        system.ward.displayAvailableBeds();

        String output = outContent.toString();
        assertTrue(output.contains("AVAILABLE BEDS"));
        assertTrue(output.contains("B02"));
        assertTrue(output.contains("B03"));
    }

    @Test
    @DisplayName("Test Occupied Beds Display")
    void testOccupiedBedsDisplay() {
        system.ward.allocateBed("P001");
        system.ward.allocateBed("P002");
        system.ward.displayOccupiedBeds();

        String output = outContent.toString();
        assertTrue(output.contains("OCCUPIED BEDS"));
        assertTrue(output.contains("B01: P001"));
        assertTrue(output.contains("B02: P002"));
    }

    @Test
    @DisplayName("Test Occupied Beds Count Display")
    void testOccupiedBedsCountDisplay() {
        system.ward.allocateBed("P001");
        system.ward.allocateBed("P002");
        system.displayOccupiedBedsCount();

        String output = outContent.toString();
        assertTrue(output.contains("Total Occupied Beds: 2"));
    }

    @Test
    @DisplayName("Test Occupancy Percentage Display")
    void testOccupancyPercentageDisplay() {
        system.ward.allocateBed("P001");
        system.ward.allocateBed("P002");
        system.displayOccupancyPercentage();

        String output = outContent.toString();
        assertTrue(output.contains("Ward Occupancy Percentage: 10.0%"));
    }
}
