FormateurProject
================

Application Androïd de gestion des formateurs
A la suite d’une formation sur Android, nous avons entamé la réalisation d’un projet en binôme sur une période de deux semaines.
Ce projet concerne l’école INSTA qui dispense des formations d’informatique et de comptabilité depuis plus de 15 ans.
Dans un contexte d’ouverture et de communication, l’école INSTA désire mettre en place des outils permettant d’exploiter ses données.
Elle exprime le besoin de réaliser une application Android destiné à ses formateurs. 
Actuellement, aucune application mobile satisfaisante n’existe pour cette école. L’école dispose d’un site internet uniquement. 
C’est dans ce sens que va se tourner la présentation, à savoir comment nous avons planifié et organisé le projet en vue de le réaliser et de répondre aux attentes des clients. 
Modèle en cascade 


-Analyse du cahier des charges Expression des besoins/ distinction entre besoin et fonction
Seul les formateurs et l’Admin pourront accéder à l’application.
visualiser les informations des formateurs, des promos et des élèves. 
Accéder au news de l’école. accéder à la page Facebook et twitter de l’école.
L’Admin pourra  ajouter  supprimer des formateurs/élèves/promo. 
L’application devra être ergonomique.



Specification fonctionnelle générale
Réaliser une application qui contient une 1ere activité User MDP (authentification) l’authentification devra déboucher sur les informations de l’utilisateur.
Si l’Admin se connecte il doit accéder à une plateforme de suppression ou modification. 
La seconde activité sera l’affichage du listing des promos qui débouchera au moment du clic de la promo sur le contenu de la promo, à savoir la liste des élèves. Chaque élève contiendra une fiche descriptive modifiable ou non selon le rôle. 
Dans le même sens,  afficher la liste des formateurs de l’école où chaque formateur détient une fiche descriptive.
 La troisième activité devra gérer l’affichage des news de l’école.
Enfin la quatrième activité permettra la visualisation de la page Facebook et twitter de l’application.
L’application devra contenir un menu. 

Conception générale 
-Réalisation de maquette
Chaque module a bénéficié d’une phase de maquettage.  Ainsi les parties statiques sont définies et nous disposons d’un visuel pour chaque activité.  La réalisation des layouts en sera facilitée. 
Démonstration d’une maquette (partie réseau sociaux et partie authentification) 
-Etablir les scénarios selon les activités

Distinction entre le statique et le dynamique
Pour chaque module nous avons mis en place un scénario qui permettra de définir ce que fera le module en relation avec les IHM. 
Exemple du listing formateurs : 
voir cela de façon simple et permettre l’interaction sur la sélection du formateurs. La gestion de l’évènement "sélection du formateur et redirection vers le détail du formateur" a été réfléchie en amont pour structurer le projet et limiter le périmètre du module.

Contrainte technique : coté serveur générer flux JSON ou XML, avoir une application optimale

-Planning et répartition des tâches
Diagramme de Gantt
-Pert pour savoir l'ordre des tâches
-Phase de réalisation 


Présentation de l’architecture

-Phase de test


