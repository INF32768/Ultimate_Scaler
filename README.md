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

## 更新计划

### 短期计划

- 兼容服务端，实现客户端和服务端的简单通信；
- 为配置文件添加注释；
- 偏移末地岛屿的生成；
- 禁用指定噪声：让指定噪声在采样时始终返回0；
- 新命令：`noiseinfo`，给定一个噪声的名称或定义，显示其相关信息（如值域、频率、溢出位置等）；
- 全局流体替换：在地形生成时替换指定的流体，防止流体过多导致游戏卡顿；
- 向下移植到1.18.2 ~ 1.21.1。

### 中期计划

- 向下移植到1.18.1及以下版本；
- 移植到Forge加载器；
- 添加Java版不同版本的地形生成算法，如1.18.1，1.12.2，Beta版等，直至Pre-Classic版本；
- 修复坐标超过33554432时出现的一系列Bug，如崩溃、方块停止渲染、光照效果异常等；
- 在边缘之地生成天空网格；
- 移除30000000处的空气墙。

### 长期计划

- 用BigInteger重写地形生成算法，支持偏移更多的地形生成，包括结构、地物等，预计届时模组将发布1.0版本；
- 添加基岩版地形生成算法，包括旧版本；
- 与某个小地图模组（尚未确定）联动，将噪声、密度函数的图像绘制在地图上；
- 在世界界限方面突破32位整数限制、64位整数限制、单精度浮点数乃至双精度浮点数的限制，预计届时模组将发布2.0版本并更换名称。
- 在世界界限方面突破Y轴限制，支持生成高度达到2147483647的地形（未来也有可能支持更高的高度）。

## Update plan

### Short-term plan

- Compatible with the server, implement simple communication between the client and the server.
- Add comments to the configuration file.
- Offset the generation of the end island.
- Disable specified noise: Make specified noise always return 0 when sampling.
- New command: `noiseinfo`, which displays information related to a noise (such as value range, frequency, overflow position) when given a name or definition.
- Global fluid replacement: Replace specified fluids during terrain generation to prevent the game from lagging due to too many fluids.
- Port to 1.18.2 ~ 1.21.1.

### Middle-term plan

- Port to 1.18.1 and below versions.
- Port to Forge loader.
- Add terrain generation algorithms of different versions of Java Edition, such as 1.18.1, 1.12.2, Beta version, and so on, up to Pre-Classic version.
- Fix various bugs that occur when the coordinates exceed 33554432, such as crashes, blocks not rendering, and lighting effects abnormalities.
- Generate sky grids in the fringe lands.
- Remove the air walls at 30000000.

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