package com.springboot.restservices.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
//@JsonIgnoreProperties({"firstname","lastname"}) -- Stating Filtering JsonIgnore
//@JsonFilter(value = "userFilter") -- used for MappingJacksonValue filtering section
public class User extends RepresentationModel<User> {

	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private Long id;

	@NotEmpty(message = "Username is Mandatory field. Please provide username")
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	@JsonView(Views.External.class)
	private String username;

	@Size(min = 2, message = "FirstName show have atleast 2 characters")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String firstname;

	@Column(name = "LAST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String lastname;

	@Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String email;

	@Column(name = "ROLE", length = 50, nullable = false)
	@JsonView(Views.Internal.class)
	private String role;

//	@JsonIgnore -- Stating Filtering JsonIgnore
	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	@JsonView(Views.Internal.class)
	private String ssn;

	@OneToMany(mappedBy = "user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;
	
	@Column(name = "")
	private String address;

	public User(Long id, @NotEmpty(message = "Username is Mandatory field. Please provide username") String username,
			@Size(min = 2, message = "FirstName show have atleast 2 characters") String firstname, String lastname,
			String email, String role, String ssn, List<Order> orders, String address) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders + ", address=" + address
				+ "]";
	}

}
