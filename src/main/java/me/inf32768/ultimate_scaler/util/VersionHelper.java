package me.inf32768.ultimate_scaler.util;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.game.minecraft.McVersionLookup;

public class VersionHelper {
    private VersionHelper() {}

    public static final String CURRENT_VERSION = McVersionLookup.getRelease(FabricLoader.getInstance().getRawGameVersion());

    public static boolean isVersion(String version) {
        return CURRENT_VERSION.equals(version);
    }

    public static boolean isVersionAtLeast(String version) {
        return isVersionAtLeast(CURRENT_VERSION, version);
    }

    public static boolean isVersionAtLeast(String compareVersion, String version) {
        return compareVersions(compareVersion, version) >= 0;
    }

    public static boolean isVersionBelow(String version, String compareVersion) {
        return compareVersions(compareVersion, version) > 0;
    }

    public static boolean isVersionBelow(String version) {
        return isVersionBelow(CURRENT_VERSION, version);
    }

    public static boolean isVersionInRange(String minVersion, String maxVersion) {
        return isVersionInRange(minVersion, maxVersion, CURRENT_VERSION);
    }

    public static boolean isVersionInRange(String minVersion, String maxVersion,String compareVersion) {
        return compareVersions(minVersion, compareVersion) <= 0 && compareVersions(compareVersion, maxVersion) <= 0;
    }

    public static int compareVersions(String version1, String version2) {
        String[] version1Array = parseVersion(version1);
        String[] version2Array = parseVersion(version2);
        for (int i = 0; i < 3; i++) {
            int compare = Integer.compare(Integer.parseInt(version1Array[i]), Integer.parseInt(version2Array[i]));
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    public static String[] parseVersion(String version) {
        String[] split = version.split("\\.");
        if (split.length == 2) {
            String[] parsed = new String[3];
            parsed[0] = split[0];
            parsed[1] = split[1];
            parsed[2] = "0";
            return parsed;
        }
        return split;
    }
}
