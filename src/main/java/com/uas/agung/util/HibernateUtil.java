package com.uas.agung.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static Session getSession(){
        SessionFactory sf;
        sf= new Configuration().configure().buildSessionFactory();
        Session s;
        s=sf.openSession();
        return s;
    }
}
