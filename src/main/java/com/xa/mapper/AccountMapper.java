package com.xa.mapper;

import com.xa.pojo.Account;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    int existsCardNo(@Param("cardNo") String cardNo);

    Account queryByCardNoPwd(@Param("cardNo") String cardNo, @Param("password") String password);

    double queryBalance(@Param("cardNo") String cardNo);

    /**
     * 返回时1代表未冻结
     * @param cardNo
     * @return
     */
    int freeZon(@Param("cardNo") String cardNo);

    /**
     * 检查cardNo对应的账号要转出transactionAmount是否够
     * @param cardNo
     * @param transactionAmount
     * @return
     */
    int balanceEnough(@Param("cardNo") String cardNo, @Param("transactionAmount") Double transactionAmount);

    void plusMoney(@Param("cardNo") String cardNo, @Param("transactionAmount") Double transactionAmount);

    void minusMoney(@Param("cardNo") String cardNo, @Param("transactionAmount") Double transactionAmount);
}
