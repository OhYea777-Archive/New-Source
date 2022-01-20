import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.plugin.java.JavaPlugin;

import com.ohyea777.solitarytools.api.IModule;

public class GuardTag implements IModule, Listener {

	@Override
	public String getName() {
		return "Guard Tag";
	}

	@Override
	public void init(JavaPlugin plugin) {
		plugin.getLogger().info("[" + getName() + "] Module Enabled!");
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@Override
	public void deInit(JavaPlugin plugin) {
		plugin.getLogger().info("[" + getName() + "] Module Disabled!");
		HandlerList.unregisterAll(this);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(ignoreCancelled = true)
	public void onPlayerHit(EntityDamageByEntityEvent event) {
		if (event.getEntityType().equals(EntityType.PLAYER)) {
			Player guard = (Player) event.getEntity();
			Player damager = null;
			if (event.getDamager().getType().equals(EntityType.PLAYER)) {
				damager = (Player) event.getDamager();
			} else if (event.getCause().equals(DamageCause.PROJECTILE) && event.getDamager() instanceof Projectile) {
				Projectile proj = (Projectile) event.getDamager();
				if (proj.getShooter() instanceof Player) {
					damager = (Player) proj.getShooter();
				}
			}
			
			if (damager != null) {
				if (guard.hasPermission("server.guardtag")) {
					if (!damager.hasPermission("server.guardtag.bypass")) {
						guard.sendMessage(_("&8[&bSolitary Tools&8] &7You Were Damaged by &3" + damager.getName()));
					}
				}
			}
		}
	}
	
	public String _(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return false;
	}

}
