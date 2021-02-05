CREATE DATABASE IF NOT EXISTS Hotel;
USE Hotel;
CREATE TABLE IF NOT EXISTS guests(
	GuestId int NOT NULL,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    NumberOfNights INT,
    PRIMARY KEY(guestid)
);
CREATE TABLE IF NOT EXISTS rooms(
	RoomNumber int,
    RoomName VARCHAR(255),
    Beds int,
    Bedname VARCHAR(255),
    AcEquipped TINYINT(1),
    BreakFastIncluded TINYINT(1),
    ChargePerDay int,
    Guest TINYINT(1),
    PRIMARY KEY(RoomNumber)
);

