package com.ohyea777.virtualvaults.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ohyea777.virtualvaults.player.VaultUser;
import com.ohyea777.virtualvaults.util.IMessageGet;

public class VaultCommandDefault extends VaultCommand {

	@Override
	public String getCommandLabel() {
		return "vault";
	}

	@Override
	public String[] getArgs() {
		return new String[] { "def", "default", "d", "setdefault", "setd", "setdef", "set" };
	}
	
	@Override
	public String getPermission() {
		return VaultCommandDefaultMessages.DEF_PERMISSION.getNoPrefix();
	}
	
	@Override
	public IMessageGet getNoPerm() {
		return VaultCommandDefaultMessages.DEF_NO_PERM;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
		if (sender instanceof Player) {
			VaultUser p = getLoader().get((Player) sender);
			
			if (args.length == 1) {
				try {
					int id = Integer.valueOf(args[0]) - 1;
					if (p.containsVault(id)) {
						sender.sendMessage(VaultCommandDefaultMessages.DEFAULTED.sub(p.setDefaultVault(id)));
					} else {
						sender.sendMessage(VaultCommandDefaultMessages.INVALID_VAULT.get());
					}
					return true;
				} catch (NumberFormatException e) {
					if (p.containsVault(args[0])) {
						sender.sendMessage(VaultCommandDefaultMessages.DEFAULTED.sub(p.setDefaultVault(args[0])));
					} else {
						sender.sendMessage(VaultCommandDefaultMessages.INVALID_VAULT.get());
					}
					return true;
				}
			}
			sender.sendMessage(VaultCommandDefaultMessages.HELP_DEF.getAsList());
		} else {
			sender.sendMessage(VaultCommandDefaultMessages.PLAYERS_ONLY.get());
			return true;
		}

		return false;
	}

}
