package de.Ste3et_C0st.ProtectionLib.main.plugins;

import biz.princeps.landlord.api.ILandLord;
import biz.princeps.landlord.api.IOwnedLand;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fLandLord extends protectionObj {

    public fLandLord(Plugin pl) {
        super(pl);
    }

    public boolean canBuild(Player player, Location loc) {
        if (getPlugin() == null) {
            return true;
        }
        ILandLord plugin = (ILandLord) getPlugin();
        IOwnedLand land = plugin.getWGManager().getRegion(loc);
        if (land == null) return true;
        if (land.isOwner(player.getUniqueId())) {
            return true;
        }
        return land.isFriend(player.getUniqueId());
    }

    public boolean isOwner(Player player, Location loc) {
        if (getPlugin() == null) {
            return true;
        }
        ILandLord plugin = (ILandLord) getPlugin();
        IOwnedLand land = plugin.getWGManager().getRegion(loc);
        if (land == null) return true;
        return land.isOwner(player.getUniqueId());
    }
}
