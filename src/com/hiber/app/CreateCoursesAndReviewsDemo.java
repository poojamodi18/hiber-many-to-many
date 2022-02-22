package com.hiber.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hiber.entity.Course;
import com.hiber.entity.Instructor;
import com.hiber.entity.InstructorDetail;
import com.hiber.entity.Review;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			Course course = new Course("Pitches and Synths manipulation");
			
			course.addReview(new Review("Cool use of synths"));
			course.addReview(new Review("perfect pitches"));
			course.addReview(new Review("great"));
			
			session.save(course);

			session.getTransaction().commit();

		} finally {
			session.close();
			factory.close();
		}
	}

}
