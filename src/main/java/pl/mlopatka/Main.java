package pl.mlopatka;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import pl.mlopatka.model.Customer;
import pl.mlopatka.model.ExternalAccount;
import pl.mlopatka.model.InternalAccount;
import pl.mlopatka.model.Transfer;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws SQLException {
        Configuration conf = getConfiguration();
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Customer c1 = new Customer();
        Customer c2 = new Customer();

        InternalAccount ci1 = new InternalAccount("1234123412341234", new BigDecimal(10.0),
                Currency.getInstance("PLN"));
        InternalAccount ci12 = new InternalAccount("2200000012344321", new BigDecimal(1000.0),
                Currency.getInstance("EUR"));
        InternalAccount ci2 = new InternalAccount("1900000012344321", new BigDecimal(1000.0),
                Currency.getInstance("EUR"));
        ci1.setCustomer(c1);
        ci12.setCustomer(c1);
        ci2.setCustomer(c2);

        Set<InternalAccount> accountsC1 = new HashSet<>();
        accountsC1.add(ci1);
        accountsC1.add(ci12);

        Set<InternalAccount> accountsC2 = new HashSet<>();
        accountsC2.add(ci2);

        c1.setAccounts(accountsC1);
        c2.setAccounts(accountsC2);

        Transfer transfer = new Transfer("2200000012344321", "1900000012344321",
                "Transfer", new BigDecimal(1000.0), Currency.getInstance("EUR"), LocalDateTime.now());

        ExternalAccount extAcc = new ExternalAccount("7700000014231251", Currency.getInstance("PLN"));

        session.save(c1);
        session.save(c2);
        session.save(ci1);
        session.save(ci12);
        session.save(ci2);
        session.save(transfer);
        session.save(extAcc);

        performQuery("from Customer", session);
        tx.commit();
        session.close();

        Session session2 = factory.openSession();
        performQuery("from Customer", session2);
        session2.close();

        factory.close();
//        makeSessions();
//        JdbcConnectionUtil.checkHibernate();
    }

    private static void performQuery(String hql, Session session2) {
        Query query = session2.createQuery(hql);
        List result = query.list();
        result.stream().forEach(n -> System.out.println(n));
    }

    private static void makeSessions() {
        Configuration conf = getConfiguration();
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        tx.rollback();
        session.close();
        Session session2 = factory.openSession();
        Transaction tx2 = session2.beginTransaction();

        Query query = session2.createSQLQuery("select * from users");
        List list = query.list();
        list.stream().forEach(n -> System.out.println(n));
        tx2.commit();
        session2.close();

        factory.close();
    }

    private static Configuration getConfiguration() {
        Configuration conf = new Configuration();
        Properties properties = getProperties();
        conf.setProperties(properties);
        conf.addAnnotatedClass(Customer.class);
        conf.addAnnotatedClass(Transfer.class);
        conf.addAnnotatedClass(ExternalAccount.class);
        conf.addAnnotatedClass(InternalAccount.class);
        return conf;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.HBM2DDL_AUTO,"create-drop");
        properties.setProperty(Environment.DRIVER, "org.h2.Driver");
        properties.setProperty(Environment.USER, "sa");
        properties.setProperty(Environment.PASS, "");
        properties.setProperty(Environment.URL, "jdbc:h2:~/test");
        return properties;
    }

}
