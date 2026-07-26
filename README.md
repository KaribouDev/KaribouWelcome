# KaribouWelcome

A simple and customizable Paper plugin that manages player welcome messages, join messages, and server rules.

## Features

- Custom first join messages.
- Custom returning player messages.
- Custom public join messages.
- Custom public quit messages.
- Automatically sends rules to new players.
- `/rules` command to display rules.
- Configurable messages with `config.yml`.
- Reload configuration without restarting.
- MiniMessage support for colors and text styles.

## Compatibility

- Paper
- Java 25

## Installation

1. Download the `.jar` file.
2. Put it into your server's `plugins` folder.
3. Start the server.
4. Edit `plugins/KaribouWelcome/config.yml`.
5. Use `/karibouwelcome reload`.

## Commands

| Command | Description | Permissions |
|---------|-------------|-------------|
| `/rules` | Displays the server rules. | Player |
| `/karibouwelcome reload` | Reloads the configuration. | OP |

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
%player%
```

## Roadmap

- [x] Welcome messages
- [x] Join messages
- [x] Rules system
- [x] Reload command
- [x] Permissions
- [x] MiniMessage support
- [x] Quit messages

## License

MIT License