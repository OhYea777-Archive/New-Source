package com.ohyea777.virtualvaults.command;

import static com.ohyea777.virtualvaults.util.VaultMessages.PREFIX;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ohyea777.virtualvaults.VirtualVaults;
import com.ohyea777.virtualvaults.inventory.Vault;
import com.ohyea777.virtualvaults.util.IMessageGet;
import com.ohyea777.virtualvaults.util.VaultLoader;
import com.ohyea777.virtualvaults.util.VaultMessages;

public abstract class VaultCommand {
	
	public abstract String getCommandLabel();

	public abstract String[] getArgs();
	
	public IMessageGet getNoPerm() {
		return VaultMessages.NO_PERM;
	}
	
	public String getPermission() {
		return "";
	}
	
	public boolean hasPermission(CommandSender sender) {
		return getPermission() == null || getPermission().isEmpty() || sender.hasPermission(getPermission());
	}
	
	public abstract boolean onCommand(CommandSender sender, Command cmd, String[] args);
	
	public VaultLoader getLoader() {
		return VirtualVaults.getVaultsInstance().getLoader();
	}
	
	public enum VaultCommandDefaultMessages implements IMessageGet {

		DEF_PERMISSION("Permissions.Commands.Def", "virtualvaults.default"),
		DEF_NO_PERM("Commands.Def.NoPerm", "&cYou Do Not Have Permission to Set Your Default Vault!"),
		PLAYERS_ONLY("Commands.Def.PlayersOnly", "&cOnly Players Can Set a Default Vault!"),
		HELP_DEF("Commands.Def.Help", "{0} Default Vault Help&8:", "&8-&6] &7To Set a Vault to Default Use &8/vault def <id>", "&8-&6] &7or Use &8/vault def <name>" ),
		INVALID_VAULT("Commands.Def.InvalidVault", "&cInvalid Vault!"),
		DEFAULTED("Commands.Def.Defaulted", "Your Vault&8: &6{0}&7 - Set to Default!"),
		RENAME_PERMISSION("Permissions.Commands.Rename", "virtualvaults.rename"),
		RENAME_NO_PERM("Commands.Rename.NoPerm", "&cYou Do Not Have Permission to Rename Your Vaults!"),
		VAULT_EXISTS("Commands.Rename.Exists", "&cYour Vault&8: &6{0}&c - Already Exists!"),
		RENAMED("Commands.Rename.Renamed", "Your Vault&8: &6{0}&7 - Has Been Renamed to&8: &6{1}"),
		RENAME_HELP("Commands.Rename.Help", "{0} Rename Vault Help&8:", "&8-&6] &7To Rename a Vault Use &8/vault rename <id> <new_name>", "&8-&6] &7or Use &8/vault rename <name> <new_name>");

		private String loc;
		private String[] def;

		private VaultCommandDefaultMessages(String loc, String... def) {
			this.loc = loc;
			this.def = def;
		}
		
		public String sub(Object... args) {
			String s = get();
			for (int i = 0; i < args.length; i ++) {
				s = s.replace("{" + i + "}", match(args[i]));
			}
			return _(s);
		}
		
		public String match(Object o) {
			if (o instanceof Vault) {
				return ((Vault) o).getName();
			} else if (o instanceof World) {
				return ((World) o).getName();
			} else if (o instanceof Player) {
				return ((Player) o).getName();
			} else {
				return o.toString();
			}
		}

		public String get() {
			return _(PREFIX.getNoPrefixColoured() + (VirtualVaults.getVaultsInstance().getConfig().getString(loc) != null ? VirtualVaults.getVaultsInstance().getConfig().getString(loc) : def[0])); 
		}

		public String getNoPrefix() {
			return ChatColor.stripColor(_((VirtualVaults.getVaultsInstance().getConfig().getString(loc) != null ? VirtualVaults.getVaultsInstance().getConfig().getString(loc) : def[0])));
		}
		
		public String[] getAsList() {
			String[] list = def;
			
			if (VirtualVaults.getVaultsInstance().getConfig().getStringList(loc) != null)
				def = VirtualVaults.getVaultsInstance().getConfig().getStringList(loc).toArray(new String[VirtualVaults.getVaultsInstance().getConfig().getStringList(loc).size()]);
			
			for (int i = 0; i < list.length; i ++) {
				String s = def[i];
				list[i] = _(s.replace("{0}", PREFIX.getNoPrefixColoured().replace(" ", "")));
			}
			return list;
		}

		public String _(String s) {
			return ChatColor.translateAlternateColorCodes('&', s);
		}

	}
	
}
