package br.com.application.model.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import br.com.application.model.company.Company;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false, unique = true)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "company_id", updatable = false)
	private Company company;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	private LocalDateTime lastDateToken;

	@Column(nullable = false)
	private int type;

	@Column(nullable = false)
	private Boolean active = true;

	@Transient
	private String token;

	@Transient
	private String confirmPassword;

	@Column
	private String refreshToken;

	public LocalDateTime getLastDateToken() {
		return lastDateToken;
	}

	public void setLastDateToken(LocalDateTime lastDateToken) {
		this.lastDateToken = lastDateToken;
	}

	public User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getToken() {
		return this.token;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}

// @Id
// @GeneratedValue(strategy = GenerationType.AUTO)
// @Column(nullable = false, updatable = false, unique = true)
// private Integer id;

// @Column(nullable = false, unique = true)
// private String username;
// @Column(nullable = false)
// private String firstName;
// @Column(nullable = false)
// private String lastName;
// @Column(nullable = false)
// private String email;
// @Column(nullable = false)
// private String password;

// private String firebaseToken;
// private LocalDateTime lastDateToken;

// @ManyToMany(cascade = { CascadeType.DETACH })
// @JoinTable(name = "user_customer", joinColumns = { @JoinColumn(name =
// "user_id") }, inverseJoinColumns = {
// @JoinColumn(name = "customer_id") }, uniqueConstraints = {
// @UniqueConstraint(columnNames = { "user_id", "customer_id" }) })
// private List<Customer> customers = new ArrayList<>();

// @JsonIgnore
// @OneToMany(mappedBy = "user", fetch= FetchType.EAGER)
// private List<UserMarina> marinas;

// @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
// @JsonIgnore
// private List<UserAccess> accessList;

// @OneToOne(mappedBy = "user")
// @JsonManagedReference
// private Sailor sailor;

// @JsonIgnore
// @ManyToMany(mappedBy = "users")
// private List<SlingConfig> slingsConfig = new ArrayList<>();

// private LocalDateTime created;

// private Boolean active = true;

// @ManyToOne
// @JoinColumn(name = "role_id")
// private Role roleId ;

// @Transient
// private LocalDateTime lastUserSling;

// @Transient
// private LocalDateTime lastUserSlingApp;

// @Transient
// private String token;

// @Transient
// private String confirmPassword;

// @Column
// private String refreshToken;

// @JsonIgnore
// @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
// "user")
// List<AppNotification> appNotifications;

// @PrePersist
// public void initCreated() {
// created = LocalDateTime.now();
// }

// public User() {
// }

// public User(UserMarinaDTO userMarinaDTO) {
// this.id = userMarinaDTO.getId();
// this.username = userMarinaDTO.getUsername();
// this.firstName = userMarinaDTO.getFirstName();
// this.lastName = userMarinaDTO.getLastName();
// this.email = userMarinaDTO.getEmail();
// this.password = userMarinaDTO.getPassword();
// this.customers = new ArrayList<>();
// this.marinas = new ArrayList<>();
// this.confirmPassword = userMarinaDTO.getConfirmPassword();
// this.appNotifications = new ArrayList<>();
// }

// public Integer getId() {
// return id;
// }

// public void setId(Integer id) {
// this.id = id;
// }

// public String getUsername() {
// return username;
// }

// public void setUsername(String username) {
// this.username = username;
// }

// public String getFirstName() {
// return firstName;
// }

// public void setFirstName(String firstName) {
// this.firstName = firstName;
// }

// public String getLastName() {
// return lastName;
// }

// public void setLastName(String lastName) {
// this.lastName = lastName;
// }

// public String returnFullName() {
// return this.firstName + " " + this.lastName;
// }

// public String getEmail() {
// return email;
// }

// public void setEmail(String email) {
// this.email = email;
// }

// public Marina getMarina() {
// return this.getMarinas().isEmpty()? null :
// this.getMarinas().get(0).getMarina();
// }

// public String getPassword() {
// return password;
// }

// public void setPassword(String password) {
// this.password = password;
// }

// public List<Customer> getCustomers() {
// return customers;
// }

// public void setCustomers(List<Customer> customers) {
// this.customers = customers;
// }

// public LocalDateTime getCreated() {
// return created;
// }

// public void setCreated(LocalDateTime created) {
// this.created = created;
// }

// public void setToken(String token) {
// this.token = token;
// }

// public String getToken() {
// return this.token;
// }

// public List<SlingConfig> getSlingsConfig() {
// return slingsConfig;
// }

// public void setSlingsConfig(List<SlingConfig> slingsConfig) {
// this.slingsConfig = slingsConfig;
// }

// public LocalDateTime getLastUserSling() {
// return lastUserSling;
// }

// public void setLastUserSling(LocalDateTime lastUserSling) {
// this.lastUserSling = lastUserSling;
// }

// public LocalDateTime getLastUserSlingApp() {
// return lastUserSlingApp;
// }

// public void setLastUserSlingApp(LocalDateTime lastUserSlingApp) {
// this.lastUserSlingApp = lastUserSlingApp;
// }

// public List<UserMarina> getMarinas() {
// return marinas!= null? marinas : new ArrayList<UserMarina>();
// }

// public void setMarinas(List<UserMarina> marinas) {
// this.marinas = marinas;
// }

// public String getFirebaseToken() {
// return firebaseToken;
// }

// public void setFirebaseToken(String firebaseToken) {
// this.firebaseToken = firebaseToken;
// }

// public LocalDateTime getLastDateToken() {
// return lastDateToken;
// }

// public void setLastDateToken(LocalDateTime lastDateToken) {
// this.lastDateToken = lastDateToken;
// }

// public String getConfirmPassword() {
// return confirmPassword;
// }

// public void setConfirmPassword(String confirmPassword) {
// this.confirmPassword = confirmPassword;
// }

// public String getRefreshToken() {
// return refreshToken;
// }

// public void setRefreshToken(String refreshToken) {
// this.refreshToken = refreshToken;
// }

// public void setAccessList(List<UserAccess> accessList) {
// this.accessList = accessList;
// }

// public Sailor getSailor() {
// return sailor;
// }

// public void setSailor(Sailor sailor) {
// this.sailor = sailor;
// }

// public List<AppNotification> getAppNotifications() {
// return appNotifications;
// }

// public void setAppNotifications(List<AppNotification> appNotifications) {
// this.appNotifications = appNotifications;
// }

// public Boolean getActive() {
// return active;
// }

// public void setActive(Boolean active) {
// this.active = active;
// }

// public Role getRoleId() {
// return roleId;
// }
// }
