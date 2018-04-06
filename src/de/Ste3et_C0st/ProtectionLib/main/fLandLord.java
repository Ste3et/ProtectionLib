package de.Ste3et_C0st.ProtectionLib.main;

import biz.princeps.landlord.api.LandLordAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import biz.princeps.landlord.util.OwnedLand;

public class fLandLord extends ProtectinObj {
    Player p;
    Location loc;

    public fLandLord(Plugin pl) {
        super(pl);
    }

    public boolean canBuild(Plugin p, Player player, Location loc) {
        this.p = player;
        this.loc = loc;
        return canBuild(p);
    }

    public boolean isOwner(Plugin p, Player player, Location loc) {
        this.p = player;
        this.loc = loc;
        return isOwner(p);
    }

    private boolean canBuild(Plugin p) {
        if (p == null) {
            return true;
        }
        OwnedLand land = getAPI().getLand(this.loc);
        if (land == null) return true;
        if (land.isOwner(this.p.getUniqueId())) {
            return true;
        }
        // Todo this might not work in every case, but fine for now
        WorldGuardPlugin wgp = WorldGuardPlugin.inst();
        if (land.getWGLand().isMember(wgp.wrapPlayer(this.p))) {
            return true;
        }
        return false;
    }

    private boolean isOwner(Plugin p) {
        if (p == null) {
            return true;
        }
        OwnedLand land = getAPI().getLand(this.loc);
        if (land == null) return true;
        if (land.isOwner(this.p.getUniqueId())) {
            return true;
        }
        return false;
    }

    private LandLordAPI getAPI() {
        return ((LandLordAPI) Bukkit.getPluginManager().getPlugin("Landlord"));
    }
}
