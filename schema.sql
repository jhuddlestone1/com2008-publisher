CREATE DATABASE `team012`;

USE `team012`;

CREATE TABLE `Article` (
  `articleID` int(11) NOT NULL AUTO_INCREMENT,
  `pageRange` int(11) NOT NULL,
  `submissionID` int(11) NOT NULL,
  `editionID` int(11) NOT NULL,
  PRIMARY KEY (`articleID`),
  KEY `FK_Article_Submission_idx` (`submissionID`),
  KEY `FK_Article_Edition_idx` (`editionID`),
  CONSTRAINT `FK_Article_Edition` FOREIGN KEY (`editionID`) REFERENCES `Edition` (`editionID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Article_Submission` FOREIGN KEY (`submissionID`) REFERENCES `Submission` (`submissionID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `Criticism` (
  `criticismID` int(11) NOT NULL AUTO_INCREMENT,
  `criticism` mediumtext NOT NULL,
  `answer` mediumtext,
  `reviewID` int(11) NOT NULL,
  PRIMARY KEY (`criticismID`),
  KEY `DK_Criticism_Review_idx` (`reviewID`),
  CONSTRAINT `DK_Criticism_Review` FOREIGN KEY (`reviewID`) REFERENCES `Review` (`reviewID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;

CREATE TABLE `Edition` (
  `editionID` int(11) NOT NULL AUTO_INCREMENT,
  `volumeID` int(11) NOT NULL,
  `edition` varchar(20) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`editionID`),
  KEY `volumeID_idx` (`volumeID`),
  CONSTRAINT `FK_Edition_Volume` FOREIGN KEY (`volumeID`) REFERENCES `Volume` (`volumeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE `Journal` (
  `ISSN` int(11) NOT NULL,
  `journalTitle` tinytext NOT NULL,
  `chiefEditorID` int(11) NOT NULL,
  PRIMARY KEY (`ISSN`),
  KEY `FK_Journal_UserDetails_idx` (`chiefEditorID`),
  CONSTRAINT `FK_Journal_UserDetails` FOREIGN KEY (`chiefEditorID`) REFERENCES `UserDetails` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `JournalEditors` (
  `ISSN` int(11) NOT NULL,
  `editorID` int(11) NOT NULL,
  KEY `FK_JournalEditors_Journal_idx` (`ISSN`),
  KEY `FK_JournalEditors_UserDetails_idx` (`editorID`),
  CONSTRAINT `FK_JournalEditors_Journal` FOREIGN KEY (`ISSN`) REFERENCES `Journal` (`ISSN`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_JournalEditors_UserDetails` FOREIGN KEY (`editorID`) REFERENCES `UserDetails` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Review` (
  `reviewID` int(11) NOT NULL AUTO_INCREMENT,
  `summary` longtext NOT NULL,
  `typoList` varchar(45) NOT NULL,
  `iniVerdict` tinytext NOT NULL,
  `finVerdict` tinytext,
  `submissionID` int(11) NOT NULL,
  `reviewerID` int(11) NOT NULL,
  PRIMARY KEY (`reviewID`),
  KEY `FK_Review_UserDetails_idx` (`reviewerID`),
  KEY `FK_Review_Submission_idx` (`submissionID`),
  CONSTRAINT `FK_Review_Submission` FOREIGN KEY (`submissionID`) REFERENCES `Submission` (`submissionID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Review_UserDetails` FOREIGN KEY (`reviewerID`) REFERENCES `UserDetails` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

CREATE TABLE `Submission` (
  `submissionID` int(11) NOT NULL AUTO_INCREMENT,
  `title` mediumtext NOT NULL,
  `summary` longtext NOT NULL,
  `pdfFile` mediumblob,
  `reviewNumber` int(1) DEFAULT '0',
  `isApproved` tinyint(1) DEFAULT '0',
  `mainAuthorID` int(11) NOT NULL,
  `ISSN` int(11) NOT NULL,
  PRIMARY KEY (`submissionID`),
  KEY `FK_Submission_UserDetails_idx` (`mainAuthorID`),
  KEY `FK_Submission_Journal_idx` (`ISSN`),
  CONSTRAINT `FK_Submission_Journal` FOREIGN KEY (`ISSN`) REFERENCES `Journal` (`ISSN`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Submission_UserDetails` FOREIGN KEY (`mainAuthorID`) REFERENCES `UserDetails` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

CREATE TABLE `SubmissionAuthors` (
  `submissionID` int(11) NOT NULL,
  `authorID` int(11) NOT NULL,
  KEY `FK_ArticleAuthors_UserDetails_idx` (`authorID`),
  KEY `FK_ArticleAuthors_Submission_idx` (`submissionID`),
  CONSTRAINT `FK_SubmissionAuthors_Submission` FOREIGN KEY (`submissionID`) REFERENCES `Submission` (`submissionID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SubmissionAuthors_UserDetails` FOREIGN KEY (`authorID`) REFERENCES `UserDetails` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `UserDetails` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `title` tinytext NOT NULL,
  `forename` mediumtext NOT NULL,
  `surname` mediumtext NOT NULL,
  `uniAffiliation` mediumtext NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `userID_idx` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

CREATE TABLE `UserLogin` (
  `email` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `userID` int(11) NOT NULL,
  PRIMARY KEY (`email`),
  KEY `FK_UserLogin_UserDetails_idx` (`userID`),
  CONSTRAINT `FK_UserLogin_UserDetails` FOREIGN KEY (`userID`) REFERENCES `UserDetails` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Volume` (
  `volumeID` int(11) NOT NULL AUTO_INCREMENT,
  `ISSN` int(11) NOT NULL,
  `volume` varchar(10) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`volumeID`),
  KEY `ISSN_idx` (`ISSN`),
  CONSTRAINT `FK_Volume_Journal` FOREIGN KEY (`ISSN`) REFERENCES `Journal` (`ISSN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;