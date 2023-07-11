package com.github.kernel1947.coffeecord.core;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {
	@Override
	public void onReady(ReadyEvent event) {
		Logger.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
	}
}
