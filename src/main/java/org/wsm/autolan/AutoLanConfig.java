package org.wsm.autolan;

import org.jetbrains.annotations.Nullable;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = AutoLan.MODID)
public class AutoLanConfig implements ConfigData {
    public String ngrokAuthtoken = "";

    @ConfigEntry.Gui.Excluded
    @Nullable
    public LanSettings lanSettings = null;

    public void setLanSettings(@Nullable LanSettings lanSettings) {
        this.lanSettings = lanSettings;
        AutoLan.CONFIG.save();
    }
}
