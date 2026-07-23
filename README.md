# KaribouWelcome

A simple Paper plugin that welcomes players and automatically displays the server rules to new players.

## Features

- 👋 Displays a custom welcome message when a player joins.
- 📜 Sends the server rules to players on their first login.
- 📝 `/rule` command to display the rules again at any time.
- ⚙️ Rules are configurable through `config.yml`.

## Compatibility

- Paper
- Java 21

## Installation

1. Download the plugin.
2. Place the `.jar` file into your server's `plugins` folder.
3. Start or restart the server.

## Command

| Command | Description |
|---------|-------------|
| `/rule` | Displays the server rules. |

## Configuration

Example:

```yaml
rules:
  - "&6Welcome to the server!"
  - "&7Please respect other players."
  - "&aHave fun!"
```

## Roadmap

- [x] Welcome message
- [x] Rules command
- [x] Configurable rules
- [ ] Permissions
- [ ] Reload command
- [ ] Custom join messages in config

## License

MIT License