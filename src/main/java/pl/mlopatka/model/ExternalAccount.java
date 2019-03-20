package pl.mlopatka.model;

import org.hibernate.annotations.GenericGenerator;

import javax.money.CurrencyUnit;
import javax.persistence.*;
import java.util.Currency;

@Entity
@Table(name = "external_accounts")
@Access(AccessType.FIELD)
public class ExternalAccount extends BaseEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "account_number", unique = true, updatable = false, nullable = false, length = 16)
    private String accountNr;

    @Column(name = "currency", nullable = false)
    private Currency currency;

    public ExternalAccount() {
    }

    public ExternalAccount(final String accountNr, final Currency currency) {
        this.accountNr = accountNr;
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(String accountNr) {
        this.accountNr = accountNr;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}

