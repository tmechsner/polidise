package org.polidise.controller.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewUserCmd {

	public String username;
	public String passwordHash;
	public String emailAddress;

}
