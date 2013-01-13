package me.tom.LFT;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class LFT extends JavaPlugin implements Listener {

	public Logger log;
	public final Logger logger = Logger.getLogger("Minecraft");
	
	private String donateURL = ChatColor.RED + "http://echo-gaming.net/premium";
	public String donatePlox = ChatColor.YELLOW + "Sorry, you can't do that!\n" + donateURL;
	
	public void onEnable() {
		this.log = getLogger();
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + pdfFile.getVersion()
				+ " has been Enabled");
		getServer().getPluginManager().registerEvents(this, this);
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + pdfFile.getVersion()
				+ " has been Disabled");
	}

	// TNT and lava _block_
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {

		Material placedBlock = event.getBlockPlaced().getType();

		if (placedBlock.equals(Material.TNT)) {
			if (event.getPlayer().hasPermission("TNT.place")) {
				this.log.info(event.getPlayer().getName()
						+ " placed a TNT block!");
			} else {
				event.setCancelled(true);
				this.log.info(event.getPlayer().getName()
						+ " has no permissions to place a TNT block!");
				Player p = event.getPlayer();
				p.sendMessage(donatePlox);
			}
		}

		if (placedBlock.equals(Material.LAVA)
				|| placedBlock.equals(Material.STATIONARY_LAVA)) {
			if (event.getPlayer().hasPermission("lava.place")) {
				this.log.info(event.getPlayer().getName() + " placed Lava!");
			} else {
				event.setCancelled(true);
				this.log.info(event.getPlayer().getName()
						+ " has no permissions to place Lava!");
				Player p = event.getPlayer();
				p.sendMessage(donatePlox);
			}
		}
	}

	
	// Lava bucket
	@EventHandler
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
		if (!(event.getPlayer().hasPermission("lava.place")) && (event.getPlayer().getItemInHand().getTypeId() == 327)) {
			event.setCancelled(true);
			this.log.info(event.getPlayer().getName() + " has no permissions to use lava [bucket]!");
			event.getPlayer().sendMessage(donatePlox);
			
		}
		
		
	}
	
	// Igniting shit
	@EventHandler
	public void onBlockIgnite(BlockIgniteEvent event) {
		if ((event.getPlayer() instanceof Player) && (!event.getPlayer().hasPermission("fire.place"))) {
			event.setCancelled(true);
			this.log.info(event.getPlayer().getName() + " has no permissions to place fire!");
			event.getPlayer().sendMessage(donatePlox);
		}
	}

}
