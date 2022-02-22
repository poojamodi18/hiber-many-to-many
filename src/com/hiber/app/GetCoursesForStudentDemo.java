package com.hiber.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hiber.entity.Course;
import com.hiber.entity.Instructor;
import com.hiber.entity.InstructorDetail;
import com.hiber.entity.Review;
import com.hiber.entity.Student;

public class GetCoursesForStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			int id = 1;
			Student student = session.get(Student.class, id);

			if (student != null) {
				System.out.println(student);
				System.out.println(student.getCourses());
			}

			session.getTransaction().commit();

		} finally {
			factory.close();
		}
	}

}
