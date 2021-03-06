package com.example.demo.dao;

import com.example.demo.model.TrainingSession;
import com.example.demo.payload.SortRequestTrainingSessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class TrainingSessionDaoImpl implements TrainingSessionDao{

    @Autowired
    private EntityManager entityManager;

        @Override
        public List<TrainingSession> findData(SortRequestTrainingSessions request) {
            String queryStr = "SELECT t\n" +
                    "FROM TrainingSession t\n" +
                    "where \n" +
                    (request.getDeliveryMethod() != null ? "      t.deliveryMethod = :deliveryMethod AND" : "") +
                    (request.getIfsVersion() != null ? "      t.ifsApplicationVersion = :ifsApplicationVersion AND" : "")+
                    (request.getStartDate() != null ? "      t.startDate = :startDate" : "");
            if(queryStr.endsWith("AND")){
                queryStr = queryStr.substring(0, queryStr.length() - 4);
            }
            TypedQuery<TrainingSession> query = entityManager.createQuery(
                    queryStr,
                    TrainingSession.class
            );
            if(request.getDeliveryMethod() != null){
                query = query.setParameter("deliveryMethod", request.getDeliveryMethod());
            }
            if(request.getIfsVersion() != null){
                query = query.setParameter("ifsApplicationVersion", request.getIfsVersion());
            }
            if(request.getStartDate() != null){
                query = query.setParameter("startDate", request.getStartDate());
            }

            return query.getResultList();
        }


}
