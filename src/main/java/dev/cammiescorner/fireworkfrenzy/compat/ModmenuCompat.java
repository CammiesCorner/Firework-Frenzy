package dev.cammiescorner.fireworkfrenzy.compat;

import com.teamresourceful.resourcefulconfig.client.ConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import dev.cammiescorner.fireworkfrenzy.FireworkFrenzyConfig;
import org.jetbrains.annotations.Nullable;

public class ModmenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            @Nullable var config = FireworkFrenzy.CONFIGURATOR.getConfig(FireworkFrenzyConfig.class);

            return config != null ? new ConfigScreen(null, config) : null;
        };
    }
}
