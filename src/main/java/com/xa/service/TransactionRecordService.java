package com.xa.service;

import com.github.pagehelper.PageInfo;
import com.xa.dto.RecordDTO;
import com.xa.pojo.TransactionRecord;

import java.util.List;

public interface TransactionRecordService {
    PageInfo<TransactionRecord> queryByCardNo(RecordDTO recordDTO);
}
