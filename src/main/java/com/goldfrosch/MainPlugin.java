package com.goldfrosch;

import com.goldfrosch.commands.Commands;
import com.goldfrosch.events.NewEvent;
import lombok.Getter;
import lombok.Setter;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class MainPlugin extends JavaPlugin implements Listener {
    private PluginDescriptionFile pdfFile = this.getDescription();
    private String pfName = pdfFile.getName() + " v" + pdfFile.getVersion();

    @Override
    public void onEnable(){
        //config 파일 있는지 파악 후 생성
        if (!getDataFolder().exists()) {
            getConfig().options().copyDefaults(true);
        }
        saveConfig();

        //Event Register
        registerEvent();

        //command
        Commands cmd = new Commands(this,"cmd");
        getCommand("cmd").setExecutor(cmd);
        getCommand("cmd").setTabCompleter(cmd);

        consoleLog(pfName+"이 활성화 되었습니다");
        super.onEnable();
    }

    @Override
    public void onDisable(){
        consoleLog(pfName+"이 비활성화 되었습니다");
        super.onDisable();
    }

    public void consoleLog(String msg){
        getLogger().info(msg);
    }

    public void registerEvent() {
        Bukkit.getPluginManager().registerEvents(new NewEvent(this),this);
    }
}
