# Networking API
[![maven-central](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/maven-central_vector.svg)](https://central.sonatype.com/artifact/fr.herobrine/networking)
![gradle](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/built-with/gradle_vector.svg)
---
Cette librairie donne accès au protocole réseau d'Herobrine.fr.

Celle-ci est écrite en **Kotlin**, mais est compatible avec **Java**. Vous y trouvez
différents **codecs** permettant serialize/deserialize diverses données liées au serveur (économie, emotes, etc...) ainsi
qu'un ensemble de **packets** et de **fonctions** permettant d'interagir avec le serveur.


## Implémentation
**Gradle**
```kotlin
implementation("fr.herobrine:networking:(VERSION)")
```

**Maven**
```xml
<dependency>
    <groupId>fr.herobrine</groupId>
    <artifactId>networking</artifactId>
    <version>(VERSION)</version>
</dependency>
```