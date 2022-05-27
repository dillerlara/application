package br.com.application.model.company;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class MenuItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)	
	private Integer Id;
	
	private String label;
	
	private String icon;
	
	private String routerLink;
	
	private String callFunction;
	
	private String tooltip;
	
	private String value;
	
	private String styleclass;
	
	private String tooltipValue;
	
	private String application;
	
	private String image;
	
	private Integer parent;

	private Integer order;
	
	private Integer level;


	private Integer permission;


	@OneToMany
	private List<MenuItem> children;

	public List<MenuItem> getChildren() {
		return children;
	}

	public void setChildren(List<MenuItem> list) {
		this.children = list;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	
	// @Convert(converter = SetMarinaMenuRuleToString.class)
	// private Set<MarinaMenuRule> marinaRule;
	
	// @JsonIgnore
	// public Set<Role> getRoles() {
	// 	return roles;
	// }
	// @JsonIgnore
	// public void setRoles(Set<Role> roles) {
	// 	this.roles = roles;
	// }
	// @JsonIgnore
	// @ManyToMany(mappedBy = "menuItens")	
	// private Set<Role> roles;
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	

	public String getTooltipValue() {
		return tooltipValue;
	}

	public void setTooltipValue(String tooltipValue) {
		this.tooltipValue = tooltipValue;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}
	
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	// public Set<MarinaMenuRule> getMarinaRule() {
	// 	return marinaRule;
	// }
	// public void setMarinaRule(Set<MarinaMenuRule> marinaRule) {
	// 	this.marinaRule = marinaRule;
	// }
	public String getStyleclass() {
		return styleclass;
	}
	public void setStyleclass(String styleclass) {
		this.styleclass = styleclass;
	}
	public String getRouterLink() {
		return routerLink;
	}
	public void setRouterLink(String routerLink) {
		this.routerLink = routerLink;
	}
	public String getCallFunction() {
		return callFunction;
	}
	public void setCallFunction(String callFunction) {
		this.callFunction = callFunction;
	}
	
	
}
