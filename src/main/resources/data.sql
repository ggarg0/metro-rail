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

INSERT INTO METRO_CARD (first_name, last_name, user_name, email, card_number, card_pin, balance)
VALUES ('Adam', 'Sandler', 'adam_s', 'adam@hollywood.com', 11111, '$2a$10$AVI6e8rkyn.9Y93Yrbu9/.YM8plIBGZ33PUQy57U1fXw34uXT9mNC', 800),
	   ('Brad', 'Pitt', 'brad_p', 'brad@hollywood.com', 22222, '$2a$10$BkDnsFV/DA1.fIZ0l6BkEOcWxYMc3313nN2uD0YYNLKkwx6cgY/y.', 500),
	   ('Mark', 'Waugh', 'mark_w', 'mark@cricket.com', 33333, '$2a$10$2qcBY05Bo2W9VrlX7HUm1ONdc0OwTu5qF7382DOFlB3tnMydEpdl2', 1500),
       ('Chris', 'Gayle', 'chris_g', 'chris@cricket.com', 44444, '$2a$10$k2QUCfZf.t7dAHC3OvwdyO01yl5sNHhewaGBwyZRuCx/3LuzTQpPq', 1230);

INSERT INTO STATIONS (station_name, station_sequence, in_count, out_count, tariff, weekend_tariff)
VALUES ('A1', 1, 0, 0, 0, 0.75),
       ('A2', 2, 2, 2, 17, 0.75),
       ('A3', 3, 0, 0, 31, 0.75),
       ('A4', 4, 0, 0, 43, 0.75),
       ('A5', 5, 0, 0, 54, 0.75),
       ('A6', 6, 0, 0, 68, 0.75),
       ('A7', 7, 0, 0, 76, 0.75),
       ('A8', 8, 0, 0, 87, 0.75),
       ('A9', 9, 0, 0, 98, 0.75);