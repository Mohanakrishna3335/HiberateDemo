package com.kone.DemoHib;


import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Alien alien = new Alien();
		/*
		 * alien.setAid(104); alien.setAname("mohan"); alien.setColor("red");
		 */

		Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		//used to save the data into DB
		//session.save(alien);
		String sql ="SELECT * FROM koneiot.Alien where color='green'";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Alien.class);
        List employees = query.list();

        for (Iterator iterator = employees.iterator(); iterator.hasNext();){
            alien = (Alien) iterator.next(); 
           System.out.print("Alien ID: " + alien.getAid()); 
           System.out.print(" Alien Name: " + alien.getAname()); 
           System.out.println(" Alien color: " + alien.getColor()); 
        }
		tx.commit();
		System.out.println(alien);
	}
}
