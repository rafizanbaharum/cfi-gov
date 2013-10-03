package net.canang.cfi.core.jm.model;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.so.model.CfDocument;

import java.math.BigDecimal;
import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfJournal extends CfDocument {

    String getJournalNo();

    void setJournalNo(String journalNo);

    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);

    CfJournalType getJournalType();

    void setJournalType(CfJournalType type);

    CfCostCenter getRequester();

    void setRequester(CfCostCenter requester);

    List<CfJournalTransaction> getTransactions();

    void setTransactions(List<CfJournalTransaction> transactions);
}
