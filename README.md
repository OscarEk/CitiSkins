About
-----
CitiSkins is a module for the popular Citizens Bukkit plugin which adds NPC functionality to Minecraft Multiplayer. With the use of CitiSkins and the aid of [SpoutPlugin][Spout Link] you can add capes as well as skins to any NPCs while maintaining the NPC's name.

CitiSkins is [open-source][GitHub] | [Report an issue][Issues]

Installation
------------
* CitiSkins requires [Spout][Spout Link] and [Citizens][Citizens Link] to run. You can [download the latest recommended build of spout][Spout RB]
* This requires the Spoutcraft client mod to view skins and capes, but not to join the server (Download [here][Get Spout])
* You can learn more about Spoutcraft [here][Spout Wiki].
* Drag and drop Spout into your plugins directory
* Drag and drop Citizens into your plugins directory
* Drag and drop CitiSkins into your plugin directory
* Start the server

Usage
-----
* Commands
    - /citiskins | Shows version and author of plugin.
    - /citikins skin apply <url> | Gives your selected NPC a skin from the URL.
    - /citikins skin remove | Resets your selected NPCs skin.
    - /citiskins cape apply <url> | Gives your selected NPC a cape from the URL.
    - /citiskins cape remove | Give your selected NPC a cape from the URL.
* Permissions
    - <code>citiskins.*</code> | Gives complete access to CitiSkins commands.
    - <code>citiskins.skin.apply</code> | Gives access to apply NPC skins.
    - <code>citiskins.skin.remove</code> | Gives access to remove NPC skins.
    - <code>citiskins.cape.apply</code> | Gives access to apply NPC capes.
    - <code>citiskins.cape.remove</code> | Gives access to remove NPC capes.


Credits
-------
[![Windwaker](http://www.gravatar.com/avatar/942913bba29c93344d8a2e4da56c6bf1.png)](http://forums.spout.org/members/windwaker.47/)

Support
-------
Get support at TextureMe's [BukkitDev][Page]

[![Help me out!][Donate Icon]][Donate]

License
-------
TextureMe is licensed under [GNU Lesser General Public License v3][License]

[Spout Wiki]: http://wiki.spout.org
[Spout Link]: http://spout.in
[Citizens Link]: http://forums.bukkit.org/threads/7173/
[Spout RB]: http://spout.in/plugin
[Get Spout]: http://get.spout.org
[Reload]: http://spout.in/reload
[License]: http://www.gnu.org/licenses/lgpl.html
[Page]: http://dev.bukkit.org/server-mods/textureme/
[GitHub]: http://github.com/WalkerCrouse/TextureMe
[Issues]: http://github.com/WalkerCrouse/TextureMe
[Donate Icon]: https://www.paypalobjects.com/en_US/i/btn/btn_donate_LG.gif
[Donate]: https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=637G838ZVVD9N

For Developers - Hooking into CitiSkins
---------------------------------------
CitiSkins can alternatively be used as an easy way to skin your Citizens v2.x NPCs in your plugin, it only takes two easy steps to skin NPCs.

First
-----

Add CitiSkins as a dependency to your plugin with:

```
depend: [CitiSkins]
```

In your plugin's plugin.yml file. Alternatively, you can also make it optional with something like

```
softdepend: [CitiSkins]
```

In your plugin.yml and then

```java
@Override
public void onEnable() {
  if (Bukkit.getPluginManager().getPlugin("CitiSkins") == null) {
    // Log message or...
    Bukkit.getPluginManager().disablePlugin(this);
  }
}
```

In your plugin's onEnable() method.

Second
------
Add CitiSkins to your build path in your IDE and skin NPCs with

```java
CitiSkins.getInstance().getSkins().apply(citizenNPC, "skinUrl");
CitiSkins.getInstance().getSkins().remove(citizenNPC);

// or

CitiSkins.getInstance().getCapes().apply(citizenNPC, "capeUrl");
CitiSkins.getInstance().getCapes().remove(citizenNPC);
```