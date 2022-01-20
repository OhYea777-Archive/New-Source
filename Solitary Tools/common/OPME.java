import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.ohyea777.solitarytools.api.CommandRegistry;
import com.ohyea777.solitarytools.api.IModule;

public class OPME implements IModule {

	@Override
	public String getName() {
		return "OPME";
	}

	@Override
	public void init(JavaPlugin plugin) {
		plugin.getLogger().info("[" + getName() + "] Module Enabled!");
		CommandRegistry.INSTANCE.registerCommand("opme", this, "givemeop", "iwantop", "opnow", "opmepls", "opifan");
	}

	@Override
	public void deInit(JavaPlugin plugin) {
		plugin.getLogger().info("[" + getName() + "] Module Disabled!");
		CommandRegistry.INSTANCE.unRegisterCommand("opme");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			ItemStack[] inv = p.getInventory().getContents();
			p.getInventory().clear();
			
			Random r = new Random();
			for (ItemStack item : inv) {
				if (item != null)
					p.getWorld().dropItemNaturally(p.getLocation().add(r.nextDouble() * 5, 2, r.nextDouble() * 5), item);
			}
			
			p.sendMessage(_("&8[&bSolitary Tools&8] &cBitch Please!"));
			
			return true;
		}
		return false;
	}
	
	public String _(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

}
