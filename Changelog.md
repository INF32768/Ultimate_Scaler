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