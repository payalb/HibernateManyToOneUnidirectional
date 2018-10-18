package com.java;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.dto.Phone;
import com.java.dto.Student;
import com.java.dto.Type;

public class Main {
	static SessionFactory sf;
	static {
		Configuration cfg = new Configuration().addPackage("com.java.dto");
		cfg.configure("hibernate.cfg.xml");
		sf = cfg.buildSessionFactory();
	}

	public static void main(String args[]) {
		Main obj = new Main();
		try {
			obj.insertRecords();
			obj.fetchData();
		} finally {
			sf.close();
		}

	}

	public void insertRecords() {
		Student st = new Student(1, "payal");
		Phone p1 = new Phone(76_372l, Type.LANDLINE, st);
		Phone p2 = new Phone(7_38_47_476l, Type.MOBILE, st);
		Session s = sf.openSession();
		s.beginTransaction();
		s.save(st);
		s.save(p1);
		s.save(p2);
		s.getTransaction().commit();
		s.close();
	}

	public void fetchData() {
		Session s = sf.openSession();
		Phone p1 = s.get(Phone.class, 76_372l);
		System.out.println(p1.getStudent());
		s.close();
	}
}
