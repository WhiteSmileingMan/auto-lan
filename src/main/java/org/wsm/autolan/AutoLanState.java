package org.wsm.autolan;

import org.jetbrains.annotations.Nullable;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.PersistentState;
import org.wsm.autolan.LanSettings;

public class AutoLanState extends PersistentState {
    public static final String AUTO_LAN_KEY = AutoLan.MODID;
    private static final String LAN_SETTINGS_KEY = "lanSettings";
    private static final String WHITELIST_ENABLED_KEY = "whitelistEnabled";

    @Nullable
    private LanSettings lanSettings;
    private boolean whitelistEnabled;

    public static PersistentState.Type<AutoLanState> getPersistentStateType() {
        return new PersistentState.Type<AutoLanState>(AutoLanState::new, AutoLanState::fromNbt, null);
    }

    private AutoLanState(@Nullable LanSettings lanSettings, boolean whitelistEnabled) {
        this.lanSettings = lanSettings;
        this.whitelistEnabled = whitelistEnabled;
    }

    public AutoLanState() {
        this.lanSettings = null;
        this.whitelistEnabled = false;
    }

    public static AutoLanState fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        NbtCompound lanSettingsNbt = nbt.getCompound(LAN_SETTINGS_KEY);
        return new AutoLanState(lanSettingsNbt.isEmpty() ? null : LanSettings.fromNbt(lanSettingsNbt),
                nbt.getBoolean(WHITELIST_ENABLED_KEY));
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        if (lanSettings != null) {
            NbtCompound lanSettingsNbt = new NbtCompound();
            lanSettings.writeNbt(lanSettingsNbt);
            nbt.put(LAN_SETTINGS_KEY, lanSettingsNbt);
        }
        nbt.putBoolean(WHITELIST_ENABLED_KEY, this.whitelistEnabled);
        return nbt;
    }

    @Nullable
    public LanSettings getLanSettings() {
        return this.lanSettings;
    }

    public boolean getWhitelistEnabled() {
        return this.whitelistEnabled;
    }

    public void setLanSettings(@Nullable LanSettings lanSettings) {
        this.lanSettings = lanSettings;
        this.markDirty();
    }

    public void setWhitelistEnabled(boolean whitelistEnabled) {
        this.whitelistEnabled = whitelistEnabled;
        this.markDirty();
    }
}