package com.github.kernel1947.coffeecord.command.commands.superuser;

import com.github.kernel1947.coffeecord.command.Command;
import com.github.kernel1947.coffeecord.command.CommandContext;
import com.github.kernel1947.coffeecord.command.CommandType;
import com.github.kernel1947.coffeecord.command.ICommand;
import com.github.kernel1947.coffeecord.core.Util;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

@Command(type = CommandType.SUPERUSER)
public class ShutdownCommand implements ICommand {
	@Override
	public void execute(CommandContext ctx) {
		TextChannel channel = ctx.getChannel();
		User user = ctx.getAuthor();

		if(Util.isSuperuser(user)) {
			channel.sendMessage("Shutting down...").complete();
			System.exit(0);
		} else {
			channel.sendMessage("Not a Superuser...").queue();
		}
	}

	@Override
	public String getCommand() {
		return "shutdown";
	}

	@Override
	public String getHelp() {
		return "Shutdown Bot!\nSuperuser Command!";
	}
}
