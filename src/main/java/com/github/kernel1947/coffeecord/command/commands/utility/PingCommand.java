package com.github.kernel1947.coffeecord.command.commands.utility;

import com.github.kernel1947.coffeecord.command.CommandContext;
import com.github.kernel1947.coffeecord.command.ICommand;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class PingCommand implements ICommand {
	@Override
	public void execute(CommandContext ctx) {
		TextChannel channel = ctx.getChannel();
		channel.sendMessage("PONG! `" + ctx.getJDA().getGatewayPing() + "ms`").queue();
	}

	@Override
	public String getCommand() {
		return "ping";
	}

	@Override
	public String getHelp() {
		return "Get Gateway Ping";
	}
}
