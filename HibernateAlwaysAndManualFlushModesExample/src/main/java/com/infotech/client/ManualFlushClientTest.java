package com.infotech.client;

import java.math.BigInteger;

import org.hibernate.FlushMode;
import org.hibernate.Session;

import com.infotech.entities.Person;
import com.infotech.util.HibernateUtil;

public class ManualFlushClientTest {


	public static void main(String[] args) {

		try(Session session = HibernateUtil.getSessionFactory().openSession() ) {
			session.setHibernateFlushMode(FlushMode.MANUAL);
			
			Person  person = new Person();
			person.setFirstName("Gavin");
			person.setLastName("King");
			
			session.beginTransaction();
			session.save(person);
			session.flush();
			BigInteger count = (BigInteger) session.createNativeQuery("select count(*) from Person")
			        .uniqueResult();
			System.out.println("Count:"+count);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}