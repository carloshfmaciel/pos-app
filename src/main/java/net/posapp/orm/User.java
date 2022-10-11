package net.posapp.orm;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@javax.persistence.Entity
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode
public class User implements Serializable {

	private static final long serialVersionUID = -2329807463128123378L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "st_name")
	private String name;

	@Column(name = "status")
	private String status;

	@OneToOne
	@JoinColumn(name = "st_entity_type", referencedColumnName = "st_entity_type")
	private EntityType entityType;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_address", referencedColumnName = "id")
	private Address address;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
	private List<Role> roles;

	@Column(name = "email", unique = true)
	private String email;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	public User() {
	}

	public User(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public User(User user) {
		super();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.roles = user.getRoles();
		this.id = user.getId();
	}

	public User(String name, String email, String password, List<Role> roles) {
		super();
		this.name = name;
		this.email = email;
		this.roles = roles;
		this.password = password;
	}

}
