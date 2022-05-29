package com.goldfrosch;

import com.goldfrosch.commands.Commands;
import com.goldfrosch.events.NewEvent;
import lombok.Getter;
import lombok.Setter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import static com.goldfrosch.utils.Constants.COMMAND_TITLE;
import static com.goldfrosch.utils.TwipUtils.DonateRegisterAction;

@Getter
@Setter
public final class TwipRoulette extends JavaPlugin {
    public static TwipRoulette plugin;
    private PluginDescriptionFile pdfFile = this.getDescription();
    private String pfName = pdfFile.getName() + " v" + pdfFile.getVersion();

    public static boolean gameStatus = false;

    public TwipRoulette() {
        plugin = this;
    }
    @Override
    public void onEnable(){
        //config 파일 있는지 파악 후 생성
        if (!getDataFolder().exists()) {
            getConfig().options().copyDefaults(true);
        }
        saveConfig();

        //트윕 연동
        DonateRegisterAction();

        //Event Register
        registerEvent();

        //command
        Commands cmd = new Commands(this,COMMAND_TITLE);
        getCommand(COMMAND_TITLE).setExecutor(cmd);
        getCommand(COMMAND_TITLE).setTabCompleter(cmd);

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

    public void reload() {
        reloadConfig();
    }

    public void registerEvent() {
        Bukkit.getPluginManager().registerEvents(new NewEvent(),this);
    }
}
