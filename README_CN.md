# Ultimate Scaler
[English](README.md) | **简体中文**

[![](https://z3.ax1x.com/2021/08/02/fpgDCq.png)](https://www.curseforge.com/minecraft/mc-mods/fabric-api) [![](https://z3.ax1x.com/2021/08/02/fpgr80.png)](https://www.curseforge.com/minecraft/mc-mods/cloth-config)
[![](https://s21.ax1x.com/2025/05/28/pVpUmYq.jpg)]()
## 介绍

Ultimate Scaler是一个Minecraft Fabric Mod，它提供了一些有关地形生成的修改功能，主要用于探索边境之地和各种与地形生成有关的距离现象。

此Mod仍处于早期开发阶段，功能仍在不断增加中，且可能存在一些Bug。

**警告：修改的结果不一定100%符合理论预期**

## 获取

最新正式版的Mod可以从[GitHub Releases](https://github.com/INF32768/UltimateScaler/releases)下载，或在[AppVeyor](https://ci.appveyor.com/project/INF32768/ultimatescaler/build/artifacts)上下载最新开发版的Mod。

## 功能

- 偏移地形生成：在双精度浮点数的范围内偏移大多数地形生成，目前支持偏移的密度函数类型有：
  - `OldBlendedNoise`：包括高噪声、低噪声和选择器噪声；
  - Noise类型：包括 `Noise`, `ShiftedNoise`, `Shift`, `Shift_A` 和 `Shift_B`；
- 修改 `maintainPrecision` 方法，实现更改边境之地生成的位置、移除边缘之地等功能；
- `locate pos` 命令，用于定位一个特定位置偏移后的坐标；
- 修复各种原版地形生成算法的Bug；

## 配置

Mod的配置可以通过*Mod Menu*，或是按下快捷键（默认是 `Ctrl + U`）来进行修改，也可以通过配置文件来进行修改。

配置文件位于 `config/ultimatescaler.toml` 中

## 鸣谢

- 感谢 @SysWOWBrO3([BiliBili](https://space.bilibili.com/482351725)) 为本Mod提供图标、封面和建议。

## 许可证

本Mod遵循MIT许可证，你可以在[LICENSE](LICENSE)文件中找到相关信息。