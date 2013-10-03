package net.canang.cfi.core.vm.dao.impl;

import net.canang.cfi.core.dd.model.*;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.CfMetadata;
import net.canang.cfi.core.so.model.CfUser;
import net.canang.cfi.core.vm.dao.CfVoteDao;
import net.canang.cfi.core.vm.model.CfVote;
import net.canang.cfi.core.vm.model.CfVoteTransaction;
import net.canang.cfi.core.vm.model.CfVoteTransactionType;
import net.canang.cfi.core.vm.model.impl.CfVoteImpl;
import net.canang.cfi.core.vm.model.impl.CfVoteTransactionImpl;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.perf4j.aop.Profiled;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static net.canang.cfi.core.so.model.CfMetaState.ACTIVE;
import static net.canang.cfi.core.vm.model.CfVoteTransactionType.*;

/**
 * @author : alif haikal razak
 */
public class CfVoteDaoImpl extends DaoSupport<Long, CfVote, CfVoteImpl> implements CfVoteDao {

    private static final Logger log = Logger.getLogger(CfVoteDaoImpl.class);

    @Override
    public CfVote newInstance() {
        CfVote vote = new CfVoteImpl();
        return vote;
    }

    @Override
    public CfVoteTransaction newTransactionInstance() {
        CfVoteTransaction voteTx = new CfVoteTransactionImpl();
        return voteTx;
    }

    @Override
    public CfVote find(CfPeriod period, CfSodoCode sodo) {
        Validate.notNull(period, "Budget cannot be empty");
        Validate.notNull(sodo, "Sodo cannot be empty");

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select v from CfVote v where " +
                "v.sodo = :sodo " +
                "and v.budget = :period " +
                "and v.metadata.state = :metaState");
        query.setEntity("sodo", sodo);
        query.setEntity("period", period);
        query.setCacheable(Boolean.TRUE);
        query.setInteger("metaState", ACTIVE.ordinal());
        return (CfVote) query.uniqueResult();
    }

    @Profiled
    @Override
    public List<CfVote> findByPeriod(CfPeriod period) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select v from CfVote v where " +
                "v.budget = :period " +
                "and v.metadata.state = :metaState");
        query.setEntity("period", period);
        query.setInteger("metaState", ACTIVE.ordinal());
        return (List<CfVote>) query.list();
    }

    @Override
    public List<CfVote> findByPeriod(CfPeriod period, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select v from CfVote v where " +
                "v.budget = :period " +
                "and v.metadata.state = :metaState");
        query.setEntity("period", period);
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfVote>) query.list();
    }

    @Override
    public List<CfVote> findByPeriodAndSodo(CfPeriod period, CfSodoCode sodo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select v from CfVote v where " +
                "v.budget = :period " +
                "and v.sodo = :sodo " +
                "and v.metadata.state = :metaState " +
                "order by v.id asc");
        query.setEntity("period", period);
        query.setEntity("sodo", sodo);
        query.setInteger("metaState", ACTIVE.ordinal());
        return (List<CfVote>) query.list();
    }

    @Override
    public List<CfVote> findByPeriodAndSodo(CfPeriod period, CfSodoCode sodo, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select v from CfVote v where " +
                "v.budget = :period " +
                "and v.sodo = :sodo " +
                "and v.metadata.state = :metaState " +
                "order by v.id asc");
        query.setEntity("period", period);
        query.setEntity("sodo", sodo);
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfVote>) query.list();
    }

    @Override
    public List<CfVote> find(CfFundCode fund, CfDepartmentCode department, CfProjectCode project, CfSubProjectCode subProjectCode, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public List<CfVote> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select v from CfVote v where " +
                "(v.code like upper(:filter) " +
                "or v.description like upper(:filter)) " +
                "and v.metadata.state = :metaState " +
                "order by v.id asc");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfVote>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(v) from CfVote v where " +
                "v.metadata.state = :metaState");
        query.setInteger("metaState", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(CfFundCode fund, CfDepartmentCode department, CfProjectCode project, CfSubProjectCode subProjectCode) {
        return null;
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(v) from CfVote v where" +
                "(v.code like upper(:filter) " +
                "or v.description like upper(:filter)) " +
                "and v.metadata.state = :metaState " +
                "order by v.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("metaState", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public BigDecimal getVoteBalance(CfPeriod period, CfSodoCode sodo) {
        return getSumSodo(period, sodo);
    }

    @Override
    public BigDecimal getVoteBalance(CfAccountCode account, CfPeriod period) {
        return getSumSodo(account, period);
    }

    @Override
    public void addTransaction(CfVote vote, CfVoteTransaction voteTx, CfUser user) {
        Session session = sessionFactory.getCurrentSession();
        voteTx.setVote(vote);

        // prepare metadata
        CfMetadata metadata = new CfMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreator(user.getId());
        metadata.setState(ACTIVE);
        voteTx.setMetadata(metadata);
        session.save(vote);
    }

    @Override
    public void addTransactions(CfVote vote, List<CfVoteTransaction> voteTx, CfUser user) {
        for (CfVoteTransaction tx : voteTx) {
            addTransaction(vote, tx, user);
        }
    }

    @Override
    public List<CfVoteTransaction> findTransactions(CfVote vote) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select vt from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.metadata.state = :metaState " +
                "order by vt.id asc");
        query.setEntity("vote", vote);
        query.setInteger("metaState", ACTIVE.ordinal());
        return (List<CfVoteTransaction>) query.list();
    }

    @Override
    public List<CfVoteTransaction> findTransactions(CfVote vote, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select vt from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.metadata.state = :metaState " +
                "order by vt.id asc");
        query.setEntity("vote", vote);
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfVoteTransaction>) query.list();
    }

    @Override
    public List<CfVoteTransaction> findTransactions(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select vt from CfVoteTransaction vt where " +
                "vt.sourceNo = :sourceNo " +
                "and vt.metadata.state = :metaState " +
                "order by vt.id asc");
        query.setString("sourceNo", referenceNo);
        query.setInteger("metaState", ACTIVE.ordinal());
        return (List<CfVoteTransaction>) query.list();
    }

    @Override
    public List<CfVoteTransaction> findTransactions(CfVote vote, CfSodoCode spender, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select vt from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.spender = :spender " +
                "and vt.metadata.state = :metaState " +
                "order by vt.id asc");
        query.setEntity("vote", vote);
        query.setEntity("spender", spender);
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfVoteTransaction>) query.list();
    }

    @Override
    public Integer countTransaction(CfVote vote) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(vt) from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.metadata.state = :metaState");
        query.setEntity("vote", vote);
        query.setInteger("metaState", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(CfPeriod period) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(v) from CfVote v where " +
                "v.budget = :period " +
                "and v.metadata.state = :metaState");
        query.setEntity("period", period);
        query.setInteger("metaState", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(CfPeriod period, CfSodoCode sodo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(v) from CfVote v where " +
                "v.budget = :period " +
                "and v.sodo = :sodo " +
                "and v.metadata.state = :metaState");
        query.setEntity("period", period);
        query.setEntity("sodo", sodo);
        query.setInteger("metaState", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExists(CfPeriod period, CfSodoCode sodo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(v.id) from CfVote v where " +
                "v.budget = :period " +
                "and v.sodo = :sodo " +
                "and v.metadata.state = :metaState");
        query.setEntity("period", period);
        query.setEntity("sodo", sodo);
        query.setCacheable(Boolean.TRUE);
        query.setInteger("metaState", ACTIVE.ordinal());
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public BigDecimal checkBalance(CfVoteTransactionType transactionType, CfVote vote) {
        // validate precondition
        Validate.notNull(vote, "Vote cannot be null");
        Validate.notNull(vote.getApprovedAmount(), "Approve amount cannot be null");

        // sum it up
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(amount) from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.transactionCode = :transactionCode  " +
                "and vt.metadata.state = :metaState");
        query.setEntity("vote", vote);
        query.setInteger("transactionCode", transactionType.ordinal());
        query.setInteger("metaState", ACTIVE.ordinal());
        BigDecimal sum = (BigDecimal) query.uniqueResult();

        // if null, something is wrong
        if (null == sum) return BigDecimal.ZERO;
        Validate.notNull(sum, "Sum cannot be null");

        return sum;
    }

    @Override
    public BigDecimal checkShadowBalance(CfVote vote) {
        // validate precondition
        Validate.notNull(vote, "Vote cannot be null");
        Validate.notNull(vote.getApprovedAmount(), "Approve amount cannot be null");

        // sum it up
        Session session = sessionFactory.getCurrentSession();
        Query query1 = session.createQuery("select sum(amount) from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.transactionCode in :transactionCode  " +
                "and vt.metadata.state = :metaState");
        query1.setEntity("vote", vote);
        query1.setParameterList("transactionCode",
                new Integer[]{BALANCE_OPENING.ordinal(),
                        BALANCE_ADDITION.ordinal(),
                        BALANCE_VIREMENT.ordinal()});
        query1.setInteger("metaState", ACTIVE.ordinal());
        BigDecimal sum = (BigDecimal) query1.uniqueResult();
        if (null == sum) sum = BigDecimal.ZERO;

        Query query2 = session.createQuery("select sum(amount) from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.transactionCode in :transactionCode  " +
                "and vt.metadata.state = :metaState");
        query2.setEntity("vote", vote);
        query1.setParameterList("transactionCode",
                new Integer[]{CUMULATIVE_EXPENDITURE.ordinal()});
        query2.setInteger("metaState", ACTIVE.ordinal());
        BigDecimal expenses = (BigDecimal) query2.uniqueResult();
        if (null == expenses) expenses = BigDecimal.ZERO;


        return sum.subtract(expenses);
    }

    @Override
    public BigDecimal checkCurrentBalance(CfVote vote) {
        // validate precondition
        Validate.notNull(vote, "Vote cannot be null");
        Validate.notNull(vote.getApprovedAmount(), "Approve amount cannot be null");

        // sum it up
        Session session = sessionFactory.getCurrentSession();
        Query query1 = session.createQuery("select sum(amount) from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.transactionCode in (:transactionCode)  " +
                "and vt.metadata.state = :metaState");
        query1.setEntity("vote", vote);
        query1.setParameterList("transactionCode",
                new Integer[]{BALANCE_OPENING.ordinal(),
                        BALANCE_ADDITION.ordinal(),
                        BALANCE_VIREMENT.ordinal()});
        query1.setInteger("metaState", ACTIVE.ordinal());
        BigDecimal sum = (BigDecimal) query1.uniqueResult();
        if (null == sum) sum = BigDecimal.ZERO;

        Query query2 = session.createQuery("select sum(amount) from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.transactionCode = :transactionCode  " +
                "and vt.metadata.state = :metaState");
        query2.setEntity("vote", vote);
        query2.setInteger("transactionCode", CUMULATIVE_EXPENDITURE.ordinal());
        query2.setInteger("metaState", ACTIVE.ordinal());
        BigDecimal expenses = (BigDecimal) query2.uniqueResult();
        if (null == expenses) expenses = BigDecimal.ZERO;


        return sum.subtract(expenses);
    }

    @Override
    public BigDecimal checkBalance(CfVoteTransactionType transactionType, CfVote vote, boolean positiveTx) {
        // validate precondition
        Validate.notNull(vote, "Vote cannot be null");
        Validate.notNull(vote.getApprovedAmount(), "Approve amount cannot be null");

        String descriminator = "";
        if (positiveTx) descriminator = "and vt.amount < 0";
        else descriminator = "and vt.amount > 0";

        // sum it up
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(amount) from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.transactionCode = :transactionCode " +
                "and vt.metadata.state = :metaState " + descriminator);
        query.setEntity("vote", vote);
        query.setInteger("transactionCode", transactionType.ordinal());
        query.setInteger("metaState", ACTIVE.ordinal());
        BigDecimal sum = (BigDecimal) query.uniqueResult();

        // if null, something is wrong
        if (null == sum) return BigDecimal.ZERO;
        Validate.notNull(sum, "Sum cannot be null");

        return sum;
    }

    @Override
    public BigDecimal checkBalance(CfVoteTransactionType transactionType, CfVote vote, Date startDate, Date endDate) {
        // validate precondition
        Validate.notNull(vote, "Vote cannot be null");
        Validate.notNull(vote.getApprovedAmount(), "Approve amount cannot be null");

        // sum it up
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(amount) from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.metadata.createdDate >= :startDate " +
                "and vt.metadata.createdDate <= :endDate " +
                "and vt.transactionCode = :transactionCode " +
                "and vt.metadata.state = :metaState");
        query.setEntity("vote", vote);
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);
        query.setCacheable(Boolean.TRUE);
        query.setInteger("transactionCode", transactionType.ordinal());
        query.setInteger("metaState", ACTIVE.ordinal());
        BigDecimal sum = (BigDecimal) query.uniqueResult();

        // if null, something is wrong
        if (null == sum) return BigDecimal.ZERO;
        Validate.notNull(sum, "Sum cannot be null");
        return sum;
    }

    @Override
    public BigDecimal checkBalance(CfVoteTransactionType transactionType, CfVote vote, Date startDate, Date endDate, boolean positiveTx) {
        // validate precondition
        Validate.notNull(vote, "Vote cannot be null");
        Validate.notNull(vote.getApprovedAmount(), "Approve amount cannot be null");

        String descriminator = "";
        if (positiveTx) descriminator = "and vt.amount < 0";
        else descriminator = "and vt.amount > 0";

        // sum it up
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(amount) from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.metadata.createdDate >= :startDate " +
                "and vt.metadata.createdDate <= :endDate " +
                "and vt.transactionCode = :transactionCode " +
                "and vt.metadata.state = :metaState " + descriminator);
        query.setEntity("vote", vote);
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);
        query.setInteger("transactionCode", transactionType.ordinal());
        query.setInteger("metaState", ACTIVE.ordinal());
        BigDecimal sum = (BigDecimal) query.uniqueResult();

        // if null, something is wrong
        if (null == sum) return BigDecimal.ZERO;
        Validate.notNull(sum, "Sum cannot be null");

        return sum;
    }

    @Override
    public BigDecimal checkShadowBalance(CfVote vote, Date startDate, Date endDate) {
        // validate precondition
        Validate.notNull(vote, "Vote cannot be null");
        Validate.notNull(vote.getApprovedAmount(), "Approve amount cannot be null");

        // sum it up
        Session session = sessionFactory.getCurrentSession();
        Query query1 = session.createQuery("select sum(amount) from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.metadata.createdDate >= :startDate " +
                "and vt.metadata.createdDate <= :endDate " +
                "and vt.transactionCode in (:transactionCode) " +
                "and vt.metadata.state = :metaState");
        query1.setEntity("vote", vote);
        query1.setParameterList("transactionCode",
                new Integer[]{CfVoteTransactionType.BALANCE_OPENING.ordinal(),
                        CfVoteTransactionType.BALANCE_ADDITION.ordinal(),
                        CfVoteTransactionType.BALANCE_VIREMENT.ordinal()});
        query1.setInteger("metaState", ACTIVE.ordinal());
        query1.setDate("startDate", startDate);
        query1.setDate("endDate", endDate);
        BigDecimal sum = (BigDecimal) query1.uniqueResult();
        if (null == sum) sum = BigDecimal.ZERO;

        Query query2 = session.createQuery("select sum(amount) from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.metadata.createdDate >= :startDate " +
                "and vt.metadata.createdDate <= :endDate " +
                "and vt.transactionCode in (:transactionCode)  " +
                "and vt.metadata.state = :metaState");
        query2.setEntity("vote", vote);
        query1.setParameterList("transactionCode",
                new Integer[]{CfVoteTransactionType.CUMULATIVE_EXPENDITURE.ordinal()});
        query2.setInteger("metaState", ACTIVE.ordinal());
        query2.setDate("startDate", startDate);
        query2.setDate("endDate", endDate);
        BigDecimal expenses = (BigDecimal) query2.uniqueResult();
        if (null == expenses) expenses = BigDecimal.ZERO;


        return sum.subtract(expenses);
    }

    @Override
    public BigDecimal checkCurrentBalance(CfVote vote, Date startDate, Date endDate) {
        // validate precondition
        Validate.notNull(vote, "Vote cannot be null");
        Validate.notNull(vote.getApprovedAmount(), "Approve amount cannot be null");

        // sum it up
        Session session = sessionFactory.getCurrentSession();
        Query query1 = session.createQuery("select sum(amount) from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.metadata.createdDate >= :startDate " +
                "and vt.metadata.createdDate <= :endDate " +
                "and vt.transactionCode in (:transactionCode)  " +
                "and vt.metadata.state = :metaState");
        query1.setEntity("vote", vote);
        query1.setParameterList("transactionCode",
                new Integer[]{CfVoteTransactionType.BALANCE_OPENING.ordinal(),
                        CfVoteTransactionType.BALANCE_ADDITION.ordinal(),
                        CfVoteTransactionType.BALANCE_VIREMENT.ordinal()});
        query1.setInteger("metaState", ACTIVE.ordinal());
        query1.setDate("startDate", startDate);
        query1.setDate("endDate", endDate);
        BigDecimal sum = (BigDecimal) query1.uniqueResult();
        if (null == sum) sum = BigDecimal.ZERO;

        Query query2 = session.createQuery("select sum(amount) from CfVoteTransaction vt where " +
                "vt.vote = :vote " +
                "and vt.metadata.createdDate >= :startDate " +
                "and vt.metadata.createdDate <= :endDate " +
                "and vt.transactionCode = :transactionCode  " +
                "and vt.metadata.state = :metaState");
        query2.setEntity("vote", vote);
        query2.setInteger("transactionCode", CUMULATIVE_EXPENDITURE.ordinal());
        query2.setInteger("metaState", ACTIVE.ordinal());
        query2.setDate("startDate", startDate);
        query2.setDate("endDate", endDate);
        BigDecimal expenses = (BigDecimal) query2.uniqueResult();
        if (null == expenses) expenses = BigDecimal.ZERO;
        return sum.subtract(expenses);
    }

    private BigDecimal getSumSodo(CfAccountCode account, CfPeriod period) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(v.approvedAmount) from CfVote v inner join v.transactions t " +
                "where " +
                "v.budget = :budget " +
                "and v.sodo.account = :account " +
                "and t.metadata.state = :metaState");
        query.setEntity("budget", period);
        query.setEntity("account", account);
        query.setInteger("metaState", ACTIVE.ordinal());
        return (BigDecimal) query.uniqueResult();
    }

    private BigDecimal getSumSodo(CfPeriod period, CfSodoCode sodo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(v.approvedAmount) from CfVote v inner join v.transactions t " +
                "where v.budget = :period " +
                "and v.sodo = :sodo " +
                "and t.metadata.state = :metaState");
        query.setEntity("period", period);
        query.setEntity("sodo", sodo);
        query.setInteger("metaState", ACTIVE.ordinal());
        return (BigDecimal) query.uniqueResult();
    }

}
