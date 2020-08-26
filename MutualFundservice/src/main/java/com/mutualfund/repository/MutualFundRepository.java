package com.mutualfund.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mutualfund.model.MutualFund;

@Repository
public interface MutualFundRepository extends JpaRepository<MutualFund, Integer> {

@Query(value = "SELECT * FROM mutualfund muf WHERE muf.panNo=:panNo ", nativeQuery = true)
List<MutualFund> findFundDetails(String panNo);

@Query(value = "SELECT * FROM mutualfund muf WHERE muf.panNo=:panNo AND muf.fundId=:fundId", nativeQuery = true)
List<MutualFund> getTransaction(String panNo, Integer fundId);

}

