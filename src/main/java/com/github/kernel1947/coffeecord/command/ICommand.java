package com.github.kernel1947.coffeecord.command;

public interface ICommand {
	void execute(CommandContext ctx);
	String getCommand();
	String getHelp();
}
