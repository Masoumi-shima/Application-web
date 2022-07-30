# Application web d'enregistrement des membres
## Table of Contents
- [Description](#description)
- [Technologies](#technologies)
- [Installation](#installation)
    * [Prérequis](#pr-requis)
    * [Monter et exécuter](#monter-et-ex-cuter)
- [Composants](#composants)
    * [_Member Class_](#-member-class-)
    * [_Repository_](#-repository-)
    * [_Service_](#-service-)
    * [_Controller_](#-controller-)
    * [_Generator_](#-generator-)
- [Usage](#usage)
    * [PRG](#prg)
    * [REST API](#rest-api)
- [Sources](#sources)

## Description
Ce projet est une application web permettant aux utilisateurs de s'inscrire en utilisant le formulaire d'inscription, de voir la liste des membres inscrits, et de modifier ou supprimer les membres existants.


## Technologies
[![Spring Boo](src/main/resources/static/images/springboot.png)](https://spring.io/projects/spring-boot)  
[![Thymeleaf](src/main/resources/static/images/thymeleaf.jpg)](https://www.thymeleaf.org)  
[![PostgreSQL](src/main/resources/static/images/postgresql.png)](https://www.postgresql.org)  


## Installation
### Prérequis
- Maven  
  - Installation avec _[Homebrew](https://brew.sh)_  
    `% brew install maven`  


  - Installation manuelle  
    - >https://maven.apache.org/install.html
  
### Monter et exécuter

- Montez l'application à l'aide de Maven en exécutant la commande ci-dessous dans le répertoire de l'application :  
`% mvn package  exec:java -Dexec.mainClass=WebApnplication`


- Une fois l'exécution terminée avec succès, exécutez l'application avec la commande suivante :  
`% mvn spring-boot: run`

Allez sur http://localhost:8080 afin de voir l'application en exécusion.

## Composants
### _Member Class_
Cette classe comprend les informations sur un membre, notamment son prénom, son nom, sa date de naissance, son adresse courriel, son sexe et s'il a réussi l'examen de l'ordre.
La classe crée une table de membre dans la base de données avec les colonnes représentatives (en utilisant JPA Hibernate) et marque chaque colonne avec des validations et des messages d'erreur spécifiés.
### _Repository_
Le _repository_ est le point de connexion entre l'application et la base de données, il contient une API pour les opérations CRUD. En utilisant le _repository_, on peut effectuer des actions CRUD sur l'objet, comme trouver un membre spécifique en utilisant son identifiant ou son courriel, ainsi que supprimer le membre.
### _Service_
Le service est l'endroit où la logique d'affaires est mise en œuvre. Tout ce qui doit être vérifié ou modifié avant d'aller dans la base de données doit être implémenté ici. Par exemple, la vérification de la duplication de l'adresse courriel lors de l'enregistrement d'un nouveau membre.
### _Controller_
Le contrôleur est chargé de traiter les demandes entrantes provenant du client, de les transmettre au _service_ et ensuite d'envoyer une réponse au client.
Dans ce projet, nous avons deux contrôleurs, le contrôleur _PRG_ qui traite les demandes _POST-REDIRECT-GET_ et le contrôleur _API_ qui est responsable du traitement des demandes _REST API_.
### _Generator_
Cette classe est responsable de la création d'un ID pour chaque objet de la base de données basé sur la logique d'affaires.
## Usage
### PRG
Montez et exécutez le projet en suivant les instructions de ce document. Allez sur l'hôte local et travaillez avec l'application en action.
### REST API
Ce service peut être vu en action en utilisant un client REST comme Postman ou YARC!. Le corps de la requête doit être au format Json. Vérifiez l'adresse fournie dans les méthodes de la classe _ApiController_ pour chaque action.
## Sources
- Images :  
> https://google.com
