package com.example.demo.dao.impl;

import com.example.demo.dao.VirtualMachineDao;
import com.example.demo.model.VirtualMachine;
import com.example.demo.payload.SortRequestVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class VirtualMachineDaoImpl implements VirtualMachineDao {

    @Autowired
    private EntityManager entityManager;
    @Override
    public List<VirtualMachine> findData(SortRequestVM request) {
        String queryStr = "SELECT v\n" +
                "FROM VirtualMachine v\n" +
                "where \n" +
                (request.getProduct() != null ? "      v.product = :product AND" : "") +
                (request.getVersion() != null ? "      v.version = :version AND" : "")+
                (request.getRegion() != null ? "      v.region = :region" : "");
        if(queryStr.endsWith("AND")){
            queryStr = queryStr.substring(0, queryStr.length() - 4);
        }
        TypedQuery<VirtualMachine> query = entityManager.createQuery(
                queryStr,
                VirtualMachine.class
        );
        if(request.getProduct() != null){
            query = query.setParameter("product", request.getProduct());
        }
        if(request.getVersion() != null){
            query = query.setParameter("version", request.getVersion());
        }
        if(request.getRegion() != null){
            query = query.setParameter("region", request.getRegion());
        }

        return query.getResultList();
    }
}
