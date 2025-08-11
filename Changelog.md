# Changelog

## 0.1 - 2025-5-5

### 新特性 | New Features

- 添加了初步偏移地形的功能。 | Added the ability to preliminarily offset terrain.  
- 添加了 `caloffset` 命令。 | Added the `caloffset` command.  
- 在调试 HUD 中添加了显示地形生成位置的功能。 | Added a line to the debug HUD to show the current position of terrain generation.
- 添加了防止在33552992块以外生成地形时崩溃的功能。 | Added a check to prevent the game from crashing when trying to generate chunks outside 33552992 blocks.  

### 改进 | Improvements

- 优化了代码。 | Improved some code.

## 0.2 - 2025-5-29

### 新特性 | New Features

- ※ 删除了来自PercyDan/BorderRemover的代码并重置了仓库，此mod不再是fork自PercyDan/BorderRemover。 | Deleted the code from PercyDan/BorderRemover and reset the repository. This mod is no longer forked from PercyDan/BorderRemover.
- 添加了mod图标。 | Added the mod icon.
- 添加了打开选项菜单的快捷键。 | Added a keybind to open the option menu.
- 添加了移动边境之地的功能。 | Added the ability to move the far lands.
- 添加了限制`maintainPrecision`方法的最大返回值的功能。 | Added the ability to limit the maximum return value of the method `maintainPrecision`.
- 添加了选项来禁用/启用`TerrainXYZ`行。 | Added a switch to enable/disable the `TerrainXYZ` line in the debug HUD.
- 重写了`README.md`文件。 | Rewrote the `README.md` file.
- 添加了MIT许可证。 | Added the MIT license.

### 改进 | Improvements

- 删除了一些未使用的代码。 | Deleted some unused code.
- 完全重写了选项菜单，准备接下来几次更新。 | Fully rewritten options screen, preparing for the next few updates
- 优化了代码质量。 | Improved the code quality.
- 提高了代码的稳定性。 | Improved the stability of the code.
- 改进了包名和类名的命名。 | Improved the literature quality of the package names and class names.
- 命令 `caloffset` 现在被重命名为 `locate pos`，作为 `locate` 命令的子命令。 | The Command `caloffset` has been renamed to `locate pos` as a sub command of `locate`.
- `locate pos` 命令现在可以接受一个可选参数 `range` 来指定搜索指定位置的范围。 | The command `locate pos` now takes in a new optional argument `range` to specify the range to search for the specified position.
- `locate pos` 命令现在可以抛出更具体的异常，而不是一个通用的 `UnexpectedException`。 | The command `locate pos` can now throw more specific exceptions instead of a generic `UnexpectedException`.
- 清除了编译时警告。 | Cleared compile-time warnings
- 重写了`appveyor.yml`文件。 | Rewrote `appveyor.yml`.

### 漏洞修复 | Bug Fixes

- 修复了修改后的密度函数 `shift` 可能返回错误值的bug。 | Fixed that the modded density function `shift` would return incorrect values.
- 修复了 `locate pos` 命令可能陷入无限循环的bug。 | Fixed that the command `locate pos` would get stuck in an infinite loop in some cases.

## 0.3.0 - 2025-8-8

### 新特性 | New Features

- 在`README.md`中添加了更新计划。 | Added the update plan in `README.md`.
- 开始使用BigInteger重写原版地形生成算法，目前已重写噪声类密度函数和`end_islands`密度函数。 | Started the rewrite of the vanilla terrain generation algorithm with BigInteger, currently rewritten the noise-based density function and `end_islands` density functions.
- 配置了Mixin Config Plugin。 | Configured the "Mixin config plugin".
- 新增了偏移密度函数 `weird_scale_multiplier` 的功能。 | Added the ability to offset the density function `weird_scale_multiplier`.
- 新增了扩展数据包字面量取值范围的功能。 | Added the ability to extend the literal value range of datapacks.
- 新增了“Y轴扩展偏移”选项。 | Added the "Extra Y-axis offset" option.
- 支持服务端运行。 | Support for server-side running.
- 在配置文件中添加了注释。 | Added comments to the configuration file.
- 新增了使用/reload命令重载配置文件的功能。 | Added the ability to reload the configuration file using the /reload command.
- 新增了替换默认流体和地底熔岩的功能。 | Added the ability to replace the default fluids and underground lava.
- 向下兼容至1.21。 | Ported down to 1.21.
- 在选项界面中添加了常见问题。 | Added frequently asked questions to the options screen.
- 现在AppVeyor构建版本将自动发布到GitHub Release。 | The AppVeyor build version will automatically release to GitHub Release.

### 改进 | Improvements

- 从现在开始，`Changelog.md` 将使用中英双语编写。 | From now on, `Changelog.md` will be written in both Chinese and English.
- 提取了常用的偏移代码到 `Util` 类中。 | Extracted common offset code to `Util` class.
- 调试屏幕中的 `TerrainXYZ` 现在会基于方块坐标计算，且会根据是否启用了 BigInteger 重写而显示不同的信息。 | The `TerrainXYZ` in the debug HUD now calculates based on block coordinates, and displays different information depending on whether BigInteger has been rewritten.
- 优化了内部类的访问形式，不再使用 `accesswidener`。 | Optimized the access of internal classes, no longer using `accesswidener`.
- 优化了“shift”类密度函数的偏移方式，不再使用 `@Overwrite` 而改用 `@ModifyArgs`，且类不再实现 `DensityFunctionTypes.Offset`。 | Optimized the offset of the "shift" class density function, no longer using `@Overwrite` but `@ModifyArgs`, and the class no longer implements `DensityFunctionTypes.Offset`.
- 清除了编译时警告（再次）。 | Cleared compile-time warnings (again).
- [!] 更改了Mod ID，不再兼容旧版本。 | Changed the Mod ID, no longer compatible with old versions.
- 彻底重写了配置系统（不再兼容旧版，但是会自动迁移）。 | Completely rewrote the configuration system (not compatible with old versions, but will automatically migrate).
- 在配置文件中添加了文件版本号。 | Added file version number to the configuration file.
- Cloth Config 不再是必须依赖。 | Cloth Config is no longer required as a must-have dependency.
- 为客户端类添加了注解。 | Added annotations to client classes.
- 重写了末地岛屿生成算法。 | Revised the algorithm for generating the end islands.
- 将“模拟末地环”更改为“修复末地环”，功能同样修改，且不需要再开启BigInteger重写。| Changed "Simulate End Rings" to "Fix End Rings", function is the same, and no longer need to enable BigInteger rewrite.
- 将“替换地底熔岩”移入实验性功能。 | Moved "Replace Underground Lava" to experimental features.
- 向`README.md`添加了徽章和更多内容。 | Added badges and more content to `README.md`.
- 替换了ObjectValueWriter.class中已弃用的方法。 | Replaced the deprecated methods in ObjectValueWriter.class.
- 去除了无用的import。 | Removed unnecessary imports.
- 用中英双语重写了`Changelog.md`中原本只有英文的部分。 | Revised the English parts of `Changelog.md` into both Chinese and English.
- 从现在开始，若一个次要更新的正式版发布，则将`Changelog.md`中其对应的开发版本的更新内容进行整合。 | From now on, if a formal release of a minor update is made, the corresponding development version update content in `Changelog.md` will be integrated.

### 漏洞修复 | Bug Fixes

- 修复了类 `MixinAbstractChunkHolder` 未被声明为抽象类的问题。 | Fixed that the class `MixinAbstractChunkHolder` was not declared as an abstract class.
- 修复了修改后 `weird_scale_multiplier` 密度函数无法正常工作的问题。 | Fixed that the modified `weird_scale_multiplier` density function did not work properly.
- 修复了当边境之地位置为`RELEASE`时，噪声不会在负半轴上溢出的问题。 | Fixed that the noise would not overflow below the negative half-axis when the far lands position is set to `RELEASE`.

## 0.3.1 - 2025-8-11

### 新特性 | New Features

- 新增了两条常见问题。 | Added two frequently asked questions.

### 改进 | Improvements

- 创建了多个分支，用于支持不同版本的Minecraft。 | Created multiple branches to support different versions of Minecraft.
- 移除了不必要的版本校验。 | Removed unnecessary version validation.
- 替换了`README.md`中的一个徽章。 | Replaced one badge in `README.md`.
- 升级了Gradle版本。 | Upgraded Gradle version.
- 升级了Fabric Loader版本。 | Upgraded Fabric Loader version.
- 整理了语言文件。 | Organized language files.
- 为大多数分支启用了Minotaur和CurseGradle支持。 | Enabled Minotaur and CurseGradle support for most branches.