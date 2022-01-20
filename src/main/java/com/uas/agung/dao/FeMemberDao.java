package com.uas.agung.dao;

import com.uas.agung.entity.FeMember;
import com.uas.agung.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class FeMemberDao implements daoInterface<FeMember> {

    @Override
    public int addData(FeMember data) {
        return daoInterface.super.addData(data);
    }

    @Override
    public int delData(FeMember data) {
        return daoInterface.super.delData(data);
    }

    @Override
    public int updateData(FeMember data) {
        return daoInterface.super.updateData(data);
    }

    @Override
    public ObservableList<FeMember> showData() {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder b=session.getCriteriaBuilder();
        CriteriaQuery<FeMember> criteriaQuery= b.createQuery(FeMember.class);
        criteriaQuery.from(FeMember.class);
        List<FeMember> list=session.createQuery(criteriaQuery).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
    public List countData() {
        String hql = "SELECT COUNT(E.citizenId) FROM FeMember E";
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery(hql);
        List results = query.list();
        session.close();
        return results;
    }
}
