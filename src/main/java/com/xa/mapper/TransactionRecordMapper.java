package com.xa.mapper;

import com.xa.dto.RecordDTO;
import com.xa.pojo.TransactionRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TransactionRecordMapper {
    int insert(TransactionRecord transactionRecord);

//    List<TransactionRecord> queryByCardNo(@Param("cardNo") String cardNo, @Param("start") Date start, @Param("end") Date end);
    List<TransactionRecord> queryByCardNo(RecordDTO recordDTO);
}
