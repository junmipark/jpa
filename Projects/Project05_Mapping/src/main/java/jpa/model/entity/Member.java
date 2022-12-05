package jpa.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEMBER")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    private Long memberId;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<Order>();

    private String name;

    private String city;
    private String street;
    private String zipcode;

    // 연관관계 메소드
    public void addOrder(Order order) {
        this.orders.add(order);
        order.setMember(this);
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
