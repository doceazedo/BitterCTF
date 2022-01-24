<h1 align="center">BitterCTF 🍋🚩</h1>

<p align="center">
  A modest Capture The Flag minigame plugin.
</p>

<p align="center">
  <img src="screenshot.gif" alt="BitterCTF screenshot">
</p>

This plugin is meant to be ran on a BungeeCord server. A "game manager/waiting lobby" plugin (that I may provide sometime soon) may be required to handle where the player should go when connecting, what team to join and what to do when the game ends.

Yes, that's right, this plugin basically requires another plugin that doesn't even exist yet.

## Setup

The setup is pretty basic for now. For now, red and blue wool are hardcoded the only blocks that can be the team flags. Red and blue are also the only colors. Oh, and everything is in pt-BR. All that should be fixed in the future!

1. Hit a red and blue wool with a stick to set them as the team flags.
2. Open the **config.yml** file and set multiple locations for the players to spawn for both teams. Make sure they both have the same amount of spawn points.

## Usage

All the commands are console-only, because they are meant to be called by the "game manager/waiting lobby" plugin I mentioned earlier.

| Command                         | Description                     |
|---------------------------------|---------------------------------|
| /jointeam \<uuid\> \<red/blue\> | Sets an user team by their UUID |
| /startgame                      | Starts the game                 |

## Building

With [maven](https://maven.apache.org) properly installed, run `mvn install` on the root directory. Use the generated jar file without prefixes in the **target** folder.

## Final thoughts

[MCCoroutine](https://github.com/Shynixn/MCCoroutine) for the win! I'm so greatful this exists.