package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.custom.QueryDAO;
import lk.ijse.dep.pos.entity.CustomEntity;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public CustomEntity getOrderInfo(int orderId) throws Exception {
        return null;
    }

    @Override
    public CustomEntity getOrderInfo2(int orderId) throws Exception {
        return null;

    }

    @Override
    public List<CustomEntity> getOrdersInfo(String query) throws Exception {
        NativeQuery nativeQuery = session.createNativeQuery(
                "SELECT o.id as orderId, c.id as customerId, c.name as customerName, o.date as orderDate, SUM(od.qty * od.unit_price) AS orderTotal  FROM Customer c INNER JOIN `Order` o ON c.id=O.customer_Id " +
                        "INNER JOIN OrderDetail od on o.id = od.order_id WHERE o.id LIKE ?1 OR c.id LIKE ?2 OR c.name LIKE ?3 OR o.date LIKE ?4 GROUP BY o.id");
        nativeQuery.setParameter(1, query);
        nativeQuery.setParameter(2, query);
        nativeQuery.setParameter(3, query);
        nativeQuery.setParameter(4, query);


        Query query1 = nativeQuery.setResultTransformer(Transformers.aliasToBean(CustomEntity.class));

        List<CustomEntity> list = query1.list();

        return list;

    }
}
