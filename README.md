# Ultimate Scaler
**English** | [简体中文](README_CN.md)

[![](https://z3.ax1x.com/2021/08/02/fpgDCq.png)](https://www.curseforge.com/minecraft/mc-mods/fabric-api) [![](https://z3.ax1x.com/2021/08/02/fpgr80.png)](https://www.curseforge.com/minecraft/mc-mods/cloth-config)
[![](https://s21.ax1x.com/2025/05/28/pVpUmYq.jpg)]()
## Introduction
Ultimate Scaler is a Minecraft Fabric Mod that provides some modification functions related to terrain generation, mainly for exploring the Far lands and various distance effects related to terrain generation.
This Mod is still in early development, and new features are being added frequently. There may be some bugs.

**Warning: The results of the modification may not be 100% in line with theoretical expectations.**

## Download

The latest stable version of the Mod can be downloaded from [GitHub Releases](https://github.com/INF32768/UltimateScaler/releases) or from [AppVeyor](https://ci.appveyor.com/project/INF32768/ultimatescaler/build/artifacts) for the latest development version.

## Features

- Offset terrain generation: Move most of the terrain generation in the range of `double` datatype, and it's able to offset these density function types currently:
    - `OldBlendedNoise`: Includes high, low and selector noise;
    - Noise types: Includes `Noise`, `ShiftedNoise`, `Shift`, `Shift_A` and `Shift_B`;
- Modify the `maintainPrecision` method, which implements the modification of the position of the far lands, and other functions such as removing the fringe lands;
- The `locate pos` command, which can be used to locate the coordinates of a specific position after offset.
- Fix various bugs in the original terrain generation algorithms.

## Configuration

The configuration of the Mod can be modified through *Mod Menu* or by pressing the shortcut key (default is `Ctrl + U`). The configuration file is located in `config/ultimatescaler.toml`.

## Special thanks

- Thanks to @SysWOWBrO3([BiliBili](https://space.bilibili.com/482351725)) for providing icons, covers and suggestions for this Mod.

## License

This Mod is under the MIT license, and you can find the relevant information in the [LICENSE](LICENSE) file.