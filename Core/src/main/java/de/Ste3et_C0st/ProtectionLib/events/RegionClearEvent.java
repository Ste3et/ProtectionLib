package de.Ste3et_C0st.ProtectionLib.events;


import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class RegionClearEvent extends Event implements Cancellable{
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private Location loc1, loc2;
    private UUID uuid;
    private boolean clear = true;
    
    @Override public HandlerList getHandlers() {return handlers; }
    @Override public boolean isCancelled() {return cancelled;}
    @Override public void setCancelled(boolean cancelled) { this.cancelled = cancelled;}
    public static HandlerList getHandlerList() {return handlers;}
    
    public RegionClearEvent(Location loc1, Location loc2) {
    	this.loc1 = loc1;
    	this.loc2 = loc2;
    }
    
    public void setClear(boolean b){this.clear = b;}
    public void setUUID(UUID uuid){this.uuid = uuid;}
    public boolean isClear(){return this.clear;}
    public UUID getUUID(){return this.uuid;}
    

    public Location getLoc1(){return this.loc1;}
    public Location getLoc2(){return this.loc2;}
}