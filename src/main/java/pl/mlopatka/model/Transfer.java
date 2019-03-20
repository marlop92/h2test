package pl.mlopatka.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Table(name = "transfers")
@Access(AccessType.FIELD)
public class Transfer extends BaseEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private int id;

    @Column(name = "sender_account", nullable = false, length = 16)
    private String senderAccount;

    @Column(name = "receiver_account", nullable = false, length = 16)
    private String receiverAccount;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "amount", nullable = false, precision = 14, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "transfer_date", nullable = false)
    private LocalDateTime transferDate;

    public Transfer() {
    }

    public Transfer(final String senderAccount, final String receiverAccount, final String title,
                    final BigDecimal amount, final Currency currency, final LocalDateTime transferDate) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.title = title;
        this.amount = amount;
        this.currency = currency;
        this.transferDate = transferDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }
}
