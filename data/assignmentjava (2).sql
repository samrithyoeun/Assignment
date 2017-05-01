-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2017 at 06:13 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `assignmentjava`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE IF NOT EXISTS `books` (
  `BookID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(200) DEFAULT NULL,
  `Isbn` varchar(50) DEFAULT NULL,
  `Category` varchar(50) DEFAULT NULL,
  `Accesscode` varchar(50) DEFAULT NULL,
  `Author` varchar(100) DEFAULT NULL,
  `Languages` varchar(100) DEFAULT NULL,
  `Edition` varchar(30) DEFAULT NULL,
  `Qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`BookID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`BookID`, `Title`, `Isbn`, `Category`, `Accesscode`, `Author`, `Languages`, `Edition`, `Qty`) VALUES
(2, 'C Programming in Linux', '978-87-403-0543-2', 'Real Science', 'A002', 'David Haskins', 'English', '2nd Edition', 15),
(3, 'Structured Programming with C++', '978-87-403-0099-4', 'Real Science', 'A002', 'Kjell Bäckman', 'English', '1st Edition', 20),
(4, 'Object Oriented Programming using Java', '978-87-7681-501-1', 'Real Science', 'A002', 'Simon Kendal', 'English', '1st Edition', 5),
(5, 'Java: The Fundamentals of Objects and Classes', '978-87-7681-475-5', 'Real Science', 'A002', 'David Etheridge', 'English', '1st Edition', 18),
(6, 'Java: Graphical User Interfaces', '978-87-7681-496-0', 'Real Science', 'A002', 'David Etheridge', 'English', '1st Edition', 13),
(7, 'Database Design and Implementation', '978-87-403-1046-7', 'Real Science', 'A002', 'Howard Gould', 'English', '1st Edition', 8),
(8, 'Introduction to programming and the C# language', '978-87-403-0250-9', 'Real Science', 'A002', 'Poul Klausen', 'English', '1st Edition', 10),
(9, 'Object Oriented Programming using C#', '978-87-7681-814-2', 'Real Science', 'A002', 'Simon Kendal', 'English', '1st Edition', 10),
(10, 'The Basics of Business Management – Vol I', '978-87-403-1594-3', 'Social Science', 'A010', 'Elly R. Twineyo Kamugisha', 'English', '1st Edition', 15),
(11, 'The Basics of Business Management – Vol II', '978-87-403-1607-0', 'Social Science', 'A010', 'Elly R. Twineyo Kamugisha', 'English', '1st Edition', 15),
(12, 'Modern Microeconomics', '978-87-403-0419-0', 'Social Science', 'A010', 'Sanjay Rode', 'English', '1st Edition', 20),
(13, 'Essentials of Microeconomics', '978-87-7681-410-6', 'Lifestyle + Sport', 'A010', 'Krister Ahlersten', 'English', '1st Edition', 10),
(14, 'I Still Can’t Speak English', '978-0-9561589-4-9', 'Art + Megazine', 'A010', 'Jason West', 'English', '1st Edition', 10),
(15, 'How to Write an Essay', '978-87-403-0571-5', 'Khmer + History', 'A010', 'Alan Barker', 'English', '1st Edition', 5),
(16, 'The Art of Interview Skills', '978-87-403-0716-0', 'World''s History', 'A010', 'Fiona Setch', 'English', '1st Edition', 5),
(17, 'Creating your CV as a self marketing tool', '978-87-7681-945-3', 'Story + Humor', 'A010', 'Paul H Brisk', 'English', '1st Edition', 5),
(18, '21st Century Internships', '978-87-403-0420-6', 'Law + Knowledge', 'A010', 'ABB , David Shindler , Mark Babbitt', 'English', '1st Edition', 5),
(19, 'Engineering Mathematics: YouTube Workbook', '978-87-403-0522-7', 'Biography', 'A019', 'Christopher C. Tisdell', 'English', '2nd Edition', 10),
(20, 'Concepts in Electric Circuits', '978-87-7681-499-1', 'Journal + News', 'A019', 'Dr. Wasif Naeem', 'English', '1st Edition', 10),
(21, 'Norse Mythology', '978-87-9001-134-2', 'Reference', 'A019', 'Neil Gaiman', 'English', '1st Edition', 5),
(22, 'American English File 5', '978-0-19-477619-6', 'Foriegn Language', 'A019', 'Christina Latham-Koenig', 'English', '2st Edition', 10),
(23, 'Divided We Stand', '978-16-3286314-0', 'Maths + Physic', 'A019', 'Marjorie J.Spruill', 'English', '1st Edition', 10),
(24, 'White Trash', '978-0-19-477619-0', 'Chemisty + Biology"', 'A019', 'Michel Lsow', 'Khmer', '4th Edition', 12);

-- --------------------------------------------------------

--
-- Table structure for table `borrows`
--

CREATE TABLE IF NOT EXISTS `borrows` (
  `BorrowID` int(11) NOT NULL AUTO_INCREMENT,
  `BookID` int(11) DEFAULT NULL,
  `MemberID` int(11) DEFAULT NULL,
  `BorrowDate` date DEFAULT NULL,
  `ReturnDate` date DEFAULT NULL,
  `Qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`BorrowID`),
  KEY `FK_BookID_Borrow` (`BookID`),
  KEY `FK_MemberID_Borrow` (`MemberID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE IF NOT EXISTS `members` (
  `MemberID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) DEFAULT NULL,
  `CardID` varchar(50) DEFAULT NULL,
  `Gender` varchar(20) DEFAULT NULL,
  `RegisterDate` date DEFAULT NULL,
  `ExpireDate` date DEFAULT NULL,
  `Mobile` varchar(20) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MemberID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=54 ;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`MemberID`, `Name`, `CardID`, `Gender`, `RegisterDate`, `ExpireDate`, `Mobile`, `Email`, `Address`) VALUES
(1, 'OEURN SENGCHHY', '24263603', 'Male', '2017-03-25', '2018-03-25', '086596291', 'sengchhyoeurn@gmail.com', 'Dankor, Phnom Penh'),
(2, 'YOEUN SAMRITH', '24263633', 'Male', '2017-03-25', '2018-03-25', '010532524', 'samrith.yoeun@gmail.com', 'Chbar Ampov, Phnom Penh'),
(3, 'SRUN VITOU', '98743987', 'Male', '2017-03-25', '2018-03-25', '010861028', 'vitousrun@gmail.com', 'Tuol Kok, Phnom Penh'),
(4, 'NOV MAKARA', '41102592', 'Male', '2017-03-25', '2018-03-25', '015503997', 'novmakara79@gmail.com', 'Pousenchey, Phnom Penh'),
(5, 'YOU CHONGEANG', '77879723', 'Male', '2017-03-25', '2018-03-25', '098837562', 'chongeangy@gmail.com', 'AreyKhsat ,Kandal'),
(6, 'DY VANARY', '54128923', 'Female', '2017-03-25', '2018-03-25', '013559379', 'dy.vanary@gmail.com', 'Dounpenh, Phnom Penh'),
(7, 'VOT DARO', '23890712', 'Male', '2017-03-25', '2018-03-25', '012909672', 'darovot@gmail.com', 'Chamkamon , Phnom Penh'),
(8, 'LY SREYNIT', '52001304', 'Female', '2017-03-25', '2018-03-25', '099883281', 'lysreynit@gmail.com', 'Reuseykeo, Phnom Penh'),
(9, 'CHAN RINA', '21340093', 'Female', '2017-03-25', '2018-03-25', '077731039', 'rinac@gmail.com', 'Dounpenh, Phnom Penh'),
(10, 'CHEA LINDA', '89093421', 'Female', '2017-03-25', '2018-03-25', '089531127', 'linda.girl@gmail.com', 'Tuol Kok, Phnom Penh'),
(11, 'VY BROSPOV', '24869901', 'Male', '2017-03-25', '2018-03-25', '010435571', 'vysreysros@gmail.com', '7makara, Phnom Penh'),
(12, 'LIM MINEA', '12902709', 'Male', '2017-03-25', '2018-03-25', '011550918', 'minealim@gmail.com', 'Dounpenh, Phnom Penh'),
(13, 'VAN BOREY', '12867290', 'Male', '2017-03-25', '2018-03-25', '012134802', 'borey.van@yahoo.com', 'Reuseykeo, Phnom Penh'),
(14, 'KUN LINA', '12009012', 'Female', '2017-03-25', '2018-03-25', '012317809', 'linakun@gmail.com', 'Chamkamon , Phnom Penh'),
(15, 'BU SREYPOV', '34091167', 'Female', '2017-03-25', '2018-03-25', '098825510', 'bu.sreypov@gmail.com', '7makara, Phnom Penh'),
(16, 'MIN DALIS', '21002880', 'Female', '2017-03-25', '2018-03-25', '077120910', 'dalis.min@gmail.com', 'Dangkor ,Phnom Penh'),
(17, 'RY DARO', '12370055', 'Male', '2017-03-25', '2018-03-25', '093661008', 'darory@gmail.com', 'Preak Pnov, Phnom Penh'),
(18, 'HUN LISA', '12095561', 'Female', '2017-03-25', '2018-03-25', '012324180', 'hun.lisa@gmail.com', 'Chbar Ampov, Phnom Penh'),
(19, 'DIM DANUT', '11925109', 'Male', '2017-03-25', '2018-03-25', '086327610', 'dimdanut@gmail.com', 'Sen SoK, Phnom Penh'),
(20, 'SY SAVIN', '34581008', 'Female', '2017-03-25', '2018-03-25', '016115921', 'sy.savin@gmail.com', 'Mean Chey, Phnom Penh'),
(21, 'KEO VANAK', '13909910', 'Male', '2017-03-25', '2018-03-25', '015921032', 'vanakkeo@gmail.com', 'Mean Chey, Phnom Penh'),
(22, 'BUN KAKADA', '90221855', 'Male', '2017-03-25', '2018-03-25', '011517790', 'kakada.bun@gmail.com', 'Tuol Kok, Phnom Penh'),
(23, 'POV DAVID', '90551866', 'Male', '2017-03-25', '2018-03-25', '086228901', 'davidpove@gmail.com', 'Dounpenh, Phnom Penh'),
(24, 'CHIN VOTHA', '88912301', 'Male', '2017-03-25', '2018-03-25', '087771899', 'votha.chin@gmail.com', 'Pousenchey, Phnom Penh'),
(25, 'LY DAVIN', '73119010', 'Female', '2017-03-25', '2018-03-25', '011517623', 'davinly@gmail.com', 'Reuseykeo, Phnom Penh'),
(26, 'RUENG KUNTHY', '11903512', 'Male', '2017-03-25', '2018-03-25', '099201389', 'kunthy.reung@gmail.com', 'Preak Pnov, Phnom Penh'),
(27, 'CHAN MONY', '90881105', 'Male', '2017-03-25', '2018-03-25', '081692290', 'monychan@gmail.com', 'Chbar Ampov, Phnom Penh'),
(28, 'YI KANIKA', '23093378', 'Female', '2017-03-25', '2018-03-25', '078100120', 'kanikayi@gmail.com', 'Dounpenh, Phnom Penh'),
(29, 'VY SREYSROS', '10034912', 'Female', '2017-03-25', '2018-03-25', '098957250', 'vysreysros@gmail.com', 'Tuol Kok, Phnom Penh'),
(30, 'HAI ROSA', '11308297', 'Female', '2017-03-25', '2018-03-25', '017982100', 'rosa.hai@gmail.com', 'Sen SoK, Phnom Penh'),
(31, 'Bora Rithy', '11308298', 'Male', '2017-03-25', '2018-03-25', '010234564', 'bora.rithy@gmail.com', 'Dankor, Phnom Penh'),
(32, 'Bunhour Hengly', '11308299', 'Male', '2017-03-25', '2018-03-25', '010234565', 'bunhour.hengly@gmail.com', 'Chbar Ampov, Phnom Penh'),
(33, 'Chab Chamnan', '11308300', 'Male', '2017-03-25', '2018-03-25', '010234566', 'chab.chamnan@gmail.com', 'Tuol Kok, Phnom Penh'),
(34, 'Chea Borin', '11308301', 'Male', '2017-03-25', '2018-03-25', '010234567', 'chea.borin@gmail.com', 'Pousenchey, Phnom Penh'),
(35, 'Chhoeun Bunchhorn', '11308302', 'Male', '2017-03-25', '2018-03-25', '010234568', 'chhoeun.bunchhorn@gmail.com', 'AreyKhsat ,Kandal'),
(36, 'Chhun Vanny', '11308303', 'Male', '2017-03-25', '2018-03-25', '010234569', 'chhun.vanny@gmail.com', 'Dounpenh, Phnom Penh'),
(37, 'Hay Thai Sakadarith', '11308304', 'Male', '2017-03-25', '2018-03-25', '010234570', 'hay.thai.sakadarith@gmail.com', 'Chamkamon , Phnom Penh'),
(38, 'Heng Borchhay', '11308305', 'Male', '2017-03-25', '2018-03-25', '010234571', 'heng.borchhay@gmail.com', 'Reuseykeo, Phnom Penh'),
(39, 'Heng Phearun', '11308306', 'Male', '2017-03-25', '2018-03-25', '010234572', 'heng.phearun@gmail.com', 'Dounpenh, Phnom Penh'),
(40, 'Heng Seyla', '11308307', 'Male', '2017-03-25', '2018-03-25', '010234573', 'heng.seyla@gmail.com', 'Tuol Kok, Phnom Penh'),
(41, 'Hing Longliza', '11308308', 'Male', '2017-03-25', '2018-03-25', '010234574', 'hing.longliza@gmail.com', '7makara, Phnom Penh'),
(42, 'Houn Hong', '11308309', 'Male', '2017-03-25', '2018-03-25', '010234575', 'houn.hong@gmail.com', 'Dounpenh, Phnom Penh'),
(43, 'Huor Keomony', '11308310', 'Female', '2017-03-25', '2018-03-25', '010234576', 'huor.keomony@gmail.com', 'Reuseykeo, Phnom Penh'),
(44, 'Keo Chimsun On', '11308311', 'Male', '2017-03-25', '2018-03-25', '010234577', 'keo.chimsun.on@gmail.com', 'Chamkamon , Phnom Penh'),
(45, 'Keo Vuth matha', '11308312', 'Male', '2017-03-25', '2018-03-25', '010234578', 'keo.vuth.matha@gmail.com', '7makara, Phnom Penh'),
(46, 'Keong Thon', '11308313', 'Male', '2017-03-25', '2018-03-25', '010234579', 'keong.thon@gmail.com', 'Dangkor ,Phnom Penh'),
(47, 'Khun Dymonypitou', '11308314', 'Male', '2017-03-25', '2018-03-25', '010234580', 'khun.dymonypitou@gmail.com', 'Preak Pnov, Phnom Penh'),
(48, 'Kin Channet', '11308315', 'Male', '2017-03-25', '2018-03-25', '010234581', 'kin.channet@gmail.com', 'Chbar Ampov, Phnom Penh'),
(49, 'Leng Makara', '11308316', 'Male', '2017-03-25', '2018-03-25', '010234582', 'leng.makara@gmail.com', 'Sen SoK, Phnom Penh'),
(50, 'Lim Pakrinha', '11308317', 'Male', '2017-03-25', '2018-03-25', '010234583', 'lim.pakrinha@gmail.com', 'Mean Chey, Phnom Penh'),
(51, 'Lim Seng Huot', '11308318', 'Male', '2017-03-25', '2018-03-25', '010234584', 'lim.seng.huot@gmail.com', 'Mean Chey, Phnom Penh'),
(52, 'Lok Oukmaley', '11308319', 'Female', '2017-03-25', '2018-03-25', '010234585', 'lok.oukmaley@gmail.com', 'Tuol Kok, Phnom Penh'),
(53, 'Ly Navin', '11308320', 'Female', '2017-03-25', '2018-03-25', '010234586', 'ly.navin@gmail.com', 'Dounpenh, Phnom Penh');

-- --------------------------------------------------------

--
-- Table structure for table `returns`
--

CREATE TABLE IF NOT EXISTS `returns` (
  `ReturnID` int(11) NOT NULL AUTO_INCREMENT,
  `BookID` int(11) DEFAULT NULL,
  `MemberID` int(11) DEFAULT NULL,
  `BorrowDate` date DEFAULT NULL,
  `ReturnDate` date DEFAULT NULL,
  `Qty` int(11) DEFAULT NULL,
  `Fine` float DEFAULT NULL,
  PRIMARY KEY (`ReturnID`),
  KEY `FK_BookID_Return` (`BookID`),
  KEY `FK_MemberID_Return` (`MemberID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=27 ;

-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE IF NOT EXISTS `settings` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `fine` double NOT NULL,
  `duration` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `settings`
--

INSERT INTO `settings` (`sid`, `fine`, `duration`, `qty`) VALUES
(1, 1000, 7, 2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(100) DEFAULT NULL,
  `Passwords` varchar(100) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `Username`, `Passwords`, `Type`) VALUES
(1, 'samrithyoeun', 'admin', 'admin'),
(2, 'guest', 'guest', 'guest');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `borrows`
--
ALTER TABLE `borrows`
  ADD CONSTRAINT `FK_BookID_Borrow` FOREIGN KEY (`BookID`) REFERENCES `books` (`BookID`),
  ADD CONSTRAINT `FK_MemberID_Borrow` FOREIGN KEY (`MemberID`) REFERENCES `members` (`MemberID`);

--
-- Constraints for table `returns`
--
ALTER TABLE `returns`
  ADD CONSTRAINT `FK_BookID_Return` FOREIGN KEY (`BookID`) REFERENCES `books` (`BookID`),
  ADD CONSTRAINT `FK_MemberID_Return` FOREIGN KEY (`MemberID`) REFERENCES `members` (`MemberID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
