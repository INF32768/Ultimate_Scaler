# Ultimate Scaler
[English](README.md) | **简体中文**

[![](https://z3.ax1x.com/2021/08/02/fpgDCq.png)](https://www.curseforge.com/minecraft/mc-mods/fabric-api) [![](https://z3.ax1x.com/2021/08/02/fpgr80.png)](https://www.curseforge.com/minecraft/mc-mods/cloth-config)
[![](https://s21.ax1x.com/2025/05/28/pVpUmYq.jpg)]()
## 介绍

Ultimate Scaler是一个Minecraft Fabric Mod，它提供了一些有关地形生成的修改功能，主要用于探索边境之地和各种与地形生成有关的距离现象。

此Mod仍处于早期开发阶段，功能仍在不断增加中，且可能存在一些Bug。

**警告：修改的结果不一定100%符合理论实际**

## 获取

最新正式版的Mod可以从[GitHub Releases](https://github.com/INF32768/Ultimate_Scaler/releases)下载，或在[AppVeyor](https://ci.appveyor.com/project/INF32768/ultimatescaler/build/artifacts)上下载最新开发版的Mod。

## 功能

- 偏移地形生成：在大范围内偏移大多数地形生成，目前支持偏移的密度函数类型有：
  - `double` 偏移：(`old_blended_noise`, `noise`, `shifted_noise`, `shift_A`, `shift_B`, `shift`, `weird_scaled_sampler`, `y_clamped_gradient`（可选）)；
  - `BigInteger` 偏移（_实验性_）：(`end_islands`) 以及所有 `double` 偏移。
- 修改 `maintainPrecision` 方法，实现更改边境之地生成的位置、移除边缘之地等功能；
- `locate pos` 命令，用于定位一个特定位置偏移后的坐标；
- 修复各种原版地形生成算法的Bug；

## 配置

在安装了*Cloth Config*的情况下，配置界面可以通过*Mod Menu*，或是按下快捷键（默认是 `Ctrl + U`）来打开，否则需要通过配置文件来进行修改。

配置文件位于 `config/ultimate_scaler.toml` 中，修改后执行 `/reload` 命令使配置生效。

## 更新计划

### 短期计划
- ※实现客户端和服务端的简单通信；
- ※在选项界面中添加使用说明；
- ※偏移海平面和地底熔岩层；
- ※禁用指定噪声：让指定噪声在采样时始终返回0；
- ※全局流体替换：在地形生成时替换指定的流体，防止流体过多导致游戏卡顿；
- ※向下移植到1.18.2 ~ 1.21.1；
- 新命令：`noiseinfo`，给定一个噪声的名称或定义，显示其相关信息（如值域、频率、溢出位置等）；
- 将调试屏幕中过大的数值显示为科学计数法；
- ✓兼容服务端；
- ✓为配置文件添加注释；
- ✓偏移末地岛屿的生成；
- ✓偏移密度函数 `weird_scaled_sampler`。
- ✓使用 BigInteger 重写噪声类密度函数；
- ✓依据当前使用的偏移模式，自动调整调试屏幕中 `TerrainPos` 的显示。

### 中期计划

- 向下移植到1.18.1及以下版本；
- 移植到Forge加载器；
- 添加Java版不同版本的地形生成算法，如1.18.1，1.12.2，Beta版等，直至Pre-Classic版本；
- 修复坐标超过33554432时出现的一系列Bug，如崩溃、方块停止渲染、光照效果异常等；
- 在边缘之地生成天空网格；
- 移除30000000处的空气墙；
- 添加“不保存并退出”功能；
- 阻止游戏在区块生成失败时崩溃。

### 长期计划

- 用BigInteger重写地形生成算法，支持偏移更多的地形生成，包括结构、地物等，预计届时模组将发布1.0版本；
- 添加基岩版地形生成算法，包括旧版本；
- 与某个小地图模组（尚未确定）联动，将噪声、密度函数的图像绘制在地图上；
- 在世界界限方面突破32位整数限制、64位整数限制、单精度浮点数乃至双精度浮点数的限制，预计届时模组将发布2.0版本并更换名称。
- 在世界界限方面突破Y轴限制，支持生成高度达到2147483647的地形（未来也有可能支持更高的高度）。

## 鸣谢

- 感谢 @SysWOWBrO3([BiliBili](https://space.bilibili.com/482351725)) 为本Mod提供图标、封面和建议。

## 许可证

本Mod遵循MIT许可证，可以在[LICENSE](LICENSE)文件中找到相关信息。

本Mod使用了来自[toml4j](https://github.com/mwanji/toml4j)的代码，该代码遵循MIT许可证。