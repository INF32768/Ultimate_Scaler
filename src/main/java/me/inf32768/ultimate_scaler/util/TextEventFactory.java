package me.inf32768.ultimate_scaler.util;

import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TextEventFactory {
    private static final Constructor<?> CLICK_EVENT_COPY_TO_CLIPBOARD_CONSTRUCTOR;
    private static final Constructor<?> HOVER_EVENT_SHOW_TEXT_CONSTRUCTOR;
    private static final String VERSION_THRESHOLD = "1.21.5";


    public static ClickEvent createCopyEvent(String text) {
        if (VersionHelper.isVersionAtLeast(VERSION_THRESHOLD)) {
            try {
                return (ClickEvent) CLICK_EVENT_COPY_TO_CLIPBOARD_CONSTRUCTOR.newInstance(text);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                return (ClickEvent) CLICK_EVENT_COPY_TO_CLIPBOARD_CONSTRUCTOR.newInstance(ClickEvent.Action.COPY_TO_CLIPBOARD, text);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static HoverEvent createShowTextEvent(Text text) {
        if (VersionHelper.isVersionAtLeast(VERSION_THRESHOLD)) {
            try {
                return (HoverEvent) HOVER_EVENT_SHOW_TEXT_CONSTRUCTOR.newInstance(text);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                return (HoverEvent) HOVER_EVENT_SHOW_TEXT_CONSTRUCTOR.newInstance(HoverEvent.Action.SHOW_TEXT, text);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static {
        if (VersionHelper.isVersionAtLeast(VERSION_THRESHOLD)) {
            try {
                CLICK_EVENT_COPY_TO_CLIPBOARD_CONSTRUCTOR = Class.forName(ClickEvent.CopyToClipboard.class.getName()).getConstructor(String.class);
                HOVER_EVENT_SHOW_TEXT_CONSTRUCTOR = Class.forName(HoverEvent.ShowText.class.getName()).getConstructor(Text.class);
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                CLICK_EVENT_COPY_TO_CLIPBOARD_CONSTRUCTOR = Class.forName(ClickEvent.class.getName()).getConstructor(ClickEvent.Action.class, String.class);
                HOVER_EVENT_SHOW_TEXT_CONSTRUCTOR = Class.forName(HoverEvent.class.getName()).getConstructor(HoverEvent.Action.class, Object.class);
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
