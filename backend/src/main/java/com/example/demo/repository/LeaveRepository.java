package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave,Integer> {


    //native query
    @Query(value="Select * from leave_app order by date desc", nativeQuery= true)
    public List<Leave> getAllLeaveDueDateDesc();


    @Query(value="Select * from leave_app order by date desc", nativeQuery= true)
    public List<Leave> getAllLeaveDueDateDescnd(Long id);

    public List<Leave> getAllByTrainer_TrainerId(Long id);


//JPA query
//	@Query(value="Select new com.sam.datatransferobject.CountType(COUNT(*)/(Select count(*) from Leave) *100,type) from Leave GROUP BY type", nativeQuery= true)
//	public List<CountType> getPercentageGroupByType();

//
//	@Query(value="SELECT * from person GROUP BY type", nativeQuery= true)
//	public List<Leave> getAllLeaveDueDateDesc();
//



}
