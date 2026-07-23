# KaribouWelcome

A simple and customizable Paper plugin that manages player welcome messages, join messages, and server rules.

## Features

- Custom first join messages.
- Custom returning player messages.
- Custom public join messages.
- Automatically sends rules to new players.
- `/rule` command to display rules.
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
| `/rule` | Displays the server rules. | Player |
| `/karibouwelcome reload` | Reloads the configuration. | OP |

## Configuration

Messages are configured in:

```
plugins/KaribouWelcome/config.yml
```

Example:

```yaml
public:
  new-player: "<gold><bold>%player%</bold> joined for the first time!"
  login: "<green>%player% joined the server."

private:
  welcome:
    - "<gold>Welcome <yellow>%player%</yellow>!"
    - "<gray>Enjoy your stay."

  rules:
    - "<gold>========== Rules =========="
    - "<white>1. Respect other players."
    - "<white>2. No cheating."
    - "<white>3. Have fun!"
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

## License

MIT License