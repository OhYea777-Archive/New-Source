import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.ohyea777.solitarytools.api.IModule;

public class FuckTheRain implements IModule, Listener {

	@Override
	public String getName() {
		return "FuckTheRain";
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

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return false;
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		if (event.toWeatherState()) {
			event.setCancelled(true);
		}
	}

}
