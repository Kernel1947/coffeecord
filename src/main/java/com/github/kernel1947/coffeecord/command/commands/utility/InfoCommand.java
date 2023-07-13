package com.github.kernel1947.coffeecord.command.commands.utility;

import com.github.kernel1947.coffeecord.command.Command;
import com.github.kernel1947.coffeecord.command.CommandContext;
import com.github.kernel1947.coffeecord.command.CommandType;
import com.github.kernel1947.coffeecord.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.awt.*;
import java.util.List;

@Command(type = CommandType.UTILITY)
public class InfoCommand implements ICommand {
	private int numberOfGuilds;
	private static String botInviteUrl;

	@Override
	public void execute(CommandContext ctx) {
		List<String> args = ctx.getArgs();
		TextChannel channel = ctx.getChannel();
		numberOfGuilds = ctx.getJDA().getGuilds().size();
		botInviteUrl = ctx.getJDA().getInviteUrl(Permission.ADMINISTRATOR);

		if(args.size() == 0) {
			channel.sendMessage(getHelp()).queue();
			return;
		}

		if(args.get(0).equals("author"))
			channel.sendMessageEmbeds(getAuthorInfoEmbed().build()).queue();
		else if(args.get(0).equals("bot"))
			channel.sendMessageEmbeds(getBotInfoEmbed().build()).queue();
		else
			channel.sendMessage(getHelp()).queue();
	}

	@Override
	public String getCommand() {
		return "info";
	}

	@Override
	public String getHelp() {
		return "Get Info about Author(Kernel) or Bot\ninfo [author/bot]\n";
	}

	private EmbedBuilder getAuthorInfoEmbed() {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Kernel Info");
		embed.setColor(Color.CYAN);

		embed.addField("\u276F Kernel Discord Server", "[Kernel](#link)", true);
		embed.addField("\u276F Kernel Github", "[Kernel](https://github.com/Kernel1947)", true);

		embed.setFooter("\u00A9 2023 Kernel");
		return embed;
	}

	private EmbedBuilder getBotInfoEmbed() {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Bot Info");
		embed.setColor(Color.CYAN);

		embed.addField("\u276F Home Server", "[CoffeeCord](#link)", true);
		embed.addField("\u276F Invite Bot", "[CoffeeCord](" + botInviteUrl + ")", true);
		embed.addField("\u276F Servers", Integer.toString(numberOfGuilds), true);
		embed.addField("\u276F Source Code", "[Github](https://github.com/Kernel1947/coffeecord)", true);
		embed.addField("\u276F Version", "0.0.1", true);
		embed.addField("\u276F Author", "Kernel", true);

		embed.setFooter("\u00A9 2023 Kernel");
		return embed;
	}
}
