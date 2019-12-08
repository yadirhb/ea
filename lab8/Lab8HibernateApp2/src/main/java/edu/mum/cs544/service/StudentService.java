package edu.mum.cs544.service;

import edu.mum.cs544.helper.EntityManagerHelper;
import edu.mum.cs544.dao.StudentDAO;
import edu.mum.cs544.domain.Student;

import javax.persistence.EntityManager;

public class StudentService {
	private StudentDAO studentdao;

	public StudentService() {
		studentdao = new StudentDAO();
	}

	public Student getStudent(long studentid) {
		EntityManager em = EntityManagerHelper.getCurrent();
		em.getTransaction().begin();
		Student student = studentdao.load(studentid);
		em.getTransaction().commit();
		em.close();
		return student;
	}
}
