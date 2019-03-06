# ProtectionLib

Did you search a Proection check for the most important Protection Plugins ?

## Getting Started

The ProtectionLib can check if the Player X have the right Permissions on the Plot/Region to build on it or is it the owner of the region/plot.

### Commands

```
/protectionlib (aliases: [protl, ptl])
/protectionlib plugins | Display all hooked Plugins
/protectionlib debug (player) | Display some Informations then the building check is called
/protectionlib reload | Reload all hooks
```

### Permissions

```
protectionlib.command | allow the protectionlib commands
protectionlib.admin | bypass the building check
```

### Supported Plugins

* [AreaShop](https://www.spigotmc.org/resources/areashop.2991/)
* [WorldGuard v7](https://dev.bukkit.org/projects/worldguard)
* [IslandWorld](https://www.spigotmc.org/resources/island-world-skyblock-replacement.2757/)
* [PlotSquared & PlotSquared Legacy](https://www.spigotmc.org/resources/plotsquared.1177/)
* [Towny](http://towny.palmergames.com/)
* [PreciousStones](https://www.spigotmc.org/resources/preciousstones.5270/)
* [GriefPrevention](https://www.spigotmc.org/resources/griefprevention.1884/)
* [LandLord](https://www.spigotmc.org/resources/beta-landlord-2.44398/)
* [uSkyBlock](https://www.spigotmc.org/resources/uskyblock.2280/)
* [aSkyBlock](https://www.spigotmc.org/resources/askyblock.1220/)
* [RedProtect](https://www.spigotmc.org/resources/redprotect-for-all-versions-anti-grief-server-protection.15841/)
* [Residence](https://www.spigotmc.org/resources/residence-1-7-10-up-to-1-13-1.11480/)
* [Vault](https://www.spigotmc.org/resources/vault.34315/)
* [Kingdoms](https://www.spigotmc.org/resources/kingdoms-battle-for-land-might-and-glory.11833/)
* [Bentobox](https://github.com/BentoBoxWorld/bentobox)

### Add Own Plugin hooks

```
public void insertPluginHook(protectionObj pluginHookClass){
  ProtectionLib.getInstance().getWatchers().add(pluginHookClass)
}
```

### Maven Support

```
  <repositories>
      <repository>
          <id>jitpack.io</id>
          <url>https://jitpack.io</url>
      </repository>
  </repositories>
```

```
  <dependency>
      <groupId>com.gitlab.Ste3et_C0st</groupId>
      <artifactId>protectionlib</artifactId>
      <version>v0.9.7</version>
  </dependency>
```

Under Construction :/

### Licensing

MIT License

Copyright (c) 2019 Ste3et_C0st

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
