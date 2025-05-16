# Ultimate Scaler
**English** | [简体中文](README_CN.md)  

A Minecraft mod for scale the terrain generation.

[![](https://z3.ax1x.com/2021/08/02/fpgDCq.png)](https://www.curseforge.com/minecraft/mc-mods/fabric-api) [![](https://z3.ax1x.com/2021/08/02/fpgr80.png)](https://www.curseforge.com/minecraft/mc-mods/cloth-config)
![](https://s21.ax1x.com/2025/05/16/pEjzueU.png)
## Settings

Use _ModMenu_ to access the settings.  

* `Global X/Z/Y Scale / Offset`: Scale or offset most terrain generation.  

    * This option currently only affects Noise and OldBlendedNoise.  

    * It will be able to affect almost entire terrain generation in the future.  

## Debug HUD

* Added a line `TerrainXYZ` to the debug HUD to show the current position of terrain generation.    
![](https://s21.ax1x.com/2025/05/05/pEq1jsg.png)

## Commands

* `locate pos`: Use dichotomies to locate a location that has been scaled and offset.  
    * Syntax: `/locate pos <originalPos> <scale> <offset> [range]`  
    * `<originalPos>`: int/String, The original position on one axis.  
    * `<scale>`: double, The scale factor, must be greater than 0.  
    * `<offset>`: double, The offset.  
    * `[range]`: double, The range of the search. Default is `Double.MAX_VALUE / scale - offset`.  
    * Example: A special terrain generates at 607949781904244613165613056 on X/Z axis when there is no offset nor scale, I would like to know where this terrain is generated when the scale of the X axis is 4.5E8 and the offset is 607949781904244603165613056, I can use `/locate pos 607949781904244613165613056 450000000 607949781904244603165613056` to get the new position. This command outputs 77, meaning this terrain is generated at 77 on X axis when scaled and offset.  
    * This command is not useful when the coordinate is small, but it is useful when the coordinate is very large and affected by precision loss.  