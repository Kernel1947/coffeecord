package com.github.kernel1947.coffeecord.core;

import net.dv8tion.jda.api.entities.User;

public class Util {
	public static boolean isSuperuser(User user) {
		return user.getId().equals(Config.get("SUPERUSER_UID"));
	}
}
