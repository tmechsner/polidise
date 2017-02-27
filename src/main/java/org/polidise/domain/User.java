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

	private String passwordHash;
	private String username;
	private boolean isActive;
	private final Set<GrantedAuthority> authorities;

	public User(String username, String passwordHash, Boolean isActive) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.isActive = isActive==null?false:isActive;
		this.authorities = new HashSet<>();
		this.authorities.add(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public void eraseCredentials() {
		passwordHash = null;
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
		return isActive;
	}

	@Override
	public String getPassword() {
		return getPasswordHash();
	}

	@Override public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

}
