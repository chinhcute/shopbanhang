package com.example.shopSpringboot.repository;

import com.example.shopSpringboot.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity , Integer> {
    @Query(value = "SELECT * FROM orders o join accounts a on o.user_id = a.id \n" +
            "join oder_details od on o.id = od.oder_id \n" +
            "join products p on od.product_id = p.id where a.email = ?1 " +
            "order by o.created_date desc", nativeQuery = true)
    List<OrderEntity> OderList(String email);
    @Query(value = "SELECT * FROM orders o join accounts a on o.user_id = a.id \n" +
            "            join oder_details od on o.id = od.oder_id \n" +
            "            join products p on od.product_id = p.id where a.email = ?1 AND o.status = 'Completed'\n" +
            "            order by o.created_date desc", nativeQuery = true)
    List<OrderEntity> findCompletedOrders(String email);
    @Query(value = "SELECT * FROM orders o join accounts a on o.user_id = a.id " +
            "join oder_details od on o.id = od.oder_id " +
            "join products p on od.product_id = p.id where a.email = ?1 AND o.status = 'Unpaid' " +
            "order by o.created_date desc", nativeQuery = true)
    List<OrderEntity> findUnpaidOrders(String email);

    @Query(value = "SELECT * FROM orders o join accounts a on o.user_id = a.id " +
            "join oder_details od on o.id = od.oder_id " +
            "join products p on od.product_id = p.id where a.email = ?1 AND o.status = 'Cancel' " +
            "order by o.created_date desc", nativeQuery = true)
    List<OrderEntity> findCancelledOrders(String email);


    @Query(value = "SELECT * FROM orders o join accounts a on o.user_id = a.id " +
            "            join oder_details od on o.id = od.oder_id " +
            "           join products p on od.product_id = p.id where a.email = ?1 AND o.status = 'Paid'" +
            "             order by o.created_date desc", nativeQuery = true)
    List<OrderEntity> findPaidOrders(String email);


    @Query(value = "SELECT * FROM orders o\n" +
            "JOIN oder_details od ON o.id = od.oder_id\n" +
            "JOIN products p ON od.product_id = p.id\n" +
            "JOIN accounts a ON p.admin_id = a.id WHERE a.email = ?1\n" +
            "order by o.created_date desc",
            countQuery = "SELECT COUNT(*) FROM orders o\n" +
                    "JOIN oder_details od ON o.id = od.oder_id\n" +
                    "JOIN products p ON od.product_id = p.id\n" +
                    "JOIN accounts a ON p.admin_id = a.id WHERE a.email = ?1\n",
            nativeQuery = true)
    Page<OrderEntity> admin_order(String email, Pageable pageable);


    @Query(value = "SELECT * " +
            "FROM orders o " +
            "JOIN oder_details od ON o.id = od.oder_id " +
            "JOIN products p ON od.product_id = p.id " +
            "JOIN accounts a ON p.admin_id = a.id " +
            "WHERE a.email = ?1 " +
            "AND (o.name LIKE %?2% OR o.shipping_address LIKE %?3% OR o.created_date LIKE %?4%) " +
            "ORDER BY o.created_date DESC", countQuery = "SELECT COUNT(*) " +
            "FROM orders o " +
            "JOIN oder_details od ON o.id = od.oder_id " +
            "JOIN products p ON od.product_id = p.id " +
            "JOIN accounts a ON p.admin_id = a.id " +
            "WHERE a.email = ?1 " +
            "AND (o.name LIKE %?2% OR o.shipping_address LIKE %?3% OR o.created_date LIKE %?4%) " +
            "ORDER BY o.created_date DESC", nativeQuery = true)
    Page<OrderEntity> list_search_admin_order(String email, String nameKeyword, String addressKeyword, String dateKeyword, Pageable pageable);




}
