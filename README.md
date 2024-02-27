# Projet Morpion
Dans un simple but de pratique et d’amélioration de mes compétences en développement, je crée ce projet : un site dédié au jeu de Morpion (aussi appelé Tic Tac Toe).

Il s’agit d’un projet complet (Front, Back, DB et déploiement) en 2 temps :
- une V1 rapidement mise en place : mettre en place un site qui permet de jouer au morpion. Il s’agit là de créer les bases du projets et de toucher à tous les blocs du projet
- une V2 qui ajoute de nombreuses fonctionnalités : enrichir significativement la partie back pour travailler tout particulièrement ces compétences. Par exemple, mise en place de profils joueurs, d’historiques de parties, de stats, plusieurs niveaux de Bot à affronter, reprendre une partie à un tour précédent …

La présentation complète du projet est disponible ici :
[https://docs.google.com/document/d/1AbAoHNutDH7iYYH1ipINYfDpAFG3YLp7OlQS93910AE/edit?usp=sharing](https://docs.google.com/document/d/1AbAoHNutDH7iYYH1ipINYfDpAFG3YLp7OlQS93910AE)


### Guide d'installation

Ce programme utilise principalement les téchnologies suivantes coté Back :
- Java 17
- Spring Boot 3.2
- MySQL 8.1

### Prerequis

Pour installer et lancer ce programme, vous devez disposez de la configuration suivante :
- JRE 17
- MySQL

### Installation

1.Télécharger le projet Git

Télécharger et dézipper ce projet.


2.Installer la base de données

Par defaut, le programme Java fonctionne avec une base de données MySQL nommée "morpiondb" sur le port 3306. Il se connecte avec l'utilisateur "root".
Le programme Java génere et gere les tables, il suffit de créer la base de données.
Cette configuration peut etre personnalisée (port, nom de la base, utilisateur et mot de passe MySQL) dans le fichier application.properties.
