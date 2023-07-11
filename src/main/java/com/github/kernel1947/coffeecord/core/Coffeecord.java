package com.github.kernel1947.coffeecord.core;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Coffeecord {

	public static void run(String token) throws LoginException {
		JDABuilder.createLight(token, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES)
				.setActivity(Activity.listening("Coffee..."))
				.addEventListeners(new Listener())
				.build();
	}

	public static void main(String[] args) {
		try {
			String token = Config.get("token");
			run(token);
		} catch (Exception e) {
			Logger.critical(e.getMessage());
		}
	}
}
