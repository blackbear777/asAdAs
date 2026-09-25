package com.tzarr.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public final class TzarrHud {

    private TzarrHud() {}

    public static void init() {

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {

            if (!TzarrClient.hudEnabled) {
                return;
            }

            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null) {
                return;
            }

            int x = 8;
            int y = 8;

            drawContext.drawTextWithShadow(
                    client.textRenderer,
                    Text.literal("TZARR CLIENT"),
                    x,
                    y,
                    0xFFFFFF
            );

            drawContext.drawTextWithShadow(
                    client.textRenderer,
                    Text.literal("CPS: " + CpsTracker.getCps()),
                    x,
                    y + 12,
                    0xFFFFFF
            );
        });
    }
}
