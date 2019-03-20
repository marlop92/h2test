package pl.mlopatka.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "customers")
@Access(AccessType.FIELD)
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "customer")
    private Set<InternalAccount> accounts;

    public Customer() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Set<InternalAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<InternalAccount> accounts) {
        this.accounts = accounts;
    }
}
