use churchmanager;

CREATE TABLE IF NOT EXISTS `Assembly` (

  `id` int(11) NOT NULL auto_increment,
  `version` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `name`  varchar(100) NULL,
   PRIMARY KEY  (`id`)

);