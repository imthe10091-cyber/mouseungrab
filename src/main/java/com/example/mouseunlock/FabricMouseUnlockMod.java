package com.example.mouseunlock;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class FabricMouseUnlockMod implements ClientModInitializer {
    private static KeyBinding unlockKey;

    @Override
    public void onInitializeClient() {
        unlockKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.mouseunlock.unlock",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F6,
            "category.mouseunlock"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (unlockKey.wasPressed()) {
                if (client.mouse.isCursorLocked()) {
                    client.mouse.unlockCursor();
                } else {
                    client.mouse.lockCursor();
                }
            }
        });
    }
}
