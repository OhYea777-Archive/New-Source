package com.ohyea777.virtualvaults.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class VaultCommandRegistry {
	
	private static Map<String, VaultCommand> objects;
	
	static {
		objects = new HashMap<String, VaultCommand>();
	}
	
	public static void register(VaultCommand cmd) {
		for (String arg : cmd.getArgs()) {
			objects.put(cmd.getCommandLabel().toLowerCase() + ":" + arg.toLowerCase(), cmd);
		}
	}

	public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length > 0 && objects.containsKey(cmd.getName().toLowerCase() + ":" + args[0].toLowerCase())) {
			VaultCommand vaultCommand = objects.get(cmd.getName().toLowerCase() + ":" + args[0].toLowerCase());
			if (vaultCommand.hasPermission(sender)) {
				return vaultCommand.onCommand(sender, cmd, Arrays.copyOfRange(args, 1, args.length));
			} else {
				sender.sendMessage(vaultCommand.getNoPerm().get());
			}
		}
		
		return false;
	}
	
}
