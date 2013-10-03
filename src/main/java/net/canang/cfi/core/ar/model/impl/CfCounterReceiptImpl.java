package net.canang.cfi.core.ar.model.impl;

import net.canang.cfi.core.ar.model.CfCounterReceipt;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
@Entity(name = "CfCounterReceipt")
@Table(name = "CF_CNTR_RCPT")
public class CfCounterReceiptImpl extends CfReceiptImpl implements CfCounterReceipt{
}
