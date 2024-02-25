# TP7-UDP: Exploration de la communication UDP

Ce projet présente trois exercices illustrant les concepts fondamentaux et avancés de la communication UDP en utilisant les classes `DatagramSocket` et `DatagramPacket` de Java.

## Exercice 1: Connexion UDP de base

Cet exercice établit une connexion UDP rudimentaire en :

- **Client:**
  - Envoie un message simple ("Hello Server") au serveur en utilisant un `DatagramPacket`.
- **Serveur (non inclus):**
  - Accepte le message du client et envoie une confirmation.

## Exercice 2: Modification d'objets sérialisables (Version corrigée)

Cet exercice se concentre sur l'envoi et la modification d'objets sérialisables :

- **Client:**
  - Envoie un objet sérialisable au serveur.
  - Reçoit l'objet modifié du serveur.
- **Serveur:**
  - Reçoit l'objet du client.
  - Renvoie l'objet modifié au client.
  
## Exercice 3: Renvoi de l'heure du serveur

Cet exercice montre comment recevoir un message quelconque et renvoyer l'heure actuelle :

- **Client:**
  - Envoie un message quelconque au serveur.
- **Serveur:**
  - Reçoit le message du client (quel que soit son contenu).
  - Récupère la date et l'heure actuelles à l'aide d'un objet `Date`.
  - Convertit l'objet `Date` en une chaîne de caractères.
  - Renvoie la chaîne de caractères représentant l'heure actuelle au client.
