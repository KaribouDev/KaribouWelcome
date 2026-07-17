# Template universel de plugin Minecraft (Paper/Spigot + Maven + Java)

## Contenu du template

- `pom.xml` : configuration Maven, dépendance vers l'API Paper
- `src/main/java/com/exemple/monplugin/MonPlugin.java` : classe principale (onEnable/onDisable + commande `/bonjour`)
- `src/main/java/com/exemple/monplugin/MonListener.java` : exemple d'écouteur d'événement (message de bienvenue)
- `src/main/resources/plugin.yml` : métadonnées du plugin

## Étapes pour l'utiliser

### 1. Ouvrir le projet dans VS Code

- Dézippe le dossier `minecraft-plugin-template`
- Dans VS Code : `Fichier > Ouvrir un dossier` et sélectionne ce dossier
- L'extension Java devrait détecter automatiquement le `pom.xml` et importer les dépendances (regarde en bas à droite si Maven télécharge des paquets)

### 2. Personnaliser

- Renomme le package `com.exemple.monplugin` selon tes préférences (clic droit > Rename dans VS Code, ou fais-le manuellement)
- Change `groupId`, `artifactId`, `version` dans `pom.xml`
- Change `name` et `main` dans `plugin.yml` (le `main` doit toujours pointer vers ta classe qui extends `JavaPlugin`)
- Vérifie que la version du `paper-api` dans `pom.xml` correspond à ta version de serveur Minecraft (regarde https://papermc.io pour les versions disponibles)

### 3. Compiler avec Maven

Dans un terminal, à la racine du projet :

```bash
mvn clean package
```

Le fichier `.jar` sera généré dans le dossier `target/` (ex: `MonPlugin-1.0.0.jar`).

Astuce VS Code : tu peux aussi utiliser l'onglet Maven (icône éléphant dans la barre latérale) puis double-cliquer sur `package` sous "Lifecycle".

### 4. Tester sur un serveur

- Télécharge un serveur Paper correspondant à la version de ton API : https://papermc.io/downloads
- Place le `.jar` du serveur dans un dossier, lance-le une première fois pour générer les fichiers (accepte l'EULA dans `eula.txt`)
- Copie ton `.jar` de plugin compilé dans le dossier `plugins/` du serveur
- Relance le serveur : ton plugin doit apparaître dans les logs au démarrage
- Connecte-toi et teste la commande `/bonjour`

## Pour aller plus loin

- Ajoute de nouvelles commandes : déclare-les dans `plugin.yml` sous `commands:`, puis gère-les dans `onCommand()`
- Ajoute de nouveaux événements : crée une méthode annotée `@EventHandler` dans `MonListener.java` (ou une nouvelle classe listener) avec le bon type d'événement Bukkit en paramètre
- Si tu as besoin de librairies externes (autres que l'API serveur), regarde le plugin Maven **Shade** pour les inclure dans ton `.jar` final
- Documentation officielle Paper : https://docs.papermc.io/
- Javadocs Bukkit/Paper pour explorer tous les événements et méthodes disponibles
