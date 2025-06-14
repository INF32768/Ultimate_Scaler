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