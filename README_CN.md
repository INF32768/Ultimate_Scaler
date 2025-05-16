# Ultimate Scaler
[English](README.md) | **简体中文**

一个 Minecraft 模组，可以偏移和缩放地形生成。

[![](https://z3.ax1x.com/2021/08/02/fpgDCq.png)](https://www.curseforge.com/minecraft/mc-mods/fabric-api) [![](https://z3.ax1x.com/2021/08/02/fpgr80.png)](https://www.curseforge.com/minecraft/mc-mods/cloth-config)
![](https://s21.ax1x.com/2025/05/16/pEjzmLT.png)
## 设置

使用 _ModMenu_ 打开设置界面。

* `全局 X/Y/Z 缩放/偏移`：缩放或偏移大多数地形生成。

    * 这个选项目前只影响 Noise 和 OldBlendedNoise。  

    * 在未来，这个选项将影响几乎整个地形生成。  

## 调试屏幕

* 在调试屏幕中添加了一行`TerrainXYZ`，显示当前的地形生成位置。  
![](https://s21.ax1x.com/2025/05/05/pEq1jsg.png)

## 命令

* `locate pos`：使用二分法定位一个缩放和偏移过的新位置。  
    * 语法：`/locate pos <originalPos> <scale> <offset> [range]`  
    * `<originalPos>`：int/String，原本的 X/Y/Z 坐标。
    * `<scale>`：double，缩放因子，必须大于 0。
    * `<offset>`：double，偏移量。
    * `[range]`：double，搜索范围，默认为 `Double.MAX_VALUE / scale - offset`。
    * 示例：当没有偏移或缩放时，在 X/Z 轴的 607949781904244613165613056 处会生成一个特殊地形，我想知道当 X 轴的缩放为 4.5E8 且偏移量为 607949781904244603165613056 时该地形生成的位置，我可以使用 `/locate pos 607949781904244613165613056 450000000 607949781904244603165613056` 来获取新位置。此命令输出 77，这意味着缩放并偏移后此地形在 X 轴上的 77 处生成。
    * 这个命令在坐标很小时可能并不实用，但当坐标很大且受到精度损失时，这个命令非常有用。