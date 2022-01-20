package com.uas.agung.dao;

import com.uas.agung.util.HibernateUtil;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface daoInterface<E> {
    public default int addData(E data){
        int res=0;
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        try{
            s.save(data);
            t.commit();
            res=1;
        }catch (HibernateException e){

            t.rollback();
        }
        s.close();
        return res;
    };

    public default int delData(E data){
        int res=0;
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        try{
            s.delete(data);
            t.commit();
            res=1;
        }catch (HibernateException e){
            t.rollback();
        }
        s.close();
        return res;
    };

    public default int updateData(E data){
        int res=0;
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        try{
            s.update(data);
            t.commit();
            res=1;
        }catch (HibernateException e){
            t.rollback();
        }
        s.close();
        return res;
    };

    public ObservableList<E> showData();
}
