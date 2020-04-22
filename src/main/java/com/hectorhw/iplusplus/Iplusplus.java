package com.hectorhw.iplusplus;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public final class Iplusplus extends JavaPlugin {
    HashMap<UUID, Integer> storage = null;
    @Override
    public void onEnable() {
        // Plugin startup logic
        try{
            FileInputStream f = new FileInputStream("Iplusplus.dat");
            ObjectInputStream r = new ObjectInputStream(f);
            storage = (HashMap<UUID, Integer>)r.readObject(); // is it that safe?
            f.close();
            getLogger().info("successfully read database");
        } catch (FileNotFoundException e) {
            getLogger().warning("no file was found. Creating new database");
            storage = new HashMap<>();
        } catch (IOException e) {
            getLogger().warning("IO exception occured. Creating new database");
            storage = new HashMap<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new StickListener(storage), this);
        getLogger().info("registered event handler");
    }



    @Override
    public void onDisable() {
        try{
            FileOutputStream f = new FileOutputStream("Iplusplus.dat");
            ObjectOutputStream r = new ObjectOutputStream(f);
            r.writeObject(storage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
