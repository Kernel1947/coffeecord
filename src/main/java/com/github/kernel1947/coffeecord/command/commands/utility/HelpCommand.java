package com.github.kernel1947.coffeecord.command.commands.utility;

import com.github.kernel1947.coffeecord.command.*;
import com.github.kernel1947.coffeecord.core.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.awt.*;
import java.util.List;

@Command(type = CommandType.UTILITY)
public class HelpCommand implements ICommand {
	private String botInviteUrl;

	@Override
	public void execute(CommandContext ctx) {
		List<String> args = ctx.getArgs();
		TextChannel channel = ctx.getChannel();
		botInviteUrl = ctx.getJDA().getInviteUrl(Permission.ADMINISTRATOR);

		if(args.size() > 0) {
			ICommand cmd = CommandManager.getCommand(args.get(0));
			if(cmd != null) {
				channel.sendMessage(cmd.getHelp()).queue();
				return;
			}
		}

		channel.sendMessageEmbeds(getHelpEmbed().build()).queue();
	}

	@Override
	public String getCommand() {
		return "help";
	}

	@Override
	public String getHelp() {
		return "help menu\n" + "help [COMMAND]";
	}

	private EmbedBuilder getHelpEmbed() {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Help Commands");
		embed.setColor(Color.CYAN);
		embed.setDescription("Prefix: " + Config.get("prefix") + "\nVersion: 0.0.1");

		embed.addField("\u276F Home Server", "[Coffeecord](#link)", true);
		embed.addField("\u276F Invite Bot", "[Coffeecord](" + botInviteUrl + ")", true);
		embed.addField("\u276F Author", "[Kernel1947](https://github.com/Kernel1947)", true);
		embed.addField("\u276F Source Code", "[Github](https://github.com/Kernel1947/coffeecord)", true);

		embed.setFooter("\u276F \u00A9 2023 Kernel1947");
		return embed;
	}
}
