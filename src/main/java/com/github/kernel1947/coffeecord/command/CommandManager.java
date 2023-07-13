package com.github.kernel1947.coffeecord.command;

import com.github.kernel1947.coffeecord.core.Config;
import com.github.kernel1947.coffeecord.core.Logger;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.reflections.Reflections;

import java.util.*;
import java.util.regex.Pattern;

public class CommandManager {
	private static List<ICommand> commands = new ArrayList<>();

	public CommandManager() {
		try {
			var classes = new Reflections("com.github.kernel1947.coffeecord").getTypesAnnotatedWith(Command.class);
			for (var Class : classes) {
				ICommand instance = (ICommand) Class.getDeclaredConstructor().newInstance();
				addCommand(instance);
			}
		} catch (Exception e) {
			Logger.critical(e.getMessage());
		}
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

	public static ICommand getCommand(String command) {
		for(ICommand cmd: commands)
			if(cmd.getCommand().equals(command))
				return cmd;
		return null;
	}

	public static List<ICommand> getCommands() {
		return commands;
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
