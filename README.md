# KaribouWelcome

A simple and customizable Paper plugin that manages player welcome messages, join messages, and server rules.

## Features

- Custom first join messages.
- Custom returning player join messages.
- Custom public join messages.
- Custom public quit messages.
- Automatically sends welcome messages to new players.
- Automatically displays rules with the `/rules` command.
- Configurable messages with `config.yml`.
- Reload configuration without restarting the server.
- Admin test commands to preview configured messages.
- Permission-based command access.
- MiniMessage support for colors and text styles.
- Tab completion support for plugin commands.

## Compatibility

- Paper
- Java 25
- MiniMessage (Adventure API)

## Installation

1. Download the `.jar` file.
2. Put it into your server's `plugins` folder.
3. Start the server.
4. Edit `plugins/KaribouWelcome/config.yml`.
5. Use `/karibouwelcome reload` to apply configuration changes.
6. Use `/karibouwelcome test <type>` to preview messages.

## Commands

Both `/karibouwelcome` and `/kbw` can be used to manage the plugin.

| Command | Description | Permissions |
|---------|-------------|-------------|
| `/rules` | Displays the server rules. | Player |
| `/karibouwelcome reload` / `/kbw reload` | Reloads the configuration. | Admin |
| `/karibouwelcome test <type>` / `/kbw test <type>` | Tests configured messages. | Admin |

Available test types:
- `join`
- `quit`
- `newplayer`
- `welcome`
- `rules`

## Configuration

Messages are configured in:

```
plugins/KaribouWelcome/config.yml
```

Example:

```yaml
public:
  # Message shown to everyone when a player joins
  login: "<green>%player% joined the server.</green>"

  # Message shown to everyone when a player joins for the first time
  new-player: "<gold><bold>%player%</bold> joined the server for the first time!</gold>"

  # Message shown to everyone when a player leaves
  quit: "<yellow>%player% left the server.</yellow>"

private:
  # Sent only to new players
  welcome:
    - "<gold>Welcome to the server, <yellow>%player%</yellow><gold>!"
    - "<gray>We hope you enjoy your stay."
    - "<gray>Use <yellow>/rules</yellow> to read the rules again."

  # Rules displayed with /rules
  rules:
    - "<gold>========== Rules =========="
    - "<white>1. Respect other players."
    - "<white>2. No cheating."
    - "<white>3. No griefing."
    - "<white>4. Have fun!"
    - "<gold>==========================="
```

## Formatting

KaribouWelcome uses MiniMessage formatting.

Examples:

```
<red>Red text
<gold><bold>Important message
<#55FF55>Custom color
<gradient:#ff0000:#ffff00>Gradient
```

Available variable:

```
The `%player%` variable is replaced automatically with the player's name.
```

## Roadmap

- [x] Welcome messages
- [x] Join messages
- [x] First join messages
- [x] Rules system
- [x] Reload command
- [x] Permissions system
- [x] MiniMessage support
- [x] Quit messages
- [x] Configurable messages with config.yml
- [x] Test commands for message previews
- [x] Command tab completion

## License

MIT License