package com.hiber.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hiber.entity.Course;
import com.hiber.entity.Instructor;
import com.hiber.entity.InstructorDetail;
import com.hiber.entity.Review;
import com.hiber.entity.Student;

public class CreateCoursesAndStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			Course course = new Course("Pitches and Synths Manipulation");

			session.save(course);
			
			Student student1 = new Student("Yeonjun", "Choi", "yeonjun@naver.com");
			Student student2 = new Student("Soobin", "Choi", "soobins@naver.com");
			Student student3 = new Student("Kai", "Huening", "kai@naver.com");
			
			course.addStudent(student1);
			course.addStudent(student2);
			course.addStudent(student3);
			
			System.out.println(course.getStudents());
			
			session.save(student1);
			session.save(student2);
			session.save(student3);

			session.getTransaction().commit();

		} finally {
			session.close();
			factory.close();
		}
	}

}
