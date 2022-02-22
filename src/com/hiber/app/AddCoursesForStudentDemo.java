package com.hiber.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hiber.entity.Course;
import com.hiber.entity.Instructor;
import com.hiber.entity.InstructorDetail;
import com.hiber.entity.Review;
import com.hiber.entity.Student;

public class AddCoursesForStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			int id = 1;
			Student student = session.get(Student.class, id);

			Course course1 = new Course("Music Composition");
			Course course2 = new Course("Composition with Cubase");
			if (student != null) {
				System.out.println(student);
				System.out.println(student.getCourses());
				course1.addStudent(student);
				course2.addStudent(student);

			}
			
			session.save(course1);
			session.save(course2);

			session.getTransaction().commit();

		} finally {
			session.close();
			factory.close();
		}
	}

}
