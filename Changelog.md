# Changelog

## 0.1.0 - 2025-5-5

### New Features
- Added the ability to preliminarily offset terrain.  
- Added the `caloffset` command.  
- Added a line to the debug HUD to show the current position of terrain generation.  

### Improvements
- Improved some code.  

### Bug Fixes
- Fixed a crash when trying to generate chunks outside 33552992 blocks.  


## 0.1.1 - 2025-5-8

### Improvements
- Command `caloffset` has been renamed to `locate pos` as a sub command of `locate`.  
- `locate pos` now takes in a new optional argument `range` to specify the range to search for the specified position.  
- `locate pos` can now throw more specific exceptions instead of a generic `UnexpectedException`.  
- Rewrote `appveyor.yml`.  

### Bug Fixes
- Fixed that the command `locate pos` would get stuck in an infinite loop in some cases.  


## 0.1.2 - 2025-5-10

### Bug Fixes
- [!] Corrected the case in the configuration file which was causing the game to crash.
- Fixed that the density function `shift` could not be scaled properly.  
- Corrected the typos in `README.md`.



## 0.1.3 - 2025-5-22

### New Features
- [!] Deleted the code from PercyDan/BorderRemover and reset the repository. This mod is no longer forked from PercyDan/BorderRemover.
- Deleted some unused code.
- Fully rewritten options screen, preparing for the next few updates.

### Improvements
- Cleared compile-time warnings

### Bug Fixes
- Fixed that the debug info `TerrainXYZ` does not work properly when using _Free Camera_.

## 0.2.0 - 2025-5-29

### New Features
- Added the mod icon.
- Added a keybind to open the option menu.
- Added the ability to move the far lands.
- Added the ability to limit the maximum return value of the method `maintainPrecision`.
- Added a switch to enable/disable the `TerrainXYZ` line in the debug HUD.
- Rewrote the `README.md` file.
- Added the MIT license.

### Improvements
- Improved the code quality.
- Improved the stability of the fix for the `IllegalStateException` when generating chunks outside the world border.
- Improved the literature quality of the package names and class names.

### Bug Fixes
- Fixed that the modded density function `shift` would return incorrect values.

## 0.2.0.1 - 2025-5-29

[Emergency]

### Bug Fixes
- [!] Fixed that the game could not launch.
- Fixed that the mod icon was fuzzy.
- Corrected the incorrect explanation in option menu.

## 0.2.1 - 2025-6-14

### New Features
- Added the update plan in `README.md`.

### Bug Fixes
- Fixed that the class `MixinAbstractChunkHolder` was not declared as an abstract class.

## 0.2.2 - 2025-6-30

### New Features
- Started the rewrite of the vanilla terrain generation algorithm with BigInteger.
- Added the ability to offset the density function `end_islands` using BigInteger.
- Added more content to the update plan in `README.md`.
- Configured the "Mixin config plugin".

### Improvements
- The "Global coordinate offset" entry in the options menu now uses BigInteger.

### Bug Fixes
- Fixed that there were Chinese characters in the English version of the `README.md`

## 0.2.3 - 2025-7-5

### 新特性 | New Features
- 新增了偏移密度函数 `weird_scale_multiplier` 的功能。 | Added the ability to offset the density function `weird_scale_multiplier`.
- 添加了新的更新计划。 | Added a new update plan.

### 改进 | Improvements
- 从现在开始，`Changelog.md` 将使用中英双语编写。 | From now on, `Changelog.md` will be written in both Chinese and English.

## 0.2.4 - 2025-7-5

请注意：此版本被错误地标记为 0.2.3。 | Note: This version was incorrectly marked as 0.2.3.

### 漏洞修复 | Bug Fixes
- 修复了 `weird_scale_multiplier` 偏移密度函数的错误。 | Fixed the error in the `weird_scale_multiplier` offset density function.

## 0.2.5 - 2025-7-6

### 新特性 | New Features
- 使用 BigInteger 重写了噪声类密度函数。 | Rewrote the "noise" density function with BigInteger.
- 新增了扩展数据包字面量取值范围的功能。 | Added the ability to extend the literal value range of datapacks.

### 改进 | Improvements
- 提取了常用的偏移代码到 `Util` 类中。 | Extracted common offset code to `Util` class.
- 调试屏幕中的 `TerrainXYZ` 现在会基于方块坐标计算，且会根据是否启用了 BigInteger 重写而显示不同的信息。 | The `TerrainXYZ` in the debug HUD now calculates based on block coordinates, and displays different information depending on whether BigInteger has been rewritten.
- 优化了内部类的访问形式，不再使用 `accesswidener`。 | Optimized the access of internal classes, no longer using `accesswidener`.
- 优化了“shift”类密度函数的偏移方式，不再使用 `@Overwrite` 而改用 `@ModifyArgs`，且类不再实现 `DensityFunctionTypes.Offset`。 | Optimized the offset of the "shift" class density function, no longer using `@Overwrite` but `@ModifyArgs`, and the class no longer implements `DensityFunctionTypes.Offset`.
- 清除了编译时警告（再次）。 | Cleared compile-time warnings (again).

### 漏洞修复 | Bug Fixes
- [!] 修复了从 0.2.2 前更新模组后，游戏无法启动的问题。 | Fixed that the game could not launch after updating the mod from 0.2.2.

## 0.2.6 - 2025-7-8

### 改进 | Improvements
- 切换“BigInteger重写”不再需要重启游戏。 | Switching "BigInteger rewrite" no longer requires a restart.

### 漏洞修复 | Bug Fixes
- 修复了使用BigInteger重写时，末地岛屿无法正确生成的问题。 | Fixed that the end islands were not generated correctly when using BigInteger rewrite.

## 0.3.0-pre1 - 2025-7-27

### 新特性 | New Features
- 新增了“Y轴扩展偏移”选项。 | Added the "Extra Y-axis offset" option.
- 支持服务端运行。 | Support for server-side running.
- 在配置文件中添加了注释。 | Added comments to the configuration file.
- 新增了使用/reload命令重载配置文件的功能。 | Added the ability to reload the configuration file using the /reload command.

### 改进 | Improvements
- [!] 更改了Mod ID，不再兼容旧版本。 | Changed the Mod ID, no longer compatible with old versions.
- 彻底重写了配置系统（不再兼容旧版，但是会自动迁移）。 | Completely rewrote the configuration system (not compatible with old versions, but will automatically migrate).
- 在配置文件中添加了文件版本号。 | Added file version number to the configuration file.
- Cloth Config 不再是必须依赖。 | Cloth Config is no longer required as a must-have dependency.
- 为客户端类添加了注解。 | Added annotations to client classes.
- “全局坐标偏移”现在再次接收小数输入。 | The "Global coordinate offset" now accepts decimal input again.

### 漏洞修复 | Bug Fixes
- 修正了不正确的命名。 | Corrected incorrect naming.

## 0.3.0-pre2 - 2025-7-27

[Emergency]

### 漏洞修复 | Bug Fixes
- [!] 修复了构建版导致游戏无法启动的问题。 | Fixed that the game could not launch.
- 修复了客户端中配置文件的注释未被翻译的问题。 | Fixed that the comments in the configuration file were not translated.
- 修复了ConfigReloader类位置错误的问题。 | Fixed that the ConfigReloader class was in the wrong location.

## 0.3.0-pre3 - 2025-7-29

### 新特性 | New Features
- “Y轴扩展偏移”现在可以偏移海平面和地底熔岩层。 | The "Extra Y-axis offset" now can offset the surface of the oceans and the underground lava layers.
- 新增了替换默认流体和地底熔岩的功能。 | Added the ability to replace the default fluids and underground lava.
- 向下兼容至1.21。 | Ported down to 1.21.

### 改进 | Improvements
- 重写了末地岛屿生成算法。 | Revised the algorithm for generating the end islands.
- 将“模拟末地环”更改为“修复末地环”，功能同样修改，且不需要再开启BigInteger重写。| Changed "Simulate End Rings" to "Fix End Rings", function is the same, and no longer need to enable BigInteger rewrite.
- 配置文件版本更新为2。 | Updated the configuration file version to 2.

### 漏洞修复 | Bug Fixes
- 这里绝对没有漏洞，修个屁:D | There is absolutely no bugs, fix a f**king sh*t:D

## 0.3.0-rc1 - 2025-8-7

### 新特性 | New Features
- 在选项界面中添加了常见问题。 | Added frequently asked questions to the options screen.
- 现在AppVeyor构建版本将自动发布到GitHub Release。 | The AppVeyor build version will automatically release to GitHub Release.

### 改进 | Improvements
- 将“替换地底熔岩”移入实验性功能。 | Moved "Replace Underground Lava" to experimental features.
- 向README.md添加了徽章和更多内容。 | Added badges and more content to README.md.
- 替换了ObjectValueWriter.class中已弃用的方法。 | Replaced the deprecated methods in ObjectValueWriter.class.

### 漏洞修复 | Bug Fixes
- 修复了“修复末地环”选项仍需要开启BigInteger重写的问题。 | Fixed that the "Fix End Rings" option still requires BigInteger rewrite.
- 修复了当边境之地位置为“RELEASE”时，噪声不会在负半轴上溢出的问题。 | Fixed that the noise would not overflow below the negative half-axis when the far lands position is set to "RELEASE".
- 修复了同时进行缩放和偏移时，海平面和地底熔岩层的高度不正确的问题。 | Fixed that the height of the surface of the oceans and the underground lava layers was incorrect when scaling and offsetting at the same time.
- 修复了当未启用BigInteger重写时，末地岛屿无法正确生成的问题。 | Fixed that the end islands were not generated correctly when BigInteger rewrite was not enabled.

## 0.3.0-rc2 - 2025-8-8

### 改进 | Improvements
- 去除了无用的import。 | Removed unnecessary imports.

### 漏洞修复 | Bug Fixes
- 修复了AppVeyor无法构建的问题。 | Fixed that AppVeyor could not build.