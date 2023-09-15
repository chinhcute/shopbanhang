package com.example.shopSpringboot.repository;

import com.example.shopSpringboot.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    ProductEntity findByNameAndAdmin_Email(String name, String email);
    Page<ProductEntity> findByAdmin_Email(String email, Pageable pageable);
    @Query(value = "SELECT * FROM products p join oder_details od on p.id = od.product_id\n" +
            "join orders o on od.oder_id = o.id where o.id = ?1", nativeQuery = true)
    List<ProductEntity> list_order(int id);
    @Query(value = "SELECT * FROM products WHERE (products.name LIKE %?1% OR products.description LIKE %?2%) AND products.quantity > 0", nativeQuery = true)
    List<ProductEntity> findByNameLikeOrDescriptionLike(String name, String description);


    List<ProductEntity> findByNameContaining(String name);
    @Query(value = "SELECT * FROM products\n" +
            "WHERE products.quantity > 0\n" +
            "order by sold desc\n" +
            "limit 0, 8",nativeQuery = true)
    List<ProductEntity> findByProductOderByLimit8();
    @Query(value = "SELECT * FROM products p where p.price >= ?1 and p.price <= ?2 and p.quantity >0", nativeQuery = true)
    List<ProductEntity> list_Stat_end(int stat, int end);
    @Query(value = "SELECT p.*, sum(r.rating) as s, count(p.id) as c FROM products p join reviews  r\n" +
            "on p.id = r.product_id\n" +
            "group by p.id \n" +
            "having s / c >= ?1 and p.quantity >0" ,nativeQuery = true)
    List<ProductEntity> findBYRating(int number);

}
