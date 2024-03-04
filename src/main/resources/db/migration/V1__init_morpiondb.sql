DROP TABLE IF EXISTS `joueur`;
CREATE TABLE `joueur` (
                          `id_joueur` int NOT NULL AUTO_INCREMENT,
                          `marqueur` char(1) NOT NULL,
                          `pseudo` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id_joueur`)
);

DROP TABLE IF EXISTS `partie`;
CREATE TABLE `partie` (
                          `id_partie` int NOT NULL AUTO_INCREMENT,
                          `hauteur` int NOT NULL,
                          `longueur` int NOT NULL,
                          `status_partie` enum('EN_COURS','JOUEUR1_GAGNE','JOUEUR2_GAGNE','EGALITE') DEFAULT NULL,
                          `joueur1_id` int DEFAULT NULL,
                          `joueur2_id` int DEFAULT NULL,
                          PRIMARY KEY (`id_partie`),
                          KEY `FKf8ac1tfj9vunyrpka8oxcsm26` (`joueur1_id`),
                          KEY `FK53enqltijfb0hqv7wc3knmjri` (`joueur2_id`),
                          CONSTRAINT `FK53enqltijfb0hqv7wc3knmjri` FOREIGN KEY (`joueur2_id`) REFERENCES `joueur` (`id_joueur`),
                          CONSTRAINT `FKf8ac1tfj9vunyrpka8oxcsm26` FOREIGN KEY (`joueur1_id`) REFERENCES `joueur` (`id_joueur`)
);

DROP TABLE IF EXISTS `tour`;
CREATE TABLE `tour` (
                        `id_tour` int NOT NULL AUTO_INCREMENT,
                        `emplacement` int NOT NULL,
                        `marqueur` char(1) NOT NULL,
                        `numero_tour` int NOT NULL,
                        `partie_id` int DEFAULT NULL,
                        PRIMARY KEY (`id_tour`),
                        KEY `FKpkhe1qcwendfvhwxyqhqgwp1h` (`partie_id`),
                        CONSTRAINT `FKpkhe1qcwendfvhwxyqhqgwp1h` FOREIGN KEY (`partie_id`) REFERENCES `partie` (`id_partie`)
)