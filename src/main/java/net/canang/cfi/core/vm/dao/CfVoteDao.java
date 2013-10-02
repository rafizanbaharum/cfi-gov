package net.canang.cfi.core.vm.dao;

import net.canang.cfi.core.dd.model.*;
import net.canang.cfi.core.so.model.CfUser;
import net.canang.cfi.core.vm.model.CfVote;
import net.canang.cfi.core.vm.model.CfVoteTransaction;
import net.canang.cfi.core.vm.model.CfVoteTransactionType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfVoteDao {
    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    CfVote newInstance();

    CfVoteTransaction newTransactionInstance();

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfVote refresh(CfVote vote);

    CfVote find(CfPeriod period, CfSodoCode sodo);

    CfVote findById(Long id);

    List<CfVote> findByPeriod(CfPeriod period);

    List<CfVote> findByPeriod(CfPeriod period, Integer offset, Integer limit);

    List<CfVote> findByPeriodAndSodo(CfPeriod period, CfSodoCode sodo);

    List<CfVote> findByPeriodAndSodo(CfPeriod period, CfSodoCode sodo, Integer offset, Integer limit);

    List<CfVote> find();

    List<CfVote> find(Integer offset, Integer limit);

    List<CfVote> find(CfFundCode fund, CfDepartmentCode department, CfProjectCode project, CfSubProjectCode subProjectCode, Integer offset, Integer limit);

    List<CfVote> find(String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(CfFundCode fund, CfDepartmentCode department, CfProjectCode project, CfSubProjectCode subProjectCode);

    Integer count(String filter);

    BigDecimal getVoteBalance(CfPeriod period, CfSodoCode sodo);

    BigDecimal getVoteBalance(CfAccountCode account, CfPeriod period);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    CfVote save(CfVote vote, CfUser user);

    CfVote update(CfVote vote, CfUser user);

    CfVote deactivate(CfVote vote, CfUser user);

    void remove(CfVote vote, CfUser user);

    void addTransaction(CfVote vote, CfVoteTransaction voteTx, CfUser user);

    void addTransactions(CfVote vote, List<CfVoteTransaction> voteTx, CfUser user);

    List<CfVoteTransaction> findTransactions(CfVote vote);

    List<String> findInvalidTransactions(Integer offset, Integer limit);

    List<CfVoteTransaction> findTransactions(CfVote vote, Integer offset, Integer limit);

    List<CfVoteTransaction> findTransactions(String reference);

    List<CfVoteTransaction> findTransactions(CfVote vote, CfSodoCode spender, Integer offset, Integer limit);

    Integer countTransaction(CfVote vote);

    Integer count(CfPeriod period);

    Integer count(CfPeriod period, CfSodoCode sodo);

    boolean isExists(CfPeriod period, CfSodoCode sodo);

    BigDecimal checkBalance(CfVoteTransactionType currentBalance, CfVote vote);

    BigDecimal checkShadowBalance(CfVote vote);

    BigDecimal checkCurrentBalance(CfVote vote);

    BigDecimal checkBalance(CfVoteTransactionType currentBalance, CfVote vote, boolean positiveTx);

    BigDecimal checkBalance(CfVoteTransactionType currentBalance, CfVote vote, Date startDate, Date endDate);

    BigDecimal checkShadowBalance(CfVote vote, Date startDate, Date endDate);

    BigDecimal checkCurrentBalance(CfVote vote, Date startDate, Date endDate);

    BigDecimal checkBalance(CfVoteTransactionType currentBalance, CfVote vote, Date startDate, Date endDate, boolean positiveTx);

}
