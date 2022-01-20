package com.uas.agung.dao;

import com.uas.agung.entity.FeMember;
import com.uas.agung.entity.FePoint;
import com.uas.agung.entity.FeTransaction;
import com.uas.agung.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class FePointDao implements daoInterface<FePoint> {
    @Override
    public int addData(FePoint data) {
        return daoInterface.super.addData(data);
    }

    @Override
    public int delData(FePoint data) {
        return daoInterface.super.delData(data);
    }

    @Override
    public int updateData(FePoint data) {
        return daoInterface.super.updateData(data);
    }

    @Override
    public ObservableList<FePoint> showData() {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder b=session.getCriteriaBuilder();
        CriteriaQuery<FePoint> criteriaQuery= b.createQuery(FePoint.class);
        criteriaQuery.from(FePoint.class);
        List<FePoint> list=session.createQuery(criteriaQuery).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
    public ObservableList<FePoint> showDataById(FeMember id) {
        String hql = "FROM FePoint E WHERE E.feMemberByMemberCitizenId =:tid ";
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery(hql);
        query.setParameter("tid", id);
        List<FePoint> results = query.list();
        session.close();
        return FXCollections.observableArrayList(results);
    }
    public List sumDataById(FeMember id) {
        String hql = "SELECT SUM(E.value) FROM FePoint E WHERE E.feMemberByMemberCitizenId =:tid ";
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery(hql);
        query.setParameter("tid", id);
        List results = query.list();
        session.close();
        return results;
    }
}
