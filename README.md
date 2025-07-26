# Ultimate Scaler
**English** | [简体中文](README_CN.md)

[![](https://z3.ax1x.com/2021/08/02/fpgDCq.png)](https://www.curseforge.com/minecraft/mc-mods/fabric-api) [![](https://z3.ax1x.com/2021/08/02/fpgr80.png)](https://www.curseforge.com/minecraft/mc-mods/cloth-config)
[![](https://s21.ax1x.com/2025/05/28/pVpUmYq.jpg)]()
## Introduction
Ultimate Scaler is a Minecraft Fabric Mod that provides some modification functions related to terrain generation, mainly for exploring the Far lands and various distance effects related to terrain generation.
This Mod is still in early development, and new features are being added frequently. There may be some bugs.

**Warning: The results of the modification may not be 100% in line with theoretical actual.**

## Download

The latest stable version of the Mod can be downloaded from [GitHub Releases](https://github.com/INF32768/Ultimate_Scaler/releases) or from [AppVeyor](https://ci.appveyor.com/project/INF32768/ultimatescaler/build/artifacts) for the latest development version.

## Features

- Offset terrain generation: In a wide range, offset most of the terrain generation, and currently support the offset density function types are:
  - `double` offset: (`old_blended_noise`, `noise`, `shifted_noise`, `shift_A`, `shift_B`, `shift`, `weird_scaled_sampler`, `y_clamped_gradient`(optional))
  - `BigInteger` offset (_Experimental_): (`end_islands`) and any other `double` offset functions.
- Modify the `maintainPrecision` method, which implements the modification of the position of the far lands, and other functions such as removing the fringe lands;
- The `locate pos` command, which can be used to locate the coordinates of a specific position after offset.
- Fix various bugs in the original terrain generation algorithms.

## Configuration

If `Cloth Config` is installed, the option screen can be opened through *Mod Menu* or by pressing the binding key (default is `Ctrl + U`).

The configuration file is located in `config/ultimate_scaler.toml`. After modifying the file, execute the `/reload` command to take effect.

## Update plan

### Short-term plan

- ※Implement simple communication between the client and the server.
- ※Offset the sea level and the underground lava layer.
- ※Add usage instructions to the option interface.
- ※Disable specified noise: Make specified noise always return 0 when sampling.
- ※Global fluid replacement: Replace specified fluids during terrain generation to prevent the game from lagging due to too many fluids.
- ※Port to 1.18.2 ~ 1.21.1.
- New command: `noiseinfo`, which displays information related to a noise (such as value range, frequency, overflow position) when given a name or definition.
- Change the display of large values in the debugging screen to scientific notation.
- ✓Add comments to the configuration file.
- ✓Compatible with the server.
- ✓Offset the generation of the end island.
- ✓Offset the density function `weird_scaled_sampler`.
- ✓Rewrite the "noise" density function using BigInteger.
- ✓Automatically adjust the display of `TerrainPos` in the debugging screen according to the current offset mode.

### Middle-term plan

- Port to 1.18.1 and below versions.
- Port to Forge loader.
- Add terrain generation algorithms of different versions of Java Edition, such as 1.18.1, 1.12.2, Beta version, and so on, up to Pre-Classic version.
- Fix various bugs that occur when the coordinates exceed 33554432, such as crashes, blocks not rendering, and lighting effects abnormalities.
- Generate sky grids in the fringe lands.
- Remove the air walls at 30000000.
- Add "Quit without saving" feature.
- Prevent the game from crashing when chunk generation fails.

### Long-term plan

- Rewrite the terrain generation algorithm using BigInteger, which can offset more terrain generation, including structures and other features, and the mod will probably release 1.0 version at that time.
- Add Minecraft Bedrock version of terrain generation algorithm, including old versions.
- Link with a minimap mod (not determined yet) and draw the noise and density function graphic on the map.
- Bypass the 32-bit integer limit, 64-bit integer limit, single-precision floating-point number limit, and even double-precision floating-point number limit in the world boundary, and the mod will probably release 2.0 version and change the name at that time.
- Bypass the Y-axis limit and support the generation of terrain with a height of 2147483647 (possibly higher).

## Special thanks

- Thanks to @SysWOWBrO3([BiliBili](https://space.bilibili.com/482351725)) for providing icons, covers and suggestions for this Mod.

## License

This Mod is under the MIT license, and you can find the relevant information in the [LICENSE](LICENSE) file.

This Mod uses the [toml4j](https://github.com/mwanji/toml4j) library, which is licensed under the MIT license.