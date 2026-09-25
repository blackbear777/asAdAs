package com.tzarr.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class TzarrClient implements ClientModInitializer {

    public static KeyBinding toggleHud;
    public static KeyBinding resetCps;

    public static boolean hudEnabled = true;

    @Override
    public void onInitializeClient() {

        toggleHud = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.tzarr.toggle_hud",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_RIGHT_SHIFT,
                        "category.tzarr"
                )
        );

        resetCps = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.tzarr.reset_cps",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_R,
                        "category.tzarr"
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (toggleHud.wasPressed()) {
                hudEnabled = !hudEnabled;
            }

            while (resetCps.wasPressed()) {
                CpsTracker.reset();
            }
        });

        TzarrHud.init();
    }
}
