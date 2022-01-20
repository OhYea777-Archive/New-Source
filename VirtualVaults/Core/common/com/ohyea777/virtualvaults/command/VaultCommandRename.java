package com.ohyea777.virtualvaults.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ohyea777.virtualvaults.inventory.Vault;
import com.ohyea777.virtualvaults.player.VaultUser;
import com.ohyea777.virtualvaults.util.IMessageGet;

public class VaultCommandRename extends VaultCommand {

	@Override
	public String getCommandLabel() {
		return "vault";
	}

	@Override
	public String[] getArgs() {
		return new String[] { "rename" };
	}
	
	@Override
	public String getPermission() {
		return VaultCommandDefaultMessages.RENAME_PERMISSION.getNoPrefix();
	}
	
	@Override
	public IMessageGet getNoPerm() {
		return VaultCommandDefaultMessages.RENAME_NO_PERM;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
		if (sender instanceof Player) {
			VaultUser p = getLoader().get((Player) sender);
			int id = 0;

			if (args.length == 2) {
				Vault vault = null;
				try {
					id = Integer.valueOf(args[0]) - 1;
					if (p.containsVault(id)) {
						vault = p.getVault(id);
					} else {
						sender.sendMessage(VaultCommandDefaultMessages.INVALID_VAULT.get());
						return true;
					}
				} catch (NumberFormatException e) {
					if (p.containsVault(args[0])) {
						vault = p.getVault(args[0]);
						id = p.getVaultIndex(args[0]);
					} else {
						sender.sendMessage(VaultCommandDefaultMessages.INVALID_VAULT.get());
						return true;
					}
				}

				if (!p.containsVault(args[1])) {
					String original = vault.setName(args[1]);
					sender.sendMessage(VaultCommandDefaultMessages.RENAMED.sub(original, vault));
					p.rename(id, args[1]);
					return true;
				} else {
					sender.sendMessage(VaultCommandDefaultMessages.VAULT_EXISTS.sub(vault));
					return true;
				}
			}
			sender.sendMessage(VaultCommandDefaultMessages.RENAME_HELP.getAsList());

		} else {
			sender.sendMessage(VaultCommandDefaultMessages.PLAYERS_ONLY.get());
			return true;
		}

		return false;
	}

}
