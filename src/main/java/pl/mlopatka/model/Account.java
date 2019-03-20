package pl.mlopatka.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Currency;

@MappedSuperclass
@Access(AccessType.FIELD)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    protected Integer id;

    @Column(name = "account_number", unique = true, updatable = false, nullable = false, length = 16)
    protected String accountNr;

    @Column(name = "currency", nullable = false)
    protected Currency currency;

    public Account() {
    }

    public Account(final String accountNr, final Currency currency) {
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
