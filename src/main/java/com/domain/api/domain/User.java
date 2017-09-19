package com.domain.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mybatis.annotations.*;

import org.springframework.data.annotation.Transient;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static org.springframework.data.repository.query.parser.Part.Type.CONTAINING;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(table = "user")
@JsonIgnoreProperties("new")
@EqualsAndHashCode(callSuper=false)
public class User extends VersionId {

	@Conditions({
		@Condition,
		@Condition(properties = "fuzzyUsername", type = CONTAINING)
	})
	private String username;

	@JsonProperty(access = WRITE_ONLY)
	private String password;

	@Conditions({
			@Condition,
			@Condition(properties = "fuzzyFirstName", type = CONTAINING)
	})
	private String firstName;

	@Conditions({
			@Condition,
			@Condition(properties = "fuzzyLastName", type = CONTAINING)
	})
	private String lastName;

	@Conditions({
			@Condition,
			@Condition(properties = "fuzzyMiddleName", type = CONTAINING)
	})
	private String middleName;

	@Conditions({
			@Condition,
			@Condition(properties = "fuzzyEmail", type = CONTAINING)
	})
	private String email;

	@Conditions({
			@Condition,
			@Condition(properties = "fuzzyMobile", type = CONTAINING)
	})
	private String mobile;

	@Transient
	private List<Role> roleList;

//	@Override
//	public String getPassword() {
//		return this.password;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return getActive();
//	}
//
//	@Override
//	@JsonIgnore
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		for (Role role : this.roleList) {
//			authorities.add(new SimpleGrantedAuthority(role.toString()));
//		}
//		return authorities;
//	}
//
//	@Override
//	@JsonIgnore
//	public String getUsername() {
//		return this.username;
//	}
//
//	@Override
//	@JsonIgnore
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	@JsonIgnore
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	@JsonIgnore
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public void eraseCredentials() {
//		this.password = null;
//	}
}
