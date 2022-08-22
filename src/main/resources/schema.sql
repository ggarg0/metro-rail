/*
 * *
 *  * The MIT License (MIT)
 *  * <p>
 *  * Copyright (c) 2022
 *  * <p>
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  * <p>
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  * <p>
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *
 */

DROP TABLE IF EXISTS METRO_CARD;
CREATE TABLE METRO_CARD (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              first_name VARCHAR(250) NOT NULL,
                              last_name VARCHAR(250) NOT NULL,
                              user_name VARCHAR(250) NOT NULL,
                              email VARCHAR(250) DEFAULT NULL,
                              card_number BIGINT,
                              card_pin VARCHAR(500),
                              balance DOUBLE
);
ALTER TABLE METRO_CARD ADD CONSTRAINT email_uq UNIQUE(email);
ALTER TABLE METRO_CARD ADD CONSTRAINT card_number_uq UNIQUE(card_number);
ALTER TABLE METRO_CARD ADD CONSTRAINT user_name_uq UNIQUE(user_name);

DROP TABLE IF EXISTS STATIONS;
CREATE TABLE STATIONS (
      id INT AUTO_INCREMENT  PRIMARY KEY,
      station_name VARCHAR(250) NOT NULL,
      station_sequence INT,
      in_count INT default 0,
      out_count INT default 0,
      tariff DOUBLE
);
ALTER TABLE STATIONS ADD CONSTRAINT station_sequence_uq UNIQUE(station_sequence);
