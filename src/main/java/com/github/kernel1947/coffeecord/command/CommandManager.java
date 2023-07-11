package com.github.kernel1947.coffeecord.command;

import com.github.kernel1947.coffeecord.command.commands.utility.PingCommand;
import com.github.kernel1947.coffeecord.core.Config;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
	private static List<ICommand> commands = new ArrayList<>();

	public CommandManager() {
		// Utility Commands
		addCommand(new PingCommand());
	}

	public CommandManager(List<ICommand> commands) {
		this.commands = commands;
	}

	public void addCommand(ICommand command) {
		if(!hasCommand(command))
			commands.add(command);
	}

	public void removeCommand(ICommand command) {
		if(hasCommand(command))
			commands.remove(command);
	}

	private boolean hasCommand(ICommand command) {
		return getCommand(command.getCommand()) != null;
	}

	private ICommand getCommand(String command) {
		for(ICommand cmd: commands)
			if(cmd.getCommand().equals(command))
				return cmd;
		return null;
	}

	public void handle(MessageReceivedEvent event) {
		if(event.isFromGuild()) {
			String[] raw = event.getMessage().getContentRaw().replaceFirst("(?i)" + Pattern.quote(Config.get("prefix")), "").split("\\s+");
			String command = raw[0];
			ICommand cmd = getCommand(command);

			if(cmd != null) {
//            event.getChannel().sendTyping().queue();
				List<String> args = Arrays.asList(raw).subList(1, raw.length);
				cmd.execute(new CommandContext(event, args));
			}
		}
	}
}
