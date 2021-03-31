package com.springboot.restservices.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends RepresentationModel<Order> {
	@Id
	@GeneratedValue
	@JsonView(Views.Internal.class)
	private Long orderid;
	
	@JsonView(Views.Internal.class)
	private String orderdescription;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;
}
