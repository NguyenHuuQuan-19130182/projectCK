package vn.edu.hcmuaf.fit.demo.dao;


import vn.edu.hcmuaf.fit.demo.beans.Catelogy;
import vn.edu.hcmuaf.fit.demo.beans.Product;
import vn.edu.hcmuaf.fit.demo.db.DBConnect;
import vn.edu.hcmuaf.fit.demo.db.JDBIConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDao {
    private static ProductDao instance;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public ProductDao() {

    }

    public static ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDao();
        }
        return instance;
    }

    public List<Product> getAll() {
        return JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("select * from products").mapToBean(Product.class).stream().collect(Collectors.toList());
        });
    }

    public Product getByID(String id) {
        return JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("select  * from  products where  id = ?").bind(0, id).mapToBean(Product.class).first();
        });
    }


    public List<Catelogy> getAllCategory() {
        return JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("select  * from catelogy").mapToBean(Catelogy.class).stream().collect(Collectors.toList());
        });
    }

    public List<Product> getProductByCID(String cid) {
        return JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("select  * from  products where idCa = ?").bind(0, cid).mapToBean(Product.class).list();
        });
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        return list;
    }

}
