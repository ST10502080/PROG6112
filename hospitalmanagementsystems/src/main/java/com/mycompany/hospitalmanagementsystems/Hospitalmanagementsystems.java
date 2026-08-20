/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hospitalmanagementsystems;
import java.util.*;
/**
 *
 * @author Az'ulwazi
 */
public class Hospitalmanagementsystems {

   


    // ===== ENUM FOR PATIENT CATEGORY =====
    // An enum is a special type that represents a fixed set of constants
    // Here we define three categories of patients
    enum PatientCategory {
        INPATIENT,    // Patient who stays overnight in the hospital
        OUTPATIENT,   // Patient who visits but doesn't stay overnight
        EMERGENCY     // Patient who needs immediate medical attention
    }

    // ===== PATIENT CLASS =====
    // This class represents a patient in the hospital
    // It stores all the important information about a patient
    class Patient {

        // Public variables - can only be accessed within this class
        public String patientId;
        public String firstName;
        public String lastName;
        public int age;
        public String gender;
        public String medicalCondition;
        public PatientCategory category;

        // Constructor - this is called when we create a new Patient object
        // It initializes all the patient's information
        public Patient(String patientId, String firstName, String lastName,
                       int age, String gender, String medicalCondition,
                       PatientCategory category) {

            this.patientId = patientId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.gender = gender;
            this.medicalCondition = medicalCondition;
            this.category = category;
        }

        // ===== GETTERS =====
        // Getter methods allow other classes to read private variables
        public String getPatientId() {
            return patientId;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getAge() {
            return age;
        }

        public String getGender() {
            return gender;
        }

        public String getMedicalCondition() {
            return medicalCondition;
        }

        public PatientCategory getCategory() {
            return category;
        }

        // ===== SETTERS =====
        // Setter methods allow other classes to modify private variables
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setMedicalCondition(String medicalCondition) {
            this.medicalCondition = medicalCondition;
        }

        public void setCategory(PatientCategory category) {
            this.category = category;
        }

        // ===== DISPLAY PATIENT DETAILS =====
        // This method prints all the patient's information to the screen
        public void displayDetails() {
            System.out.println("Patient ID: " + patientId);
            System.out.println("Name: " + firstName + " " + lastName);
            System.out.println("Age: " + age);
            System.out.println("Gender: " + gender);
            System.out.println("Medical Condition: " + medicalCondition);
            System.out.println("Category: " + category);
        }
    }

    // ===== INPATIENT CLASS =====
    // This class extends the Patient class
    // It adds extra information for patients who stay overnight
    class Inpatient extends Patient {

        public int wardNumber;
        public String bedNumber;

        // Constructor for Inpatient
        // It calls the parent constructor using super()
        public Inpatient(String patientId, String firstName, String lastName,
                         int age, String gender, String medicalCondition,
                         int wardNumber, String bedNumber) {

            // Call the parent constructor with INPATIENT category
            super(patientId, firstName, lastName, age, gender,
                  medicalCondition, PatientCategory.INPATIENT);

            this.wardNumber = wardNumber;
            this.bedNumber = bedNumber;
        }

        // ===== GETTERS =====
        public int getWardNumber() {
            return wardNumber;
        }

        public String getBedNumber() {
            return bedNumber;
        }

        // ===== SETTERS =====
        public void setWardNumber(int wardNumber) {
            this.wardNumber = wardNumber;
        }

        public void setBedNumber(String bedNumber) {
            this.bedNumber = bedNumber;
        }

        // ===== DISPLAY DETAILS =====
        // @Override means we are replacing the method from the parent class
        @Override
        public void displayDetails() {
            // Call the parent's displayDetails method first
            super.displayDetails();
            // Then add the inpatient-specific information
            System.out.println("Ward Number: " + wardNumber);
            System.out.println("Bed Number: " + bedNumber);
        }
    }

    // ===== HOSPITAL WARD CLASS =====
    // This class manages the hospital ward with 20 beds arranged in 4 rows and 5 columns
    class HospitalWard {

        // Constants - these values never change
        public static final int ROWS = 4;        // 4 rows of beds
        public static final int COLS = 5;        // 5 columns of beds
        public static final int TOTAL_BEDS = 20; // Total number of beds

        public String[][] beds;  // 2D array to store which patient is in each bed

        // Constructor - initializes the bed array
        public HospitalWard() {
            beds = new String[ROWS][COLS];
            // All beds start as null (empty)
        }

        // Get bed number based on row and column
        // This creates a bed number like B01, B02, etc.
        public String getBedNumber(int row, int col) {
            int bedNum = row * COLS + col + 1;  // Calculate bed number from 1 to 20
            return String.format("B%02d", bedNum);  // Format as B01, B02, etc.
        }

        // ===== DISPLAY WARD LAYOUT =====
        // Shows all beds and whether they are occupied or empty
        public void displayWardLayout() {

            System.out.println("\n======= WARD LAYOUT =======");

            for (int i = 0; i < ROWS; i++) {

                for (int j = 0; j < COLS; j++) {

                    String bedNum = getBedNumber(i, j);
                    String occupant = beds[i][j];

                    if (occupant == null) {
                        System.out.printf("%s: [EMPTY]\t", bedNum);
                    } else {
                        System.out.printf("%s: [%s]\t", bedNum, occupant);
                    }
                }

                System.out.println(); // Move to next row
            }

            System.out.println("===========================\n");
        }

        // ===== DISPLAY AVAILABLE BEDS =====
        // Shows only the beds that are empty
        public void displayAvailableBeds() {

            System.out.println("\n======= AVAILABLE BEDS =======");

            boolean found = false;  // To check if we found any empty beds

            for (int i = 0; i < ROWS; i++) {

                for (int j = 0; j < COLS; j++) {

                    if (beds[i][j] == null) {  // If bed is empty

                        System.out.print(getBedNumber(i, j) + " ");
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("No available beds.");
            } else {
                System.out.println();
            }

            System.out.println("==============================\n");
        }

        // ===== DISPLAY OCCUPIED BEDS =====
        // Shows only the beds that have patients in them
        public void displayOccupiedBeds() {

            System.out.println("\n======= OCCUPIED BEDS =======");

            boolean found = false;  // To check if we found any occupied beds

            for (int i = 0; i < ROWS; i++) {

                for (int j = 0; j < COLS; j++) {

                    if (beds[i][j] != null) {  // If bed is occupied

                        System.out.println(
                                getBedNumber(i, j) + ": " + beds[i][j]
                        );

                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("No occupied beds.");
            }

            System.out.println("==============================\n");
        }

        // ===== ALLOCATE BED =====
        // Assigns a patient to an empty bed
        public boolean allocateBed(String patientId) {

            // Check if patient already has a bed
            if (findBedByPatient(patientId) != null) {

                System.out.println(
                        "Patient already has a bed allocated."
                );

                return false;
            }

            // Loop through all beds to find an empty one
            for (int i = 0; i < ROWS; i++) {

                for (int j = 0; j < COLS; j++) {

                    if (beds[i][j] == null) {  // Found an empty bed

                        beds[i][j] = patientId;  // Assign patient to bed

                        System.out.println(
                                "Bed " + getBedNumber(i, j)
                                + " allocated to patient " + patientId
                        );

                        return true;  // Allocation successful
                    }
                }
            }

            // If we get here, no empty beds were found
            System.out.println("No beds available!");

            return false;
        }

        // ===== RELEASE BED =====
        // Frees up a bed when a patient leaves
        public boolean releaseBed(String patientId) {

            // Find the bed occupied by this patient
            for (int i = 0; i < ROWS; i++) {

                for (int j = 0; j < COLS; j++) {

                    if (patientId.equals(beds[i][j])) {  // Found the patient

                        beds[i][j] = null;  // Free the bed

                        System.out.println(
                                "Bed " + getBedNumber(i, j)
                                + " released for patient " + patientId
                        );

                        return true;  // Release successful
                    }
                }
            }

            // If we get here, patient was not found
            System.out.println("Patient not found in any bed.");

            return false;
        }

        // ===== FIND PATIENT BED =====
        // Returns the bed number for a given patient
        // Returns null if patient doesn't have a bed
        public String findBedByPatient(String patientId) {

            for (int i = 0; i < ROWS; i++) {

                for (int j = 0; j < COLS; j++) {

                    if (patientId.equals(beds[i][j])) {
                        return getBedNumber(i, j);
                    }
                }
            }

            return null;  // Patient not found
        }

        // ===== OCCUPIED BED COUNT =====
        // Counts how many beds are occupied
        public int getOccupiedBedsCount() {

            int count = 0;

            for (int i = 0; i < ROWS; i++) {

                for (int j = 0; j < COLS; j++) {

                    if (beds[i][j] != null) {
                        count++;
                    }
                }
            }

            return count;
        }

        // ===== OCCUPANCY PERCENTAGE =====
        // Calculates what percentage of beds are occupied
        public double getOccupancyPercentage() {

            return (double) getOccupiedBedsCount()
                    / TOTAL_BEDS * 100;
        }

        // ===== CHECK IF PATIENT HAS A BED =====
        public boolean isBedOccupied(String patientId) {

            return findBedByPatient(patientId) != null;
        }

        // ===== CHECK IF WARD IS FULL =====
        public boolean isFull() {

            return getOccupiedBedsCount() == TOTAL_BEDS;
        }

        // ===== GET BED NUMBER FOR PATIENT =====
        public String getBedNumberForPatient(String patientId) {

            for (int i = 0; i < ROWS; i++) {

                for (int j = 0; j < COLS; j++) {

                    if (patientId.equals(beds[i][j])) {
                        return getBedNumber(i, j);
                    }
                }
            }

            return null;  // Patient not found
        }
    }

    // ===== HOSPITAL MANAGEMENT SYSTEM DATA =====
    // These are the main data structures used by the system

    public ArrayList<Patient> patients;  // List to store all patients
    public HospitalWard ward;            // The hospital ward with beds
    public Scanner scanner;              // For reading user input

    // =====================================================
    // CONSTRUCTOR - Initializes the system
    // =====================================================

    public Hospitalmanagementsystems() {

        patients = new ArrayList<>();  // Create empty patient list
        ward = new HospitalWard();     // Create the hospital ward
        scanner = new Scanner(System.in);  // Create input scanner
    }

    // ===== REGISTER PATIENT =====
    // This method registers a new patient in the system
    public void registerPatient() {

        System.out.println("\n======= REGISTER NEW PATIENT =======");

        // Get patient ID
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine().trim();

        // Check if ID is empty
        if (patientId.isEmpty()) {
            System.out.println("Patient ID cannot be empty!");
            return;
        }

        // Check if ID already exists
        if (findPatientById(patientId) != null) {

            System.out.println("Error: Patient ID already exists!");
            return;
        }

        // Get patient's first name
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine().trim();

        // Get patient's last name
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine().trim();

        // Get patient's age using helper method
        int age = readInteger("Enter Age: ");

        // Validate age
        if (age < 0 || age > 120) {
            System.out.println("Invalid age!");
            return;
        }

        // Get patient's gender
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine().trim();

        // Get patient's medical condition
        System.out.print("Enter Medical Condition: ");
        String condition = scanner.nextLine().trim();

        // Choose patient category
        System.out.println("\nSelect Patient Category:");
        System.out.println("1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");

        int categoryChoice = readInteger("Enter choice (1-3): ");

        Patient newPatient;  // Will hold the new patient object

        // Handle different category choices
        switch (categoryChoice) {

            case 1:  // Inpatient

                // Check if there are any beds available
                if (ward.isFull()) {

                    System.out.println(
                            "Cannot register inpatient: "
                            + "No beds available!"
                    );

                    return;
                }

                // Try to allocate a bed
                if (ward.allocateBed(patientId)) {

                    // Get the bed number that was allocated
                    String bedNumber =
                            ward.getBedNumberForPatient(patientId);

                    // Create new Inpatient object
                    newPatient = new Inpatient(
                            patientId,
                            firstName,
                            lastName,
                            age,
                            gender,
                            condition,
                            1,  // Ward number (always 1 in this system)
                            bedNumber
                    );

                    // Add to patient list
                    patients.add(newPatient);

                    System.out.println(
                            "Inpatient registered successfully!"
                    );

                } else {

                    System.out.println(
                            "Failed to allocate bed. "
                            + "Registration cancelled."
                    );
                }

                break;

            case 2:  // Outpatient

                // Create new Patient object (not Inpatient)
                newPatient = new Patient(
                        patientId,
                        firstName,
                        lastName,
                        age,
                        gender,
                        condition,
                        PatientCategory.OUTPATIENT
                );

                patients.add(newPatient);

                System.out.println(
                        "Outpatient registered successfully!"
                );

                break;

            case 3:  // Emergency

                // Create new Patient object
                newPatient = new Patient(
                        patientId,
                        firstName,
                        lastName,
                        age,
                        gender,
                        condition,
                        PatientCategory.EMERGENCY
                );

                patients.add(newPatient);

                System.out.println(
                        "Emergency patient registered successfully!"
                );

                break;

            default:  // Invalid choice

                System.out.println(
                        "Invalid category choice!"
                );
        }
    }

    // ===== FIND PATIENT =====
    // Searches for a patient by ID
    public Patient findPatientById(String patientId) {

        for (Patient p : patients) {

            if (p.getPatientId().equalsIgnoreCase(patientId)) {
                return p;  // Patient found
            }
        }

        return null;  // Patient not found
    }

    // ===== SEARCH PATIENT =====
    // Allows user to search for a patient by ID
    public void searchPatient() {

        System.out.println("\n======= SEARCH PATIENT =======");

        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine().trim();

        Patient patient = findPatientById(patientId);

        if (patient != null) {

            System.out.println("\nPatient Found:");
            patient.displayDetails();

        } else {

            System.out.println("Patient not found!");
        }
    }

    // ===== UPDATE PATIENT =====
    // Allows user to update patient details
    public void updatePatient() {

        System.out.println("\n======= UPDATE PATIENT =======");

        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine().trim();

        Patient patient = findPatientById(patientId);

        if (patient == null) {

            System.out.println("Patient not found!");
            return;
        }

        // Show current details
        System.out.println("\nCurrent Details:");
        patient.displayDetails();

        System.out.println(
                "\nEnter new details "
                + "(press Enter to keep current value):"
        );

        // Update first name
        System.out.print(
                "First Name (" + patient.getFirstName() + "): "
        );

        String input = scanner.nextLine();

        if (!input.isEmpty()) {
            patient.setFirstName(input);
        }

        // Update last name
        System.out.print(
                "Last Name (" + patient.getLastName() + "): "
        );

        input = scanner.nextLine();

        if (!input.isEmpty()) {
            patient.setLastName(input);
        }

        // Update age
        System.out.print(
                "Age (" + patient.getAge() + "): "
        );

        input = scanner.nextLine();

        if (!input.isEmpty()) {

            try {

                int age = Integer.parseInt(input);

                if (age >= 0 && age <= 120) {
                    patient.setAge(age);
                } else {
                    System.out.println("Invalid age. Age not changed.");
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid age. Age not changed."
                );
            }
        }

        // Update gender
        System.out.print(
                "Gender (" + patient.getGender() + "): "
        );

        input = scanner.nextLine();

        if (!input.isEmpty()) {
            patient.setGender(input);
        }

        // Update medical condition
        System.out.print(
                "Medical Condition ("
                + patient.getMedicalCondition()
                + "): "
        );

        input = scanner.nextLine();

        if (!input.isEmpty()) {
            patient.setMedicalCondition(input);
        }

        System.out.println(
                "Patient details updated successfully!"
        );
    }

    // ===== DELETE PATIENT =====
    // Removes a patient from the system
    public void deletePatient() {

        System.out.println("\n======= DELETE PATIENT =======");

        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine().trim();

        Patient patient = findPatientById(patientId);

        if (patient == null) {

            System.out.println("Patient not found!");
            return;
        }

        // If patient is inpatient, free their bed
        if (patient.getCategory() == PatientCategory.INPATIENT) {

            ward.releaseBed(patientId);
        }

        // Remove patient from list
        patients.remove(patient);

        System.out.println(
                "Patient deleted successfully!"
        );
    }

    // ===== DISPLAY ALL PATIENTS =====
    // Shows all patients in the system
    public void displayAllPatients() {

        System.out.println(
                "\n======= ALL REGISTERED PATIENTS ======="
        );

        if (patients.isEmpty()) {

            System.out.println("No patients registered.");
            return;
        }

        for (Patient p : patients) {

            System.out.println(
                    "----------------------------------------"
            );

            p.displayDetails();

            System.out.println(
                    "----------------------------------------"
            );
        }

        System.out.println(
                "Total: " + patients.size() + " patients"
        );
    }

    // ===== TOTAL PATIENTS =====
    // Displays total number of patients
    public void displayTotalPatients() {

        System.out.println(
                "\nTotal Registered Patients: "
                + patients.size()
        );
    }

    // ===== OCCUPIED BEDS =====
    // Displays number of occupied beds
    public void displayOccupiedBedsCount() {

        System.out.println(
                "\nTotal Occupied Beds: "
                + ward.getOccupiedBedsCount()
        );
    }

    // ===== OCCUPANCY PERCENTAGE =====
    // Displays occupancy percentage
    public void displayOccupancyPercentage() {

        System.out.printf(
                "\nWard Occupancy Percentage: %.1f%%%n",
                ward.getOccupancyPercentage()
        );
    }

    // ===== SORT BY SURNAME =====
    // Sorts patients alphabetically by last name
    public void sortPatientsBySurname() {

        Collections.sort(
                patients,
                new Comparator<Patient>() {

                    @Override
                    public int compare(Patient p1, Patient p2) {

                        return p1.getLastName()
                                .compareToIgnoreCase(
                                        p2.getLastName()
                                );
                    }
                }
        );

        System.out.println(
                "Patients sorted by surname."
        );

        displayAllPatients();
    }

    // ===== SORT BY PATIENT ID =====
    // Sorts patients by their ID
    public void sortPatientsById() {

        Collections.sort(
                patients,
                new Comparator<Patient>() {

                    @Override
                    public int compare(Patient p1, Patient p2) {

                        return p1.getPatientId()
                                .compareToIgnoreCase(
                                        p2.getPatientId()
                                );
                    }
                }
        );

        System.out.println(
                "Patients sorted by Patient ID."
        );

        displayAllPatients();
    }

    // ===== READ INTEGER SAFELY =====
    // Helper method to read an integer from user
    // Keeps asking until a valid number is entered
    public int readInteger(String message) {

        while (true) {

            System.out.print(message);

            try {

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }

    // ===== MAIN MENU =====
    // Displays the main menu options
    public void displayMenu() {

        System.out.println(
                "\n========================================"
        );

        System.out.println(
                "       HOSPITAL MANAGEMENT SYSTEM"
        );

        System.out.println(
                "========================================"
        );

        System.out.println("1. Register New Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Update Patient Details");
        System.out.println("4. Delete Patient");
        System.out.println("5. Display All Patients");
        System.out.println("6. Display Ward Layout");
        System.out.println("7. Display Available Beds");
        System.out.println("8. Display Occupied Beds");
        System.out.println("9. Display Total Patients");
        System.out.println("10. Display Occupied Beds Count");
        System.out.println("11. Display Occupancy Percentage");
        System.out.println("12. Sort Patients by Surname");
        System.out.println("13. Sort Patients by ID");
        System.out.println("14. Exit");

        System.out.println(
                "========================================"
        );

        System.out.print("Enter your choice: ");
    }

    // ===== RUN PROGRAM =====
    // Main program loop - keeps running until user exits
    public void run() {

        while (true) {

            displayMenu();

            int choice;

            try {

                choice = Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );

                continue;
            }

            // Handle user's menu choice
            switch (choice) {

                case 1:
                    registerPatient();
                    break;

                case 2:
                    searchPatient();
                    break;

                case 3:
                    updatePatient();
                    break;

                case 4:
                    deletePatient();
                    break;

                case 5:
                    displayAllPatients();
                    break;

                case 6:
                    ward.displayWardLayout();
                    break;

                case 7:
                    ward.displayAvailableBeds();
                    break;

                case 8:
                    ward.displayOccupiedBeds();
                    break;

                case 9:
                    displayTotalPatients();
                    break;

                case 10:
                    displayOccupiedBedsCount();
                    break;

                case 11:
                    displayOccupancyPercentage();
                    break;

                case 12:
                    sortPatientsBySurname();
                    break;

                case 13:
                    sortPatientsById();
                    break;

                case 14:

                    System.out.println(
                            "Thank you for using the "
                            + "Hospital Management System!"
                    );

                    scanner.close();  // Close the scanner
                    return;  // Exit the program

                default:

                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }

    // ===== MAIN METHOD =====
    // This is where the program starts
    public static void main(String[] args) {

        // Create a new instance of the system
        Hospitalmanagementsystems system =
                new Hospitalmanagementsystems();

        // Start the program
        system.run();
    }
}