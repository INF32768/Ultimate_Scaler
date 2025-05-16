package me.inf32768.ultimatescaler.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "ultimatescaler")
public class WorldGenOptions implements ConfigData {
    public double globalXScale = 1.0;
    public double globalZScale = 1.0;
    public double globalYScale = 1.0;
    public double globalXOffset = 0.0;
    public double globalZOffset = 0.0;
    public double globalYOffset = 0.0;
}
