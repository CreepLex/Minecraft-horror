package com.creepLex.nightmare;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("nightmare")
public class NightmareMod {

    public static final ResourceKey<Level> NIGHTMARE_DIMENSION =
            ResourceKey.create(
                    Registries.DIMENSION,
                    new ResourceLocation("nightmare", "nightmare")
            );

    public NightmareMod() {
        System.out.println("The Nightmare mod elindult!");
    }

    @Mod.EventBusSubscriber(modid = "nightmare")
    public static class Events {

        @SubscribeEvent
        public static void onPlayerWakeUp(PlayerWakeUpEvent event) {
            if (!(event.getEntity() instanceof ServerPlayer player)) {
                return;
            }

            ServerLevel nightmareLevel =
                    player.server.getLevel(NIGHTMARE_DIMENSION);

            if (nightmareLevel == null) {
                return;
            }

            player.teleportTo(
                    nightmareLevel,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    player.getYRot(),
                    player.getXRot()
            );
        }
    }
}
