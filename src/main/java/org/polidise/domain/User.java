package org.polidise.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class User implements UserDetails, CredentialsContainer {

	private String password;
	private String username;
	private final Set<GrantedAuthority> authorities;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.authorities = new HashSet<>();
		this.authorities.add(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public void eraseCredentials() {
		password = null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public static User getDummyUser() {
		return new User("caline", "password");
	}

	public static User getDummyAdmin() {
		return new User("admin", "password");
	}
}
