package Join;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		ConsoleCommandSender c = Bukkit.getConsoleSender();
		c.sendMessage("§8===================");
		c.sendMessage("§7Nome: §aMsJoin");
		c.sendMessage("§7Criador:§ayurirp4");
		c.sendMessage("§7Stats: §aAtivo");
		c.sendMessage("§7Versão: 1.0");
		c.sendMessage("§8===================");
		if (!new File(getDataFolder(), "config.yml").exists()){
			saveDefaultConfig();
		}
	}

	
	@Override
	public void onDisable() {
   ConsoleCommandSender c = Bukkit.getConsoleSender();
    c.sendMessage("§8===================");
	c.sendMessage("§7Nome: §cMsJoin");
    c.sendMessage("§7Criador:§cyurirp4");
	c.sendMessage("§7Stats: §cDesativado");
	c.sendMessage("§7Versão: §c1.0");
	c.sendMessage("§8===================");
	}
	
	@EventHandler
	public void joinchat(PlayerJoinEvent e){
		e.setJoinMessage(null);
		if (getConfig().getBoolean("JoinChat") == true){
		for (Player joinc : Bukkit.getOnlinePlayers()){
			joinc.sendMessage(getConfig().getString("Join_Chat").replace("&", "§").replace("%player%", e.getPlayer().getName()));
		}
		}
		if (getConfig().getBoolean("Joinactionbar") == true){
		for (Player Joina : Bukkit.getOnlinePlayers()){
			TitleAPI.sendActionBar(Joina, getConfig().getString("Join_Actionbar").replace("&", "§").replace("%player%", e.getPlayer().getName()));
		}
		}
		}
	@EventHandler 
	public void quit(PlayerQuitEvent e){
		if (getConfig().getBoolean("QuitChat") == true){
		for (Player quitc : Bukkit.getOnlinePlayers()){
			quitc.sendMessage(getConfig().getString("quit_Chat").replace("&", "§").replace("%player%", e.getPlayer().getName()));
		}
		}
		if (getConfig().getBoolean("Quitactionbar") == true){
		for (Player quita : Bukkit.getOnlinePlayers()){
			TitleAPI.sendActionBar(quita, getConfig().getString("quit_Actionbar").replace("&", "§").replace("%player%", e.getPlayer().getName()));
		}
	}
	}
	
	@EventHandler
	public void donojoin(PlayerJoinEvent e){
		if (getConfig().getBoolean("Staffs_Join_Chat") == true){
			if (!e.getPlayer().hasPermission("Owner.join")) {
				return;
			}
		for (Player owner : Bukkit.getOnlinePlayers()){
			owner.sendMessage(getConfig().getString("Owner_Join").replace("&", "§").replace("@owner", e.getPlayer().getName()));
		}
		}
		}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("msjoinrl")){
			if (!p.hasPermission("Join.reload")){
				p.sendMessage("§cyou do not have permission");
				return true;
			}
			reloadConfig();
			p.sendMessage("§eReloadedConfig");
		}
		
		return false;
	}
}
