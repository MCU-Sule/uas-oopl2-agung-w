package com.uas.agung.dao;

import com.uas.agung.entity.FeMember;
import com.uas.agung.entity.FeTransaction;
import com.uas.agung.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class FeTransactionDao implements daoInterface<FeTransaction> {

    @Override
    public int addData(FeTransaction data) {
        return daoInterface.super.addData(data);
    }

    @Override
    public int delData(FeTransaction data) {
        return daoInterface.super.delData(data);
    }

    @Override
    public int updateData(FeTransaction data) {
        return daoInterface.super.updateData(data);
    }

    @Override
    public ObservableList<FeTransaction> showData() {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder b=session.getCriteriaBuilder();
        CriteriaQuery<FeTransaction> criteriaQuery= b.createQuery(FeTransaction.class);
        criteriaQuery.from(FeTransaction.class);
        List<FeTransaction> list=session.createQuery(criteriaQuery).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
    public ObservableList<FeTransaction> showDataById(FeMember id) {
        String hql = "FROM FeTransaction E WHERE E.feMemberByMemberCitizenId =:tid ";
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery(hql);
        query.setParameter("tid", id);
        List<FeTransaction> results = query.list();
        session.close();
        return FXCollections.observableArrayList(results);
    }
    public List sumDataById(FeMember id) {
        String hql = "SELECT SUM(E.nominal) FROM FeTransaction E WHERE E.feMemberByMemberCitizenId =:tid ";
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery(hql);
        query.setParameter("tid", id);
        List results = query.list();
        session.close();
        return results;
    }
}
