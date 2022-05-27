package br.com.application.model.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.application.enums.Application;
import br.com.application.enums.user.AppNotificationType;
import br.com.application.utils.SetAppNotificationToString;


@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "origin" }) })
public class AppNotification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@ManyToOne
	User user;

	@Enumerated(EnumType.STRING)
	Application origin;

	@Convert(converter = SetAppNotificationToString.class)
	private Set<AppNotificationType> notifications = new HashSet<>();

	@OneToMany(mappedBy = "appNotification", fetch = FetchType.EAGER)
	@OrderColumn(name="id")
	private List<AppNotificationToken> tokens = new ArrayList<>();

	private boolean isConfigurated = false;
	
    public AppNotification() {
		super();
		this.notifications = new HashSet<>();
		this.tokens = new ArrayList<>();
	}
    
    public AppNotification(User user, Application origin) {
		super();
		this.user = user;
		this.origin = origin;
		this.isConfigurated = false;
		this.notifications = new HashSet<>();
		this.tokens = new ArrayList<>();
	}
	
    public AppNotification(Integer id, User user, Application origin, boolean isConfigurated,
    		List<AppNotificationToken> tokens, Set<AppNotificationType> notifications) {
		super();
		this.id = id;
		this.user = user;
		this.origin = origin;
		this.isConfigurated = isConfigurated;
		this.tokens = tokens;
		this.notifications = notifications;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Application getOrigin() {
		return origin;
	}

	public void setOrigin(Application origin) {
		this.origin = origin;
	}

	public Set<AppNotificationType> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<AppNotificationType> notifications) {
		this.notifications = notifications;
	}

	public List<AppNotificationToken> getTokens() {
		return tokens.stream().filter(appToken -> appToken != null).collect(Collectors.toList());
	}

	public void setTokens(List<AppNotificationToken> tokens) {
		this.tokens = tokens;
	}

	public boolean isConfigurated() {
		return isConfigurated;
	}

	public void setConfigurated(boolean isConfigurated) {
		this.isConfigurated = isConfigurated;
	}
}
