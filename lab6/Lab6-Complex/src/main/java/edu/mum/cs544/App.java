package edu.mum.cs544;

import edu.mum.cs544.domain.Appointment;
import edu.mum.cs544.domain.Doctor;
import edu.mum.cs544.domain.Patient;
import edu.mum.cs544.domain.Payment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

/**
 * Hello world!
 */
public class App {
    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setCity("Boston");
        patient.setZip("23114");
        patient.setStreet("100 Main Street");

        Doctor doctor = new Doctor();

        doctor.setDoctorType("Eye doctor");
        doctor.setFirstname("Frank");
        doctor.setLastname("Brown");

        em.persist(doctor);

        Payment payment = new Payment();
        payment.setPaydate(LocalDate.of(2008, 5, 12));
        payment.setAmount(100.0);

        Appointment appointment = new Appointment();

        appointment.setDate(LocalDate.of(2008, 5, 15));
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setPayment(payment);

        em.persist(appointment);

        System.out.println();
        em.getTransaction().commit();
        em.close();
    }
}
