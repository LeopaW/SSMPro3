package com.xa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xa.dto.RecordDTO;
import com.xa.mapper.TransactionRecordMapper;
import com.xa.pojo.TransactionRecord;
import com.xa.service.TransactionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("transactionRecordService")
public class TransactionRecordServiceImpl  implements TransactionRecordService {

    @Autowired
    private TransactionRecordMapper transactionRecordMapper;

    @Override
    public PageInfo<TransactionRecord> queryByCardNo(RecordDTO recordDTO) {
        PageHelper.startPage(recordDTO.getPageNum(),recordDTO.getPageSize());
        List<TransactionRecord> transactionRecords = transactionRecordMapper.queryByCardNo(recordDTO);
        PageInfo<TransactionRecord> page = new PageInfo<>(transactionRecords);
        return page;
    }
}
