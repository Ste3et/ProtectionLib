package de.Ste3et_C0st.ProtectionLib.main;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class command implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("protectionlib")) {
			String info = "ProtectionLib §a" + ProtectionLib.getInstance().getDescription().getVersion()
					+ "\n§fAuthor §e" + ProtectionLib.getInstance().getDescription().getAuthors().get(0)
					+ "\n§f/protectionlib §aplugins" 
					+ "\n§f/protectionlib §adebug"
					+ "\n§f/protectionlib §adebug <player>"
					+ "\n§f/protectionlib §atest (build/owner) <player/me>"
					+ "\n§f/protectionlib §areload";
			
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("plugins")) {
					if(!ProtectionLib.getInstance().getWatchers().isEmpty()) {
						ProtectionLib.getInstance().getWatchers().stream().forEach(plugin -> {
							if(plugin.isEnabled()) {
								sender.sendMessage("§f- " + plugin.getPlugin().getName() + " §b" + plugin.getPlugin().getDescription().getVersion() + " §f-> §a" + plugin.getClass().getSimpleName() + ".class");
							}else {
								sender.sendMessage("§f- " + plugin.getPlugin().getName() + " §c" + plugin.getPlugin().getDescription().getVersion() + " §f-> " + "§cdisabled!");
							}
						});
					}else {
						sender.sendMessage("§c§lProtectionLib is not hooked to any Plugin !");
					}
					return true;
				}else if(args[0].equalsIgnoreCase("reload")) {
					sender.sendMessage("Remove all registered Protection Plugins");
					ProtectionLib.getInstance().clearWatchers();
					ProtectionLib.getInstance().reloadConfig();
					sender.sendMessage("Hook into all avaiable Protection Plugins");
					ProtectionLib.getInstance().addWatchers();
					if(!ProtectionLib.getInstance().getWatchers().isEmpty()) {
						ProtectionLib.getInstance().getWatchers().stream().forEach(plugin -> {
							if(plugin.isEnabled()) {
								sender.sendMessage("§f- " + plugin.getPlugin().getName() + " §b" + plugin.getPlugin().getDescription().getVersion());
							}else {
								sender.sendMessage("§f- " + plugin.getPlugin().getName() + " §cdisabled!");
							}
						});
					}else {
						sender.sendMessage("§c§lProtectionLib is not hooked to any Plugin !");
					}
					return true;
				}else if(args[0].equalsIgnoreCase("debug")) {
					if(sender instanceof Player) {
						Player p = (Player) sender;
						boolean b = ProtectionLib.getInstance().toogleDebug(p.getUniqueId());
						String a = b ? p.getName() + " is now in the debug mode" : p.getName() + " is no more in the debug mode";
						p.sendMessage(a);
						return true;
					}
				}else if(args[0].equalsIgnoreCase("test")) {
					Player onlinePlayer = (Player) sender;
					boolean checkbuild = true, checkOwner = true;
					if(Objects.isNull(onlinePlayer)) {
						sender.sendMessage("§cNo player is selected");
					}else {
						Player senderPlayer = Player.class.cast(sender);
						boolean tempAdd = ProtectionLib.getInstance().getDebugList().contains(senderPlayer.getUniqueId());
						if(tempAdd == false) ProtectionLib.getInstance().getDebugList().add(senderPlayer.getUniqueId());
						
						if(checkbuild) ProtectionLib.getInstance().canBuild(onlinePlayer.getLocation(), onlinePlayer, senderPlayer);
						if(checkOwner) ProtectionLib.getInstance().isOwner(onlinePlayer.getLocation(), onlinePlayer, senderPlayer);
						
						senderPlayer.sendMessage("test");
						
						if(tempAdd == false) ProtectionLib.getInstance().getDebugList().remove(senderPlayer.getUniqueId());
					}
					return true;
				}
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("debug")) {
					Player p = Bukkit.getPlayer(args[1]);
					if(p==null || !p.isOnline()) {
						sender.sendMessage("§c" + p.getName() + " is not Online !");
						return true;
					}
					boolean b = ProtectionLib.getInstance().toogleDebug(p.getUniqueId());
					String a = b ? p.getName() + " is now in the debug mode" : p.getName() + " is no more in the debug mode";
					sender.sendMessage(a);
					return true;
				}else if(args[0].equalsIgnoreCase("test")) {
					Player onlinePlayer = null;
					boolean checkbuild = true, checkOwner = true;
					for(String stringarg : args) {
						if(stringarg.equalsIgnoreCase("build")) {
							checkOwner = false;
						}else if(stringarg.equalsIgnoreCase("owner")){
							checkbuild = false;
						}else if(stringarg.equalsIgnoreCase("me")) {
							onlinePlayer = (Player) sender;
						}else {
							onlinePlayer = Bukkit.getPlayerExact(stringarg);
						}
					}
					if(Objects.isNull(onlinePlayer)) {
						sender.sendMessage("§cNo player is selected");
					}else {
						Player senderPlayer = Player.class.cast(sender);
						boolean tempAdd = ProtectionLib.getInstance().getDebugList().contains(senderPlayer.getUniqueId());
						if(tempAdd == false) ProtectionLib.getInstance().getDebugList().add(senderPlayer.getUniqueId());
						
						if(checkbuild) ProtectionLib.getInstance().canBuild(onlinePlayer.getLocation(), onlinePlayer, senderPlayer);
						if(checkOwner) ProtectionLib.getInstance().isOwner(onlinePlayer.getLocation(), onlinePlayer, senderPlayer);
						
						if(tempAdd == false) ProtectionLib.getInstance().getDebugList().remove(senderPlayer.getUniqueId());
					}
					return true;
				}
			}else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("test")) {
					Player onlinePlayer = null;
					boolean checkbuild = true, checkOwner = true;
					for(String stringarg : args) {
						if(stringarg.equalsIgnoreCase("build")) {
							checkOwner = false;
						}else if(stringarg.equalsIgnoreCase("owner")){
							checkbuild = false;
						}else if(stringarg.equalsIgnoreCase("me")) {
							onlinePlayer = (Player) sender;
						}else {
							onlinePlayer = Bukkit.getPlayerExact(stringarg);
						}
					}
					if(Objects.isNull(onlinePlayer)) {
						sender.sendMessage("§cNo player is selected");
					}else {
						Player senderPlayer = Player.class.cast(sender);
						boolean tempAdd = ProtectionLib.getInstance().getDebugList().contains(senderPlayer.getUniqueId());
						if(tempAdd == false) ProtectionLib.getInstance().getDebugList().add(senderPlayer.getUniqueId());
						
						if(checkbuild) ProtectionLib.getInstance().canBuild(onlinePlayer.getLocation(), onlinePlayer, senderPlayer);
						if(checkOwner) ProtectionLib.getInstance().isOwner(onlinePlayer.getLocation(), onlinePlayer, senderPlayer);
						
						if(tempAdd == false) ProtectionLib.getInstance().getDebugList().remove(senderPlayer.getUniqueId());
					}
					return true;
				}
			}
			sender.sendMessage(info);
			return true;
		}
		return false;
	}

}
